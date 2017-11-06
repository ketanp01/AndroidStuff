package com.example.ketanpandey.everything;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private ViewPagerAdapter mAdapter;
    private ViewPager mViewPager;
    MenuItem prevMenuItem;
    BottomNavigationView bottomNavigationView;
    int screenWidthDp;
    int screenHeightDp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Global.setContext(MainActivity.this);

        //to get screen width and height of real device starts
        Point size=new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        screenWidthDp=size.x;
        screenHeightDp=size.y;

        //StatusBarHeight
        int statusBarheight;
        Resources myResources = getResources();
        int idStatusBarHeight = myResources.getIdentifier("status_bar_height", "dimen", "android");
        if (idStatusBarHeight > 0)
            statusBarheight = getResources().getDimensionPixelSize(idStatusBarHeight);
        else
            statusBarheight = 0;

        //Action Bar Height
        final TypedArray styledAttributes = this.getTheme().obtainStyledAttributes( new int[] { android.R.attr.actionBarSize });
        int actionbarheight = (int) styledAttributes.getDimension(0, 0);
        screenHeightDp = screenHeightDp-actionbarheight-statusBarheight;
        Global.setmainActivityscreenHeightDp(screenHeightDp);

        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.homeItem:
                                if(Global.getBlogPostFlag())
                                {
                                    ListView blogPost_Listview=(ListView) findViewById(R.id.blogPost_Listview);
                                    String titles[]=Global.getBlogTitlesList();
                                    String content[]=Global.getBlogContentList();
                                    String usernames[]=Global.getUsernameList();
                                    ListAdapter adapter=new ListAdapter(getApplicationContext(),titles,content,usernames);
                                    blogPost_Listview.setAdapter(adapter);
                                    Global.setBlogPostFlag(false);
                                }
                                mViewPager.setCurrentItem(0);
                                break;
                            case R.id.blogPostItem:
                                mViewPager.setCurrentItem(1);
                                break;
//                            case R.id.profileItem:
//                                mViewPager.setCurrentItem(2);
//                                break;
                        }
                        return false;
                    }
                });

        // Fragments and ViewPager Initialization
        mViewPager=(ViewPager) findViewById(R.id.viewpagerMainActivity);
        mAdapter= new ViewPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position)
            {

                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Disable ViewPager Swipe
        mViewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });

    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.logout)
        {
            new LogoutClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        else if(id == R.id.share)
        {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "EVERYTHING: A Bloggin Application");
            sendIntent.setType("text/plain");
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class LogoutClass extends AsyncTask<Void, Void, Void>
    {
        private int userLogout_status;
        Service_Client_Interaction client=new Service_Client_Interaction();
        @Override
        protected Void doInBackground(Void... arg0)
        {

            userLogout_status= client.logout_Json(Global.getUserName());
            //start
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if(userLogout_status==1)
            {
                startActivity(new Intent(MainActivity.this,UserAuthentication.class));
                finish();
            }



        }

    }


}
