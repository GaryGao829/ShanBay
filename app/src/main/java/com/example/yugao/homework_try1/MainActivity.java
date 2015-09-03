package com.example.yugao.homework_try1;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(LOG_TAG, "Start onCreate");
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        Log.v(LOG_TAG,"Before set up drawer");

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        Log.v(LOG_TAG,"onCreate finished");
    }
    /**
     * THIS METHOD
     *  TO BE VERIFIED
     */




    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        // 在点击了导航栏中的Item之后刷新对应的Fragment
        // 比如点了 Book1 主界面就显示第一本书内容
        /**
         * 当后期书
         */

        /**
         *  得到 Book1 内容
         *  并且填充到 Fragment 内
         */

        FragmentManager fragmentManager = getSupportFragmentManager();
        PlaceholderFragment pf = FragmentWithBook1.newInstance(position + 1);
        fragmentManager.beginTransaction()
                .replace(R.id.container, pf)
                .commit();

        //PlaceholderFragment pf = PlaceholderFragment.newInstance(position + 1 ); //这句暂时没用
        //Log.v(LOG_TAG, String.valueOf(pf.getView().getId())); 这句有问题 直接爆炸

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



}
