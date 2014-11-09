package com.msolutions.hackathon;

import android.app.Activity;
import android.content.Loader;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ContentFragment extends Fragment {
    String text = "";
    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle bundle = this.getArguments();
        text = bundle.getString("msg");
        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);
        Button button = (Button) rootView.findViewById(R.id.button);
        TextView textView = (TextView) rootView.findViewById(R.id.textView);
        Typeface font = Typeface.createFromAsset(
                getActivity().getAssets(),
                "Roboto-Light.ttf");
        button.setTypeface(font);
        textView .setTypeface(font);
        textView.setTextSize(25);
        textView.setText(text);
        return rootView;
    }

}
