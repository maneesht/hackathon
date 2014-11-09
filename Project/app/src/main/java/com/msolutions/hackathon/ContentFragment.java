package com.msolutions.hackathon;

import android.app.Activity;
import android.content.Loader;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ContentFragment extends Fragment {
    String text = "";
    public ContentFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle bundle = this.getArguments();
        text = bundle.getString("msg");
        final View rootView =  inflater.inflate(R.layout.fragment_main, container, false);
        Button button = (Button) rootView.findViewById(R.id.button);
        TextView textView = (TextView) rootView.findViewById(R.id.textView);
        Typeface font = Typeface.createFromAsset(
                getActivity().getAssets(),
                "Roboto-Light.ttf");
        button.setTypeface(font);
        textView .setTypeface(font);
        textView.setTextSize(20);
        textView.setText(text);
        /*final EditText editText = (EditText) rootView.findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                Log.d("asdf", "SDFSDF");

                Thread thread = new Thread(){
                        @Override
                        public void run() {
                            TextView textView = (TextView) rootView.findViewById(R.id.textView);
                            String typedEdited = editText.getText().toString().replaceAll(",", "");
                            if(typedEdited.length() > 3) {
                                String typedWords[] = typedEdited.split(" ");
                                String before = (String) textView.getText();
                                String sentences[] = before.split("\\.");
                                String s = "";
                                for(String keyWord: typedWords) {
                                    for(String sentence: sentences) {
                                        String words[] = sentence.split(" ");
                                        for(String word: words) {
                                            if(word.equalsIgnoreCase(keyWord)){
                                                s+=sentence;
                                                break;
                                            }
                                        }
                                    }
                                }
                                textView.setText(s);
                            }
                        }
                };
                thread.start();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/
        return rootView;
    }

}
