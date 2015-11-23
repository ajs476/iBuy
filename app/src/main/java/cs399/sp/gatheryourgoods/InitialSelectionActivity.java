package cs399.sp.gatheryourgoods;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Alex on 11/22/2015.
 */
public class InitialSelectionActivity extends AppCompatActivity{

    public Context context;
    public Button foodButton;
    public Button nonFoodButton;
    public Button backButton;
    public static String itemCategory = "";



    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_selection_alert);
        context = this;

        // custom dialog InitialSelection
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
                itemCategory = "Food";

            }
        });

        nonFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on nonFoodButton click
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialSelectionDialog.dismiss();
            }
        });

        initialSelectionDialog.show();

    }
}


