package com.example.yugao.homework_try1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by yugao on 15/9/3.
 */
public class FragmentWithBook1 extends PlaceholderFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public FragmentWithBook1(){

    }

    //读取文件内容 得到String
    public static String getString(InputStream inputStream){
        InputStreamReader inputStreamReader = null;
        try{
            inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while((line = reader.readLine())!=null){
                sb.append(line);
                sb.append("\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    InputStream Book1InputStream = getResources().openRawResource(R.raw.book1);
    String Book1Content = getString(Book1InputStream);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TextView tv = (TextView)rootView.findViewById(R.id.about_info);
        tv.setText(Book1Content);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public static PlaceholderFragment newInstance(int sectionNumber) { //
        Log.v(LOG_TAG, "in the PlaceholderFragment newInstance method");
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        Log.v(LOG_TAG, "Bundle put args");
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);//

        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
