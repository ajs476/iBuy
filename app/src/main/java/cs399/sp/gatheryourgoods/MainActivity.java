package cs399.sp.gatheryourgoods;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

// purpose: create main menu activity for app
public class MainActivity extends AppCompatActivity {

    public boolean create_clicked = false;
    public boolean load_clicked = false;
    public boolean share_clicked = false;
    public SharedPreferences preferences;
    public String email = "Shopping List: \n\n";
    public Context context;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        // check if previous list exists or not
        checkList();

        // create UI elements
        Button buttonCreate = (Button)findViewById(R.id.buttonCreate);
        Button buttonLoad = (Button)findViewById(R.id.buttonLoad);
        Button buttonShare = (Button)findViewById(R.id.buttonShare);

        // create animations and listeners for button presses
        final Animation shake = AnimationUtils.loadAnimation(this,R.anim.shake);
        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            // decide what to do after button animations finish
            @Override
            public void onAnimationEnd(Animation animation) {
                if(create_clicked){
                    create_clicked = false;
                    share_clicked = false;
                    load_clicked = false;
                    startActivity(new Intent(MainActivity.this, CreateListActivity.class));
                }
                if(load_clicked){
                    load_clicked = false;
                    create_clicked = false;
                    share_clicked = false;
                    if(!checkList()){
                        Toast.makeText(MainActivity.this, "No list has been created...", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        startActivity(new Intent(MainActivity.this, LoadList.class));
                    }
                }
                if(share_clicked){
                    share_clicked = false;
                    load_clicked = false;
                    create_clicked = false;
                    if(!checkList()){
                        Toast.makeText(MainActivity.this, "No list has been created...", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        // load data from preferences
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                        String itemsList = preferences.getString("itemList", "");
                        String delims = "[,!@]+";
                        String[] tokens = itemsList.split(delims);
                        String namelist = "";
                        String catlist = "";
                        String amtlist = "";
                        int length = tokens.length;
                        // grab item names
                        for(int i = 0; i<length; i+=3) {
                            // create new item
                            Item newItem = new Item();
                            // grab item names
                            namelist = tokens[i];
                            newItem.setItemName(namelist);
                            // grab item categories
                            catlist = tokens[i+1];
                            newItem.setItemCategory(catlist);
                            // grab item amounts
                            amtlist = tokens[i+2];
                            newItem.setItemAmount(amtlist);
                            // create email string message
                            email += namelist.substring(0,1).toUpperCase() + namelist.substring(1)+": "+amtlist+" ("+catlist+")\n";
                        }
                                // create email intent on share button press
                                Log.i("Send email", "");
                                String[] TO = {""};
                                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                                emailIntent.setData(Uri.parse("mailto:"));
                                emailIntent.setType("text/plain");
                                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "GatherYourGoods List");
                                emailIntent.putExtra(Intent.EXTRA_TEXT, email);

                                // make sure user has messenger app to share list with
                                try {
                                    startActivity(Intent.createChooser(emailIntent, "Share your list using... "));
                                    finish();
                                    Log.i("Finished sending email.", "");
                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(MainActivity.this,
                                            "There is no email client installed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                    }
                }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        // create button listeners
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_clicked = true;
                load_clicked = false;
                share_clicked = false;
                v.startAnimation(shake);
            }
        });
        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_clicked = true;
                create_clicked = false;
                share_clicked = false;
                v.startAnimation(shake);
            }
        });
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share_clicked = true;
                create_clicked = false;
                load_clicked = false;
                v.startAnimation(shake);
            }
        });
    }

    // runs right after onCreate
    @Override
    public void onStart(){
        super.onStart();

    }

    // runs right after onStart and after onPause
    @Override
    public void onResume(){
        super.onResume();

    }

    // runs if app is not being actively used by user
    @Override
    public void onPause(){
        super.onPause();

    }

    // runs at end of app lifecycle
    @Override
    public void onDestroy(){
        super.onDestroy();

    }

    // checking if list exists
    public boolean checkList(){
        // check if itemList has been saved in memory previously
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(preferences.contains("itemList")) {
            return true;
        }
        return false;
    }
}
