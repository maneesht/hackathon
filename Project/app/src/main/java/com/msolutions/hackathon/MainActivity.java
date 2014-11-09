package com.msolutions.hackathon;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        InputStream is =null;
        try {
            is = getAssets().open("article.txt");
            read = new BufferedReader(new InputStreamReader(is));
            String s;
            while((s = read.readLine()) != null) {
                str += s;
            }
            Log.d("WIN!", str);
        } catch (Exception e) {
            Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, e);
        }
        Article article = new Article(str, is);
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
            final View rootView = inflater.inflate(R.layout.fragment_loader, container, false);
            initiate(rootView);
            return rootView;
        }
        public void initiate(final View rootView) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {

                        BufferedReader read;
                        InputStream is = getActivity().getAssets().open("article2.txt");
                        read = new BufferedReader(new InputStreamReader(is));
                        String s;
                        StringBuilder sb_content = new StringBuilder();
                        while((s = read.readLine()) != null) {
                            sb_content.append(s);
                        }
                        String str = sb_content.toString();
                        Article article = new Article(str, is);
                        String x = article.getSummary();
                        Fragment fragment = new LoaderFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("msg", x);
                        fragment.setArguments(bundle);
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.container, fragment);
                        transaction.commit();

                    } catch (Exception e) {

                    }
                }
            };
            thread.start();
        }
    }
}
