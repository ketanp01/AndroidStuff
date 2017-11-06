package com.example.ketanpandey.everything.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ketanpandey.everything.Global;
import com.example.ketanpandey.everything.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class blogPostFrag extends Fragment {


    EditText contentEdittext;
    EditText titleEdittext;

    public blogPostFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v= inflater.inflate(R.layout.fragment_blog_post, container, false);
        int height= Global.getmainActivityscreenHeightDp();
        int width=Global.getscreenWidthDp();
        blogPostFragment_screen_init(v,width,height);
        return v;
    }

    private void blogPostFragment_screen_init(View rootLayout, int screenWidthDp, int screenHeightDp)
    {
        RelativeLayout blogPost_topLayout= rootLayout.findViewById(R.id.blogPost_topLayout);
        blogPost_topLayout.setLayoutParams(new LinearLayout.LayoutParams(screenWidthDp,(int)(screenHeightDp*0.10)));

        ImageView userProfileImage= blogPost_topLayout.findViewById(R.id.userProfileImage);
        userProfileImage.setLayoutParams(new RelativeLayout.LayoutParams((int)(screenWidthDp*0.10),(int)(screenHeightDp*0.07)));
        userProfileImage.setImageResource(R.drawable.profileimage2);
        RelativeLayout.LayoutParams param1=(RelativeLayout.LayoutParams)userProfileImage.getLayoutParams();
        param1.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);
        param1.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        userProfileImage.setLayoutParams(param1);

        TextView userProfileName= blogPost_topLayout.findViewById(R.id.userProfileName);
        userProfileName.setLayoutParams(new RelativeLayout.LayoutParams((int)(screenWidthDp*0.25),(int)(screenHeightDp*0.08)));
        RelativeLayout.LayoutParams param2=(RelativeLayout.LayoutParams)userProfileName.getLayoutParams();
        param2.addRule(RelativeLayout.RIGHT_OF,R.id.userProfileImage);
        param2.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        userProfileName.setLayoutParams(param2);
        userProfileName.setText(Global.getUserName());

        Button postButton= blogPost_topLayout.findViewById(R.id.postButton);
        postButton.setLayoutParams(new RelativeLayout.LayoutParams((int)(screenWidthDp*0.20),(int)(screenHeightDp*0.08)));
        RelativeLayout.LayoutParams param3=(RelativeLayout.LayoutParams)postButton.getLayoutParams();
        param3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        param3.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        postButton.setLayoutParams(param3);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contentEdittext.getText().toString().isEmpty())
                    Toast.makeText(getActivity(),"Post Empty",Toast.LENGTH_SHORT).show();
                else
                {
                    String titleText="";
                    if(titleEdittext.getText().toString().isEmpty())
                    {
                        titleText="NoTitle";
                    }
                    else
                        titleText=titleEdittext.getText().toString();

//                    Global.blogTitleList.clear();
//                    Global.blogContentList.clear();
//                    Global.userNameList.clear();
                    Global.setBlogTitlesList(titleText);
                    Global.setBlogContentList(contentEdittext.getText().toString());
                    Global.setUsernameList(Global.getUserName());
                    Global.setBlogPostFlag(true);
                    Snackbar.make(v,"Done...Write Another Post!",Snackbar.LENGTH_SHORT).show();
                    titleEdittext.setText("");
                    contentEdittext.setText("");
                }
            }
        });

        int blogPost_EditTextLayout_height=(int)(screenHeightDp*0.81);
        LinearLayout blogPost_EditTextLayout= rootLayout.findViewById(R.id.blogPost_EditTextLayout);
        blogPost_EditTextLayout.setLayoutParams(new LinearLayout.LayoutParams(screenWidthDp,blogPost_EditTextLayout_height));

        titleEdittext=blogPost_EditTextLayout.findViewById(R.id.titleEdittext);
        titleEdittext.setLayoutParams(new LinearLayout.LayoutParams(screenWidthDp,(int)(blogPost_EditTextLayout_height*0.10)));

        contentEdittext=blogPost_EditTextLayout.findViewById(R.id.contentEdittext);
        contentEdittext.setLayoutParams(new LinearLayout.LayoutParams(screenWidthDp,(int)(blogPost_EditTextLayout_height*0.90)));


    }

}
