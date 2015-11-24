package cs399.sp.gatheryourgoods;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Alex on 11/22/2015.
 */
// purpose: allow users to share their list as text using messenger application
public class ShareListActivity extends AppCompatActivity {


    public String email ="Shopping List: \n\n";

    @Override
    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.share);

        // load animation
        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        // load data from preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
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
            email += namelist+": "+amtlist+" ("+catlist+")\n";
        }

        // create share button
        Button shareListButton = (Button)findViewById(R.id.buttonShareCreatedList);
        shareListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    // create email intent on share button press
                    v.startAnimation(shake);
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
                        Toast.makeText(ShareListActivity.this,
                                "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

