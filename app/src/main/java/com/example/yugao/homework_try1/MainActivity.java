package com.example.yugao.homework_try1;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private MyHandler myHandler;
//    TextView tv;
    String Book1Content = "选择左侧导航栏加载文章";
    SpannableStringBuilder[] ssbArray = new SpannableStringBuilder[6];
    ObservableScrollView sv;
    ArrayList<String> dividedstring;
    Toast toast;

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
    public static final String LOG_TAG = "ShanBay";

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */

    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    final String BookName1 = "raw/book1";
    final String wordList1 = "raw/wordlist1";
    Findwords findwords = new Findwords();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.v(LOG_TAG, "Start onCreate");
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        //        Log.v(LOG_TAG, "Before set up drawer");

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
//        Log.v(LOG_TAG, "onCreate finished");
//        try{
//              Book1Content = getString(getResources().openRawResource(R.raw.book1));
//            Log.v(LOG_TAG, "test Findwords preprogress");
//            findwords.preProcessWordlist(getResources().openRawResource(R.raw.wordlist1));
//            Log.v("单词总个数"," "+ findwords.word5.size());
//            Log.v(LOG_TAG, String.valueOf(findwords.word1.toString()));
//            Log.v("allword 大小"," "+ findwords.allword.length);
//            Log.v("难度为0单词个数", " "+findwords.word0.size());
//            Log.v("难度为1单词个数", " "+findwords.word1.size());
//            Log.v("难度为2单词个数", " "+findwords.word2.size());
//            Log.v("难度为3单词个数", " "+findwords.word3.size());
//            Log.v("难度为4单词个数", " "+findwords.word4.size());
//            Log.v("难度为5单词个数", " "+findwords.word5.size());
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }



        myHandler = new MyHandler();
        MyThread myThread = new MyThread();
        new Thread(myThread).start();


    }
    /**
     * THIS METHOD
     *  TO BE VERIFIED
     */




    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Log.v(LOG_TAG,"Before onNavigationDrawerItemSelected run");

        FragmentManager fragmentManager = getSupportFragmentManager();
        PlaceholderFragment pf = new PlaceholderFragment();

        fragmentManager.beginTransaction()
               .replace(R.id.container, pf.newInstance(position+1))
                .commit();
        Log.v(LOG_TAG,"onNavigationDrawerItemSelected run");
