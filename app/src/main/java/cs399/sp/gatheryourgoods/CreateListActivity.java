package cs399.sp.gatheryourgoods;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Alex on 11/8/2015.
 */

public class CreateListActivity extends AppCompatActivity {

    public EditText addItemText;
    public EditText categoryText;
    public EditText addAmountText;
    public Button addItemButton;
    public String name;
    public String category;
    public String amountString;
    public String item_name, item_category, item_amount;
    public String item_list_string = "";
    public CustomListAdapter myAdapter;
    public Item noItem = new Item();
    public Item list_item;
    public boolean emptyFlag;
    public int list_size;
    public ArrayList<Item> results;
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    public Context context;
    public Button foodButton;
    public Button nonFoodButton;
    public Button backButton;
    public Button dairyButton,produceButton,frozenButton,deliButton,breadButton,beverageButton,dryButton;
    public Button cannedButton,bakingButton,breakfastButton,coffeeButton,snacksButton,condimentsButton,pastaButton;
    public Button hygieneButton,beautyButton,healthButton,homeButton;
    public static String itemCategory = "";
    public static  Animation shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        // set the current view
        setContentView(R.layout.activity_create_list);
        shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        // get item details from item list
        ArrayList item_details = getListData();
        Button setCategoryButton = (Button)findViewById(R.id.buttonSetCategory);
        categoryText = (EditText)findViewById(R.id.editTextCategory);
//        Button loadList = (Button)findViewById(R.id.buttonLoadList);
//        loadList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(CreateListActivity.this, LoadList.class));
//            }
//        });
        // create list view object
        final ListView lv1 = (ListView) findViewById(R.id.custom_list);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Item entry = (Item)parent.getItemAtPosition(position);
                new AlertDialog.Builder(context)
                        .setTitle("Remove list item?")
                        .setMessage("Are you sure you want to remove: "+entry.getItemName())
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                results.remove(entry);
                                myAdapter.notifyDataSetChanged();
                                if(results.isEmpty()){
                                    results.add(noItem);
                                    myAdapter.notifyDataSetChanged();
                                    emptyFlag = true;
                                }
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
        setCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog InitialSelection
                v.startAnimation(shake);
                final Dialog initialSelectionDialog = new Dialog(context);
                initialSelectionDialog.setContentView(R.layout.initial_selection_alert);
                initialSelectionDialog.setTitle("Category Selection");
                TextView dialogDescription = (TextView) initialSelectionDialog.findViewById(R.id.textViewDialogDescription);
                dialogDescription.setText("Select your item's category using the buttons below");


                foodButton = (Button)initialSelectionDialog.findViewById(R.id.buttonFood);
                nonFoodButton = (Button)initialSelectionDialog.findViewById(R.id.buttonNonFood);
                backButton = (Button)initialSelectionDialog.findViewById(R.id.buttonBack);

                foodButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // on foodButton click
                        v.startAnimation(shake);
                        initialSelectionDialog.dismiss();
                        // create dialog for food selection 1
                        final Dialog foodSelection1Dialog = new Dialog(context);
                        foodSelection1Dialog.setContentView(R.layout.food_selection_1);
                        foodSelection1Dialog.setTitle("Food Category Selection");

                        dairyButton = (Button)foodSelection1Dialog.findViewById(R.id.buttonDairy);
                        produceButton = (Button)foodSelection1Dialog.findViewById(R.id.buttonProduce);
                        frozenButton = (Button)foodSelection1Dialog.findViewById(R.id.buttonFrozen);
                        deliButton = (Button)foodSelection1Dialog.findViewById(R.id.buttonDeli);
                        breadButton = (Button)foodSelection1Dialog.findViewById(R.id.buttonBread);
                        beverageButton = (Button)foodSelection1Dialog.findViewById(R.id.buttonBeverage);
                        dryButton = (Button)foodSelection1Dialog.findViewById(R.id.buttonDry);

