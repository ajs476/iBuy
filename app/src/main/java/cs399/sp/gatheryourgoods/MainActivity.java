package cs399.sp.gatheryourgoods;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public boolean create_clicked = false;
    public boolean load_clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create UI elements
        Button buttonCreate = (Button)findViewById(R.id.buttonCreate);
        Button buttonLoad = (Button)findViewById(R.id.buttonLoad);

        // create animations and listeners for button presses
        final Animation shake = AnimationUtils.loadAnimation(this,R.anim.shake);
        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(create_clicked){
                    create_clicked = false;
                    startActivity(new Intent(MainActivity.this, CreateListActivity.class));
                }
                if(load_clicked){
                    load_clicked = false;
                    startActivity(new Intent(MainActivity.this, LoadList.class));
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
                v.startAnimation(shake);
            }
        });
        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_clicked = true;
                create_clicked = false;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