//        try{
//            if(position == 0) tv.setText(Book1Content);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        Log.v(LOG_TAG,"position nvalue is " + position);
        Log.v(LOG_TAG,"begin transaction go");

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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



    class PlaceholderFragment extends Fragment implements SeekBar.OnSeekBarChangeListener,ScrollViewListener{
        private SeekBar seekbar;
        private static final String arguments = "test";
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public PlaceholderFragment newInstance(int sectionNumber) {
            Log.v(getClass().toString(),"PlaceholderFragment.newInstance");
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);

            args.putString(arguments,"Book "+sectionNumber +" Content " );
            if(sectionNumber == 1){
                args.putString(arguments,Book1Content);
                //MyThread myThread = new MyThread();
                //new Thread(myThread).start();

            }
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //把耗时的任务移出Main UI
//            try {
//                tv = (TextView)rootView.findViewById(R.id.about_info);
//                tv.setText(MainActivity.Book1Content);
//                Log.v(LOG_TAG, "子类填充TextView成功 ");
//
//            }catch(Exception e){
//                e.printStackTrace();
//            }

            seekbar = (SeekBar) rootView.findViewById(R.id.seekBar);
            seekbar.setOnSeekBarChangeListener(this);
            //tv = (TextView)rootView.findViewById(R.id.about_info);
            sv = (ObservableScrollView)rootView.findViewById(R.id.scrollView);

            sv.textView = (TextView)rootView.findViewById(R.id.about_info);

            //tv.setMovementMethod(ScrollingMovementMethod.getInstance());

            //tv.setText(savedInstanceState.getString(arguments));
//            if(getArguments().getInt(ARG_SECTION_NUMBER) == 0) {
            dividedstring = DivideString(getArguments().getString(arguments));
            //sv.textView.setText(dividedstring.get(0));
            sv.textView.setText(findwords.highlightByDifficulty2(0,dividedstring.get(0)));
            if(dividedstring.size()>1){
                //dividedstring = (ArrayList<String>) dividedstring.subList(1,dividedstring.size());
                dividedstring.remove(0);
            }
            sv.setScrollViewListener(this);




//            }
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Log.v(getClass().toString(), "onProgressChanged");
            Log.v(LOG_TAG, "高亮难度为" + String.valueOf(seekBar.getProgress()) + "以下的单词");
            String stringToBeSpannabled = sv.textView.getText().toString();
            Toast.makeText(getApplicationContext(),"高亮难度级别为"+seekBar.getProgress()+"及其以下的单词",Toast.LENGTH_SHORT).show();


            sv.textView.setText(findwords.highlightByDifficulty2(seekBar.getProgress(),stringToBeSpannabled));
            //sv.textView.setText(ssbArray[seekBar.getProgress()]);
            //Log.v(getClass().toString(),ssbArray[seekBar.getProgress()].toString());
            //tv.setText(""+seekBar.getProgress());

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }


        @Override
        public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {

            if(scrollView == sv){
                double percentage =Double.valueOf(scrollView.getScrollY()) / scrollView.getChildAt(0).getHeight();
                Log.v(getClass().toString(),"display percentage "+ Double.valueOf(scrollView.getScrollY()) / (scrollView.getChildAt(0).getHeight()) + "%");
                //sv.StringArrayList = (ArrayList)sv.StringArrayList.subList(1,sv.StringArrayList.size());

                if(percentage > 0.8){
                    //scrollView.textView.setText("超过了80%");
                    if(dividedstring.size() > 0) {
                        if (dividedstring.get(0) != null) {
                           scrollView.textView.append(findwords.highlightByDifficulty2(seekbar.getProgress(), dividedstring.get(0)));
                        }
                        //scrollView.textView.append(dividedstring.get(0));
                        //dividedstring = (ArrayList<String>)dividedstring.subList(1,dividedstring.size());

                        dividedstring.remove(0);
                    }
                }
            } else {
                Log.v(getClass().toString(), "scrollView is not sv");
            }

        }
    }
    class MyHandler extends Handler {
        public MyHandler(){
        }
        public MyHandler(Looper L){
            super(L);
        }
        public void handleMessage (Message msg){
            super.handleMessage(msg);
            Bundle b = msg.getData();

            //MainActivity.this.tv.setText(b.getString("BookContent"));
            //MainActivity.this.tv.setText(ssbArray[0]);

        }
    }

    class MyThread implements Runnable{

        @Override
        public void run() {
            try{
                Log.v(this.getClass().toString(),"try to get Book content ");
                InputStream Book1InputStream =  getResources().openRawResource(R.raw.book1);
                //Book1Content = MainActivity.getString(getResources().openRawResource(R.raw.book1));
                Book1Content = MainActivity.getString(Book1InputStream);
                Log.v(this.getClass().toString(), "子线程加载文本到Book1Content 完毕");


                InputStream WordlistinputStream = getResources().openRawResource(R.raw.wordlist1);
                //Log.v(getClass().toString(), MainActivity.getString(Book1InputStream));
                //这样就没有输出 换成WordlistinputStream就有输出
                //Log.v(getClass().toString(), MainActivity.getString(getResources().openRawResource(R.raw.book1)));

                findwords.preProcessWordlist(WordlistinputStream);

//                Log.v(this.getClass().toString(), "开始处理SpannableStringBuilder");
//                for(int i = 0;i<1;i++){
//                    ssbArray[i] = findwords.highlightByDifficulty(i,getResources().openRawResource(R.raw.book1));
//                    Log.v(getClass().toString(),"第" + i + "spannableString");
//                }
//                Log.v(getClass().toString(), "处理SpannableStringBuilder 完毕");

//                if(ssbArray[0] != null) {
//                    Log.v(getClass().toString(), "ssbArray[0] 的内容是 " + ssbArray[0].toString());
//                }

            }catch (Exception e){
                e.printStackTrace();
            }

            Message msg = new Message();
            Bundle b = new Bundle();
            b.putString("BookContent",Book1Content);

            msg.setData(b);
            MainActivity.this.myHandler.sendMessage(msg);
        }
    }

    public ArrayList<String> DivideString(String string){
        ArrayList<String> dividedString = new ArrayList<>();
        dividedString.ensureCapacity(100);
        while(string.length()>=10000){
            dividedString.add(string.substring(0,10000));
            string = string.substring(10000,string.length());
        }
        if(string != "") {
            dividedString.add(string);
        }
        return dividedString;
    }


}
