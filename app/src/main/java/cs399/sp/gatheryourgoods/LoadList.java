package cs399.sp.gatheryourgoods;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Alex on 11/18/2015.
 */
public class LoadList extends AppCompatActivity {

    public ArrayList<Item> results2;
    public CustomListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_list);
        results2 = new ArrayList<Item>();
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
            results2.add(newItem);
        }


        // create list view object
        final ListView lv2 = (ListView) findViewById(R.id.custom_list2);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item entry = (Item)parent.getItemAtPosition(position);
                Toast.makeText(LoadList.this, "Clicked: " + entry.getItemName(), Toast.LENGTH_SHORT).show();
            }
        });
        // create and set the adapter for the list view
        lv2.setAdapter(myAdapter = new CustomListAdapter(this, results2)); // might need item_details




    }
}
