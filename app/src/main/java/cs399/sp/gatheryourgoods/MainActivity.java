package cs399.sp.gatheryourgoods;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        startActivity(new Intent(MainActivity.this, ShareListActivity.class));
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
