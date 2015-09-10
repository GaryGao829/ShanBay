package com.example.yugao.homework_try1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LessonDetailActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener{

    private View view1,view2,view3;
    private ViewPager viewPager;
    private List<View> viewList;
    private JSONObject lessonJSON;
    private String s1;
    private String s2;
    private String s3;
    private SeekBar seekBar1;
    private SeekBar seekBar2;
    private SeekBar seekBar3;
    TextView detailTextView1;
    TextView detailTextView2;
    TextView detailTextView3;
    Findwords findwords = new Findwords();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        String lessonString = intent.getStringExtra("JSONString");
        Log.v(getClass().toString(), lessonString);
        try {
             lessonJSON = new JSONObject(lessonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //this.getWindow().setTitle("Lesson: " + String.valueOf(intent.getIntExtra("LessonNumber",0)+1));

        setContentView(R.layout.activity_lesson_detail);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        LayoutInflater inflater = getLayoutInflater();

        view1 = inflater.inflate(R.layout.layout1,null);
        view2 = inflater.inflate(R.layout.layout2,null);
        view3 = inflater.inflate(R.layout.layout3,null);

        try {
             s1 = lessonJSON.getString("title") + "\n\n" + lessonJSON.getString("main");
             s2 = lessonJSON.getString("newWord");
             s3 = lessonJSON.getString("translation");
        } catch (JSONException e) {
            e.printStackTrace();
        }



        viewList = new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        detailTextView1 = (TextView)view1.findViewById(R.id.detail_main);
        detailTextView2 = (TextView)view2.findViewById(R.id.detail_words);
        detailTextView3 = (TextView)view3.findViewById(R.id.detail_translation);

        detailTextView1.setText(s1);
        detailTextView2.setText(s2);
        detailTextView3.setText(s3);

        seekBar1 = (SeekBar) view1.findViewById(R.id.detail_seekBar1);

        seekBar1.setOnSeekBarChangeListener(this);


        InputStream WordlistinputStream = getResources().openRawResource(R.raw.wordlist1);

        findwords.preProcessWordlist(WordlistinputStream);

        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            public void destroyItem(ViewGroup container,int position,Object object){
                container.removeView(viewList.get(position));
            }

            public Object instantiateItem(ViewGroup container,int position){
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        };

        viewPager.setAdapter(pagerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lesson_detail, menu);
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
    private static View getRootView(Activity context){
        return ((ViewGroup)context.findViewById(R.id.container)).getChildAt(0);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        Log.v(getClass().toString(), "onProgressChanged");
        Log.v(getClass().toString(), "高亮难度为" + String.valueOf(seekBar.getProgress()) + "以下的单词");
        String stringToBeSpannabled = detailTextView1.getText().toString();
        Toast.makeText(getApplicationContext(), "高亮难度级别为" + seekBar.getProgress() + "及其以下的单词", Toast.LENGTH_SHORT).show();
        Log.v(getClass().toString(), "stringToBeSpannabled is " + stringToBeSpannabled);
        Log.v(getClass().toString(), "seekBar.getProgress is " + seekBar.getProgress());

        //detailTextView1.setText(findwords.highlightByDifficulty2(seekBar.getProgress(), stringToBeSpannabled));
        //TextView tmp = (TextView)seekBar.getRootView().findViewById(R.id.detail_main);
        detailTextView1.setText(findwords.highlightByDifficulty2(seekBar.getProgress(), stringToBeSpannabled));
        Log.v(getClass().toString(),"findwords.allword[seekBar.getProgress()] is "+findwords.allword[seekBar.getProgress()].toString());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
