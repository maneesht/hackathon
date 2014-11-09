package com.msolutions.hackathon;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class IntroActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }

    public void Wink(final View v) {
        final Random rand = new Random();
        final int random = rand.nextInt(3);
        final ImageView Left = (ImageView) findViewById(R.id.LeftWink);
        final ImageView Right = (ImageView) findViewById(R.id.RightWink);
        v.setVisibility(View.INVISIBLE);
        if (random == 1) {
            Left.setVisibility(View.VISIBLE);
            Right.setVisibility(View.INVISIBLE);
        }
        else if (random == 2) {
            Right.setVisibility(View.VISIBLE);
            Left.setVisibility(View.INVISIBLE);
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        v.setVisibility(View.VISIBLE);
                        Right.setVisibility(View.INVISIBLE);
                        Left.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }, 100);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intro, menu);
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
