package cs399.sp.gatheryourgoods;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Alex on 11/8/2015.
 */
public class CreateListActivity extends AppCompatActivity {
    public ArrayList<String> itemList;
    public EditText addItemText;
    public Button addItemButton;
    public String item;
    public ArrayAdapter<String> itemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        addItemText = (EditText)findViewById(R.id.editTextAddItem);
        addItemButton = (Button)findViewById(R.id.buttonAddItem);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                item = addItemText.getText().toString();
                itemList.add(item);
                itemsAdapter.notifyDataSetChanged();

            }
        });


        itemList = new ArrayList<String>();
        // how to add to list:
        itemList.add("Apples");






        itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, itemList);

        ListView listView = (ListView) findViewById(R.id.listViewItems);
        listView.setAdapter(itemsAdapter);
    }

    @Override
    public void onStart(){
        super.onStart();


    }


    @Override
    public void onResume(){
        super.onResume();


    }

    @Override
    public void onPause(){
        super.onPause();

    }

    @Override
    public void onDestroy(){
        super.onDestroy();

    }


}



