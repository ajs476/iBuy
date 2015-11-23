package cs399.sp.gatheryourgoods;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Alex on 11/22/2015.
 */
public class ShareListActivity extends AppCompatActivity {


    public String email ="Shopping List: \n\n";

    @Override
    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.share);


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
            email += namelist+": "+amtlist+" ("+catlist+")\n";
        }


        Button shareListButton = (Button)findViewById(R.id.buttonShareCreatedList);
        shareListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Log.i("Send email", "");

                    String[] TO = {""};
                    //String[] CC = {"xyz@gmail.com"};
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");


                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    //emailIntent.putExtra(Intent.EXTRA_CC, CC);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "GatherYourGoods List");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, email);

                    try {
                        startActivity(Intent.createChooser(emailIntent, "Share "));
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

