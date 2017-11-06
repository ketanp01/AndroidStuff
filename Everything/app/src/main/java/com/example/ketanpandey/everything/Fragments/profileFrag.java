package com.example.ketanpandey.everything.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ketanpandey.everything.Global;
import com.example.ketanpandey.everything.ListAdapter;
import com.example.ketanpandey.everything.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class profileFrag extends Fragment {


    public profileFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_profile, container, false);
        int height= Global.getmainActivityscreenHeightDp();
        int width=Global.getscreenWidthDp();
        profileFrag_screen_init(v,width,height);
        return v;
    }

    private void profileFrag_screen_init(View rootLayout, int screenWidthDp, int screenHeightDp)
    {
        ImageView profileFragImage=rootLayout.findViewById(R.id.profileFragImage);
        profileFragImage.setLayoutParams(new RelativeLayout.LayoutParams(screenWidthDp,(int)(screenHeightDp*0.30)));
        RelativeLayout.LayoutParams param1=(RelativeLayout.LayoutParams)profileFragImage.getLayoutParams();
        param1.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        param1.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        profileFragImage.setLayoutParams(param1);
        profileFragImage.setImageResource(R.drawable.profileimage3);

        TextView profileFragNamme=rootLayout.findViewById(R.id.profileFragNamme);
        profileFragNamme.setLayoutParams(new RelativeLayout.LayoutParams((int)(screenWidthDp*0.40),(int)(screenHeightDp*0.15)));
        RelativeLayout.LayoutParams param2=(RelativeLayout.LayoutParams)profileFragNamme.getLayoutParams();
        param2.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        profileFragNamme.setLayoutParams(param2);
        profileFragNamme.setTextSize(40);
        profileFragNamme.setText(Global.getUserName());


    }

}