                        dairyButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // dairy button clicked
                                v.startAnimation(shake);
                                itemCategory = "Dairy";
                                categoryText.setText(itemCategory);
                                foodSelection1Dialog.dismiss();
                            }
                        });
                        produceButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // produce button clicked
                                v.startAnimation(shake);
                                itemCategory = "Produce";
                                categoryText.setText(itemCategory);
                                foodSelection1Dialog.dismiss();
                            }
                        });
                        frozenButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // frozen button clicked
                                v.startAnimation(shake);
                                itemCategory = "Frozen";
                                categoryText.setText(itemCategory);
                                foodSelection1Dialog.dismiss();
                            }
                        });
                        deliButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // deli button clicked
                                v.startAnimation(shake);
                                itemCategory = "Deli & Meat";
                                categoryText.setText(itemCategory);
                                foodSelection1Dialog.dismiss();
                            }
                        });
                        breadButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // bread button clicked
                                v.startAnimation(shake);
                                itemCategory = "Bread & Bakery";
                                categoryText.setText(itemCategory);
                                foodSelection1Dialog.dismiss();
                            }
                        });
                        beverageButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // beverage button clicked
                                v.startAnimation(shake);
                                itemCategory = "Beverage";
                                categoryText.setText(itemCategory);
                                foodSelection1Dialog.dismiss();
                            }
                        });
                        dryButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // dry button clicked
                                v.startAnimation(shake);
                                foodSelection1Dialog.dismiss();
                                final Dialog foodSelection2Dialog = new Dialog(context);
                                foodSelection2Dialog.setContentView(R.layout.food_selection_2);
                                foodSelection2Dialog.setTitle("Food Category Selection");

                                cannedButton = (Button)foodSelection2Dialog.findViewById(R.id.buttonCanned);
                                bakingButton = (Button)foodSelection2Dialog.findViewById(R.id.buttonBaking);
                                breakfastButton = (Button)foodSelection2Dialog.findViewById(R.id.buttonBreakfast);
                                coffeeButton = (Button)foodSelection2Dialog.findViewById(R.id.buttonCoffee);
                                snacksButton = (Button)foodSelection2Dialog.findViewById(R.id.buttonSnacks);
                                condimentsButton = (Button)foodSelection2Dialog.findViewById(R.id.buttonCondiments);
                                pastaButton = (Button)foodSelection2Dialog.findViewById(R.id.buttonPasta);

                                cannedButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        // canned button clicked
                                        v.startAnimation(shake);
                                        itemCategory = "Canned";
                                        categoryText.setText(itemCategory);
                                        foodSelection2Dialog.dismiss();
                                    }
                                });
                                bakingButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        // baking button clicked
                                        v.startAnimation(shake);
                                        itemCategory = "Baking";
                                        categoryText.setText(itemCategory);
                                        foodSelection2Dialog.dismiss();
                                    }
                                });
                                breakfastButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        // breakfast button clicked
                                        v.startAnimation(shake);
                                        itemCategory = "Breakfast & Cereal";
                                        categoryText.setText(itemCategory);
                                        foodSelection2Dialog.dismiss();
                                    }
                                });
                                coffeeButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        // coffee button clicked
                                        v.startAnimation(shake);
                                        itemCategory = "Coffee Tea & Cocoa";
                                        categoryText.setText(itemCategory);
                                        foodSelection2Dialog.dismiss();
                                    }
                                });
                                snacksButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        // snacks button clicked
                                        v.startAnimation(shake);
                                        itemCategory = "Snacks & Candy";
                                        categoryText.setText(itemCategory);
                                        foodSelection2Dialog.dismiss();
                                    }
                                });
                                condimentsButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        // condiments button clicked
                                        v.startAnimation(shake);
                                        itemCategory = "Condiments";
                                        categoryText.setText(itemCategory);
                                        foodSelection2Dialog.dismiss();
                                    }
                                });
                                pastaButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        // pasta button clicked
                                        v.startAnimation(shake);
                                        itemCategory = "Pasta Grains & Meal Solutions";
                                        categoryText.setText(itemCategory);
                                        foodSelection2Dialog.dismiss();
                                    }
                                });
                                foodSelection2Dialog.show();
                            }
                        });
                        foodSelection1Dialog.show();
                    }

                });

                nonFoodButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // on nonFoodButton click
                        v.startAnimation(shake);
                        initialSelectionDialog.dismiss();
                        final Dialog nonFoodSelectionDialog = new Dialog(context);
                        nonFoodSelectionDialog.setContentView(R.layout.non_food_selection_1);
                        nonFoodSelectionDialog.setTitle("Non-Food Category Selection");

                        hygieneButton = (Button)nonFoodSelectionDialog.findViewById(R.id.buttonHygiene);
                        beautyButton = (Button)nonFoodSelectionDialog.findViewById(R.id.buttonBeauty);
                        healthButton = (Button)nonFoodSelectionDialog.findViewById(R.id.buttonHealth);
                        homeButton = (Button)nonFoodSelectionDialog.findViewById(R.id.buttonHome);

                        hygieneButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // hygiene button clicked
                                v.startAnimation(shake);
                                itemCategory = "Hygiene";
                                categoryText.setText(itemCategory);
                                nonFoodSelectionDialog.dismiss();

                            }
                        });

                        beautyButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // beauty button clicked
                                v.startAnimation(shake);
                                itemCategory = "Beauty";
                                categoryText.setText(itemCategory);
                                nonFoodSelectionDialog.dismiss();
                            }
                        });

                        healthButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // health button clicked
                                v.startAnimation(shake);
                                itemCategory = "Health";
                                categoryText.setText(itemCategory);
                                nonFoodSelectionDialog.dismiss();
                            }
                        });

                        homeButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // home button clicked
                                v.startAnimation(shake);
                                itemCategory = "Home";
                                categoryText.setText(itemCategory);
                                nonFoodSelectionDialog.dismiss();
                            }
                        });

                        nonFoodSelectionDialog.show();

                    }
                });

                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.startAnimation(shake);
                        initialSelectionDialog.dismiss();
                    }
                });

                initialSelectionDialog.show();
            }
        });
        // create and set the adapter for the list view
        lv1.setAdapter(myAdapter = new CustomListAdapter(this, results)); // might need item_details
        Button clearButton = (Button)findViewById(R.id.buttonClearList);
        Button saveButton = (Button)findViewById(R.id.buttonSaveList);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(shake);
                new AlertDialog.Builder(context)
                        .setTitle("Clear current list?")
                        .setMessage("Are you sure you want to clear the current list?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                results.clear();
                                results.add(noItem);
                                myAdapter.notifyDataSetChanged();
                                emptyFlag = true;
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
                v.startAnimation(shake);
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
            noItem.setItemName("Your list is empty");
            noItem.setItemAmount("0");
            noItem.setItemCategory("Home");
            results.add(noItem);
            emptyFlag = true;
        }

        // edit text field for adding item name
        addItemText = (EditText)findViewById(R.id.editTextAddItem);
        // edit text field for adding item category
        //addCategoryText = (EditText)findViewById(R.id.editTextAddCategory);
        // edit text field for adding item amount
        addAmountText = (EditText)findViewById(R.id.editTextAddAmount);
        // create add item button for user to press
        addItemButton = (Button)findViewById(R.id.buttonAddItem);
        // set listener for add item button, to check for user clicks
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(shake);
                Item newItem = new Item();
                // grab category from Edit Text
                category = itemCategory;
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
                } else {
                    // check if list is empty
                    if (emptyFlag) {
                        // no item was in list, remove and set emptyFlag to false
                        results.remove(noItem);
                        emptyFlag = false;
                    }
                    // add new item to list
                    results.add(newItem);
                    addItemText.getText().clear();
                    addAmountText.getText().clear();
                    categoryText.getText().clear();
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

        // grab item category and put in list according to category
        for(int i = 0; i<=list_size-1; i++){
            list_item = results.get(i);
            item_category = list_item.getItemCategory();
            if(item_category.equals("Produce")){
                item_name = list_item.getItemName();
                item_amount = list_item.getItemAmount();
                item_list_string += (item_name+",");
                item_list_string += item_category+"!";
                item_list_string += item_amount+"@";
            }
        }
        for(int i = 0; i<=list_size-1; i++){
            list_item = results.get(i);
            item_category = list_item.getItemCategory();
            if(item_category.equals("Bread & Bakery")){
                item_name = list_item.getItemName();
                item_amount = list_item.getItemAmount();
                item_list_string += (item_name+",");
                item_list_string += item_category+"!";
                item_list_string += item_amount+"@";
            }
        }
        for(int i = 0; i<=list_size-1; i++){
            list_item = results.get(i);
            item_category = list_item.getItemCategory();
            if(item_category.equals("Deli & Meat")){
                item_name = list_item.getItemName();
                item_amount = list_item.getItemAmount();
                item_list_string += (item_name+",");
                item_list_string += item_category+"!";
                item_list_string += item_amount+"@";
            }
        }
        for(int i = 0; i<=list_size-1; i++){
            list_item = results.get(i);
            item_category = list_item.getItemCategory();
            if(item_category.equals("Condiments")){
                item_name = list_item.getItemName();
                item_amount = list_item.getItemAmount();
                item_list_string += (item_name+",");
                item_list_string += item_category+"!";
                item_list_string += item_amount+"@";
            }
        }
        for(int i = 0; i<=list_size-1; i++){
            list_item = results.get(i);
            item_category = list_item.getItemCategory();
            if(item_category.equals("Beverage")){
                item_name = list_item.getItemName();
                item_amount = list_item.getItemAmount();
                item_list_string += (item_name+",");
                item_list_string += item_category+"!";
                item_list_string += item_amount+"@";
            }
        }
        for(int i = 0; i<=list_size-1; i++){
            list_item = results.get(i);
            item_category = list_item.getItemCategory();
            if(item_category.equals("Pasta Grains & Meal Solutions")){
                item_name = list_item.getItemName();
                item_amount = list_item.getItemAmount();
                item_list_string += (item_name+",");
                item_list_string += item_category+"!";
                item_list_string += item_amount+"@";
            }
        }
        for(int i = 0; i<=list_size-1; i++){
            list_item = results.get(i);
            item_category = list_item.getItemCategory();
            if(item_category.equals("Canned")){
                item_name = list_item.getItemName();
                item_amount = list_item.getItemAmount();
                item_list_string += (item_name+",");
                item_list_string += item_category+"!";
                item_list_string += item_amount+"@";
            }
        }
        for(int i = 0; i<=list_size-1; i++){
            list_item = results.get(i);
            item_category = list_item.getItemCategory();
            if(item_category.equals("Baking")){
                item_name = list_item.getItemName();
                item_amount = list_item.getItemAmount();
                item_list_string += (item_name+",");
                item_list_string += item_category+"!";
                item_list_string += item_amount+"@";
            }
        }
        for(int i = 0; i<=list_size-1; i++){
            list_item = results.get(i);
            item_category = list_item.getItemCategory();
            if(item_category.equals("Snacks & Candy")){
                item_name = list_item.getItemName();
                item_amount = list_item.getItemAmount();
                item_list_string += (item_name+",");
                item_list_string += item_category+"!";
                item_list_string += item_amount+"@";
            }
        }
        for(int i = 0; i<=list_size-1; i++){
            list_item = results.get(i);
            item_category = list_item.getItemCategory();
            if(item_category.equals("Frozen")){
                item_name = list_item.getItemName();
                item_amount = list_item.getItemAmount();
                item_list_string += (item_name+",");
                item_list_string += item_category+"!";
                item_list_string += item_amount+"@";
            }
        }
        for(int i = 0; i<=list_size-1; i++){
            list_item = results.get(i);
            item_category = list_item.getItemCategory();
            if(item_category.equals("Breakfast & Cereal")){
                item_name = list_item.getItemName();
                item_amount = list_item.getItemAmount();
                item_list_string += (item_name+",");
                item_list_string += item_category+"!";
                item_list_string += item_amount+"@";
            }
        }
        for(int i = 0; i<=list_size-1; i++){
            list_item = results.get(i);
            item_category = list_item.getItemCategory();
            if(item_category.equals("Coffee Tea & Cocoa")){
                item_name = list_item.getItemName();
                item_amount = list_item.getItemAmount();
                item_list_string += (item_name+",");
                item_list_string += item_category+"!";
                item_list_string += item_amount+"@";
            }
        }
        for(int i = 0; i<=list_size-1; i++){
            list_item = results.get(i);
            item_category = list_item.getItemCategory();
            if(item_category.equals("Dairy")){
                item_name = list_item.getItemName();
                item_amount = list_item.getItemAmount();
                item_list_string += (item_name+",");
                item_list_string += item_category+"!";
                item_list_string += item_amount+"@";
            }
        }
        for(int i = 0; i<=list_size-1; i++){
            list_item = results.get(i);
            item_category = list_item.getItemCategory();
            if(item_category.equals("Hygiene")){
                item_name = list_item.getItemName();
                item_amount = list_item.getItemAmount();
                item_list_string += (item_name+",");
                item_list_string += item_category+"!";
                item_list_string += item_amount+"@";
            }
        }
        for(int i = 0; i<=list_size-1; i++){
            list_item = results.get(i);
            item_category = list_item.getItemCategory();
            if(item_category.equals("Health")){
                item_name = list_item.getItemName();
                item_amount = list_item.getItemAmount();
                item_list_string += (item_name+",");
                item_list_string += item_category+"!";
                item_list_string += item_amount+"@";
            }
        }
        for(int i = 0; i<=list_size-1; i++){
            list_item = results.get(i);
            item_category = list_item.getItemCategory();
            if(item_category.equals("Beauty")){
                item_name = list_item.getItemName();
                item_amount = list_item.getItemAmount();
                item_list_string += (item_name+",");
                item_list_string += item_category+"!";
                item_list_string += item_amount+"@";
            }
        }
        for(int i = 0; i<=list_size-1; i++){
            list_item = results.get(i);
            item_category = list_item.getItemCategory();
            if(item_category.equals("Home")){
                item_name = list_item.getItemName();
                item_amount = list_item.getItemAmount();
                item_list_string += (item_name+",");
                item_list_string += item_category+"!";
                item_list_string += item_amount+"@";
            }
        }
    }

    public void saveListSharedPreferences(){
        String string_list = item_list_string;
        if(!string_list.contains("Your list is empty")){
            editor = preferences.edit();
            editor.putString("itemList", string_list);
            editor.apply();
            Toast.makeText(CreateListActivity.this, "List String Saved to preferences", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(CreateListActivity.this, "Cannot save empty list...", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearData(){
        editor = preferences.edit();
        editor.clear();
        editor.apply();
    }



}



