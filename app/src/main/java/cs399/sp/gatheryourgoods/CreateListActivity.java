package cs399.sp.gatheryourgoods;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Alex on 11/8/2015.
 */
public class CreateListActivity extends AppCompatActivity {
    public ArrayList<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        list = new ArrayList<String>();
        list.add("Apples");
        list.add("Bananas");




        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

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



