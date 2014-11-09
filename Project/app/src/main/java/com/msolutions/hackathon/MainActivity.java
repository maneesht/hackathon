package com.msolutions.hackathon;

import android.media.MediaPlayer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainActivity extends ActionBarActivity {
String page = "article.txt";
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

    public void showSummary() {
        try {

            BufferedReader read;
            InputStream is = getAssets().open(page);
            read = new BufferedReader(new InputStreamReader(is));
            String s;
            StringBuilder sb_content = new StringBuilder();
            while((s = read.readLine()) != null) {
                sb_content.append(s);
            }
            String str = sb_content.toString();
            Article article = new Article(str, is);
            String x = article.getSummary();
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(x);
        } catch (Exception e) {
            Log.e("MYAPP", "Exception",e);
        }
    }

    public void seeWholeArticle(View view) {
        Button button = (Button) findViewById(R.id.button);
        if(button.getText().equals("Full Article")) {
            BufferedReader read;
            String str = "";
            InputStream is =null;
            try {
                is = getAssets().open(page);
                read = new BufferedReader(new InputStreamReader(is));
                String s;
                while((s = read.readLine()) != null) {
                    str += s;
                }
            } catch (Exception e) {
                Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, e);
            }
            Article article = new Article(str, is);
            String x = article.getWholeArticle();
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(x);
            button.setText("Summarize");
        }
        else {
            showSummary();
            button.setText("Full Article");
        }

    }

    public void filter(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        TextView textView = (TextView) findViewById(R.id.textView);

        String typedEdited = editText.getText().toString().replaceAll(",", "");

            String typedWords[] = typedEdited.split(" ");
            String before = (String) textView.getText();
        if(before.length() <= 1) {
            BufferedReader read;
            String str = "";
            InputStream is =null;
            try {
                is = getAssets().open(page);
                read = new BufferedReader(new InputStreamReader(is));
                String s;
                while((s = read.readLine()) != null) {
                    str += s;
                }
            } catch (Exception e) {
                Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, e);
            }
            Article article = new Article(str, is);
            String x = article.getWholeArticle();
            before = x;
        }
        String sentences[] = before.split("\\.");
            String s = "";
            for(String sentence: sentences) {
                String words[] = sentence.split(" ");
                int count = 0;
                for(String word: words) {
                    for(int i = 0; i < typedWords.length; i++) {
                        String keyWord = typedWords[i];
                        if (word.equalsIgnoreCase(keyWord)) {
                            count++;
                            break;
                        }
                    }
                }
                if(count == typedWords.length) {
                    s+=sentence + ".";
                }
            }
            textView.setText(s);
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
        Button button = (Button) findViewById(R.id.button);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.article1) {
            page = "article.txt";
            if(button.getText().equals("Full Article"))
            seeWholeArticle(null);
            else
            showSummary();
            return true;
        }
        else {
            page = "article2.txt";
            if(button.getText().equals("Full Article"))
                seeWholeArticle(null);
            else
                showSummary();
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
                        InputStream is = getActivity().getAssets().open("article.txt");
                        read = new BufferedReader(new InputStreamReader(is));
                        String s;
                        StringBuilder sb_content = new StringBuilder();
                        while((s = read.readLine()) != null) {
                            sb_content.append(s);
                        }
                        String str = sb_content.toString();
                        Article article = new Article(str, is);
                        String x = article.getSummary();
                        Fragment fragment = new ContentFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("msg", x);
                        fragment.setArguments(bundle);
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.container, fragment);
                        transaction.commit();
                        final MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.articleopen);
                        mp.start();
                    } catch (Exception e) {
                        Log.e("MYAPP", "Exception",e);
                    }
                }
            };
            thread.start();
        }
    }
}
