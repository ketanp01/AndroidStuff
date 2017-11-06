package com.example.ketanpandey.everything;

import android.content.Context;
import android.view.View;
import android.widget.TabHost;

/**
 * Created by KetanPandey on 02-11-2017.
 */

public class MyTabFactory implements TabHost.TabContentFactory
{

    private final Context mContext;
    public MyTabFactory(Context context) {
        mContext = context;
    }

    @Override
    public View createTabContent(String s) {
        View v = new View(mContext);
        v.setMinimumWidth(0);
        v.setMinimumHeight(0);
        return v;
    }
}
