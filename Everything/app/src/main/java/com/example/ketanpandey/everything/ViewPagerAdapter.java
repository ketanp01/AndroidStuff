package com.example.ketanpandey.everything;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.ketanpandey.everything.Fragments.blogPostFrag;
import com.example.ketanpandey.everything.Fragments.homeFrag;
import com.example.ketanpandey.everything.Fragments.profileFrag;

/**
 * Created by KetanPandey on 04-11-2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter
{
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                // Top Rated fragment activity
                return new homeFrag();
            case 1:
                // Games fragment activity
                return new blogPostFrag();
            case 2:
                return new profileFrag();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
