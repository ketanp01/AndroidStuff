package com.example.ketanpandey.everything;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;

public class UserAuthentication extends FragmentActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

    private TabsPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private TabHost mTabHost;
    int screenWidthDp;
    int screenHeightDp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*to make display full screen starts (it requires uses-permission : ACCESS_NETWORK_STATE */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/*to make display full screen ends*/
        setContentView(R.layout.activity_user_authentication);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        // Tab Initialization
        initialiseTabHost();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        // Fragments and ViewPager Initialization

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(UserAuthentication.this);

        //to get screen width and height of real device starts
        Point size=new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        screenWidthDp=size.x;
        screenHeightDp=size.y;

        //StatusBarHeight
//        int statusBarheight;
//        Resources myResources = getResources();
//        int idStatusBarHeight = myResources.getIdentifier("status_bar_height", "dimen", "android");
//        if (idStatusBarHeight > 0) {
//            statusBarheight = getResources().getDimensionPixelSize(idStatusBarHeight);
//        }else{
//            statusBarheight = 0;
//
//        }

        //Action Bar Height
//        final TypedArray styledAttributes = this.getTheme().obtainStyledAttributes( new int[] { android.R.attr.actionBarSize });
//        int actionbarheight = (int) styledAttributes.getDimension(0, 0);
//        screenHeightDp = screenHeightDp-actionbarheight-statusBarheight;



    }

    // Method to add a TabHost
    private static void AddTab(UserAuthentication activity, TabHost tabHost, TabHost.TabSpec tabSpec) {
        tabSpec.setContent(new MyTabFactory(activity));
        tabHost.addTab(tabSpec);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int pos = this.mViewPager.getCurrentItem();
        this.mTabHost.setCurrentTab(pos);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String s) {
        int pos = this.mTabHost.getCurrentTab();
        this.mViewPager.setCurrentItem(pos);
    }

    // Tabs Creation
    private void initialiseTabHost() {
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();

        // TODO Put here your Tabs
        UserAuthentication.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("LOGIN").setIndicator("LOGIN"));
        UserAuthentication.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("SIGN UP").setIndicator("SIGN UP"));

        mTabHost.setOnTabChangedListener(this);
    }
}
