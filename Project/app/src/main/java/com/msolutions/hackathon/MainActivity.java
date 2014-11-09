package com.msolutions.hackathon;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();

        }
    }

    public void seeWholeArticle(View view) {
        BufferedReader read;
        String str = "";
        try {
            InputStream is = getAssets().open("article.txt");
            read = new BufferedReader(new InputStreamReader(is));
            String s;
            while((s = read.readLine()) != null) {
                str += s;
            }
            Log.d("WIN!", str);
        } catch (Exception e) {
            Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, e);
        }
        Article article = new Article(str);
        String x = article.getWholeArticle();
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(x);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);


            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {

                        BufferedReader read;
                        InputStream is = getActivity().getAssets().open("article.txt");
                        read = new BufferedReader(new InputStreamReader(is));
                        String s;
                        String str = "";
                        while((s = read.readLine()) != null) {
                            str += s;
                        }
                        Article article = new Article(str);
                        final String x = article.getSummary();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                TextView textView = (TextView) rootView.findViewById(R.id.textView);
                                final ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
                                ScrollView scrollView = (ScrollView) rootView.findViewById(R.id.scrollView);
                                progressBar.setVisibility(View.INVISIBLE);
                                progressBar.setProgress(100);
                                scrollView.setVisibility(View.VISIBLE);
                                textView.setText(x);
                            }
                        });
                    } catch (Exception e) {
                        Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            };
            thread.start();
            return rootView;
        }
    }
}
