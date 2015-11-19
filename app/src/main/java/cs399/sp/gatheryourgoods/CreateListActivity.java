package cs399.sp.gatheryourgoods;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Alex on 11/8/2015.
 */
public class CreateListActivity extends AppCompatActivity {
    public EditText addItemText;
    public EditText addCategoryText;
    public EditText addAmountText;
    public Button addItemButton;
    public String name;
    public String category;
    public String amountString;
    public String item_name, item_category, item_amount;
    //public String[] item_string_list;
    //public Set<String> mySet = new HashSet<String>(Arrays.asList(item_list_string));
    //public String item_string_list;
    public String item_list_string = "";
    public CustomListAdapter myAdapter;
    public Context context;
    public Item noItem;
    public Item list_item;
    public boolean emptyFlag;
    public int list_size;
    public ArrayList<Item> results;

    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        // set the current view
        setContentView(R.layout.activity_create_list);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        // get item details from item list
        ArrayList item_details = getListData();
        Button loadList = (Button)findViewById(R.id.buttonLoadList);
        loadList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateListActivity.this, LoadList.class));
            }
        });
        // create list view object
        final ListView lv1 = (ListView) findViewById(R.id.custom_list);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item entry = (Item)parent.getItemAtPosition(position);
                Toast.makeText(CreateListActivity.this, "Clicked: "+entry.getItemName(), Toast.LENGTH_SHORT).show();
            }
        });
        // create and set the adapter for the list view
        lv1.setAdapter(myAdapter = new CustomListAdapter(this, results)); // might need item_details
        Button clearButton = (Button)findViewById(R.id.buttonClearList);
        Button saveButton = (Button)findViewById(R.id.buttonSaveList);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Clear current list?")
                        .setMessage("Are you sure you want to clear the current list?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                results.clear();
                                results.add(noItem);
                                myAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing since user clicked cancel
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Save current list?")
                        .setMessage("Are you sure you want to save the current list?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // save list
                                if(!item_list_string.isEmpty()){
                                    clearData();
                                }
                                saveListString();
                                saveListSharedPreferences();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing since user clicked cancel
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    // grabs item details
    private ArrayList getListData(){
        // create list of item information to add
        results = new ArrayList<Item>();
        if(results.isEmpty()){
            noItem = new Item();
            noItem.setItemName("Your list is empty!");
            results.add(noItem);
            emptyFlag = true;
        }

        // edit text field for adding item name
        addItemText = (EditText)findViewById(R.id.editTextAddItem);
        // edit text field for adding item category
        addCategoryText = (EditText)findViewById(R.id.editTextAddCategory);
        // edit text field for adding item amount
        addAmountText = (EditText)findViewById(R.id.editTextAddAmount);
        // create add item button for user to press
        addItemButton = (Button)findViewById(R.id.buttonAddItem);
        // set listener for add item button, to check for user clicks
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item newItem = new Item();
                // grab category from Edit Text
                category = addCategoryText.getText().toString();
                // set items category
                newItem.setItemCategory(category);
                // grab item from add item Edit Text
                name = addItemText.getText().toString();
                // set items name
                newItem.setItemName(name);
                // grab item amount from edit text field
                amountString = addAmountText.getText().toString();
                // set item amount
                newItem.setItemAmount(amountString);

                // check that all fields have been completed for item
                if (name.isEmpty() || category.isEmpty() || amountString.isEmpty()) {
                    // display alert to user if fields are not empty
                    new AlertDialog.Builder(context)
                            .setTitle("Cannot add Item")
                            .setMessage("Please enter an item Name, Category, and Amount.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //do nothing, we just wanted to show the user the alert
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //do nothing, we just wanted to show the user the alert
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else {
                    // check if list is empty
                    if(emptyFlag){
                        // list is empty, make sure noItem is in it
                        if(results.contains(noItem)){
                            // no item was in list, remove and set emptyFlag to false
                            results.remove(noItem);
                            emptyFlag = false;
                        }
                    }
                    // add new item to list
                    results.add(newItem);
                    // notify adapter that we changed the list
                    myAdapter.notifyDataSetChanged();
                }
            }
        });
        // add the item and details to the list
        return results;

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

    public void saveListString(){
        list_size = results.size();
        item_list_string = "";

        for(int i = 0; i<=list_size-1; i++){

            list_item = results.get(i);
            item_name = list_item.getItemName();
            item_category = list_item.getItemCategory();
            item_amount = list_item.getItemAmount();
            item_list_string += (item_name+",");
            item_list_string += item_category+"!";
            item_list_string += item_amount+"@";
            Toast.makeText(CreateListActivity.this, "List String Saved as: "+item_list_string, Toast.LENGTH_LONG).show();
        }
    }

    public void saveListSharedPreferences(){
        String string_list = item_list_string;
        editor = preferences.edit();
        editor.putString("itemList", string_list);
        editor.apply();
        Toast.makeText(CreateListActivity.this, "List String Saved to preferences", Toast.LENGTH_SHORT).show();
    }

    public void clearData(){
        editor = preferences.edit();
        editor.clear();
        editor.apply();
    }



}



