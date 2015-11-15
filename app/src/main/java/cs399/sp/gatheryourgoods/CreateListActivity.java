package cs399.sp.gatheryourgoods;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;


/**
 * Created by Alex on 11/8/2015.
 */
public class CreateListActivity extends AppCompatActivity {
    public EditText addItemText;
    public EditText addCategoryText;
    public EditText addAmountText;
    public Button addItemButton;
    public String item;
    public String category;
    public String amountString;
    public CustomListAdapter myAdapter;
    public Context context;
    public Item noItem;
    public boolean emptyFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        // set the current view
        setContentView(R.layout.activity_create_list);
        // get item details from item list
        ArrayList item_details = getListData();
        // create list view object
        final ListView lv1 = (ListView) findViewById(R.id.custom_list);
        // create and set the adapter for the list view
        lv1.setAdapter(myAdapter = new CustomListAdapter(this, item_details));
    }

    // grabs item details
    private ArrayList getListData(){
        // create list of item information to add
        final ArrayList<Item> results = new ArrayList<Item>();
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
                newItem.setItemCategory("Category: " + category);
                // grab item from add item Edit Text
                item = addItemText.getText().toString();
                // set items name
                newItem.setItemName(item);
                // grab item amount from edit text field
                amountString = addAmountText.getText().toString();
                // set item amount
                newItem.setItemAmount("Amount: " + amountString);

                // check that all fields have been completed for item
                if (item.isEmpty() || category.isEmpty() || amountString.isEmpty()) {
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


}



