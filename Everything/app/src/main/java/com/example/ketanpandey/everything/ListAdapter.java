package com.example.ketanpandey.everything;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by KetanPandey on 04-11-2017.
 */

public class ListAdapter extends ArrayAdapter<String>
{

    final private Context context;
    final private String[] titlearray;
    final private String[] contentArr;
    final private String[] usernames;
    private boolean likeFlag=false;
    private boolean dislikeFlag=true;
    private boolean followFlag=false;
    private boolean unfollowFlag=true;

    public ListAdapter(@NonNull Context context, String[] titles,String[] contents,String[] users)
    {
        super(context, R.layout.customdrawer,titles);
        this.context=context;
        this.titlearray=titles;
        this.contentArr=contents;
        this.usernames=users;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=null;
        row=inflater.inflate(R.layout.customdrawer, parent,false);

        listView_Item_init(row,Global.getscreenWidthDp(),Global.getmainActivityscreenHeightDp(),position);

        return row;
    }

    private void listView_Item_init(View v, int screenWidth, int screenHeight, final int position)
    {
        RelativeLayout title_follow_layout= v.findViewById(R.id.title_follow_layout);
        title_follow_layout.setLayoutParams(new LinearLayout.LayoutParams(screenWidth,(int)(screenHeight*0.07)));

        TextView titleTextview= title_follow_layout.findViewById(R.id.titleTextview);
        RelativeLayout.LayoutParams param1= (RelativeLayout.LayoutParams) titleTextview.getLayoutParams();
        param1.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);
        param1.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        titleTextview.setLayoutParams(param1);
        titleTextview.setText(usernames[position]);

        int following_height=(int)(screenHeight*0.07);
        final ImageView following= title_follow_layout.findViewById(R.id.following);
        following.setLayoutParams(new RelativeLayout.LayoutParams((int)(screenWidth*0.25),(int)(following_height*0.90)));
        RelativeLayout.LayoutParams paramf= (RelativeLayout.LayoutParams) following.getLayoutParams();
        paramf.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        following.setLayoutParams(paramf);

        final Button follow= title_follow_layout.findViewById(R.id.follow);
        RelativeLayout.LayoutParams param2= (RelativeLayout.LayoutParams) follow.getLayoutParams();
        param2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        param2.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        follow.setLayoutParams(param2);
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(unfollowFlag)
                {
                    following.setImageResource(R.drawable.following);
                    follow.setText(R.string.unfollow);
                    unfollowFlag=false;
                    followFlag=true;
                }
                else if(followFlag)
                {
                    following.setImageResource(0);
                    follow.setText(R.string.follow);
                    unfollowFlag=true;
                    followFlag=false;
                }
            }
        });

        LinearLayout blogContent_layout= v.findViewById(R.id.blogContent_layout);
        blogContent_layout.setLayoutParams(new LinearLayout.LayoutParams(screenWidth,(int)(screenHeight*0.25)));

        TextView contentTextview= blogContent_layout.findViewById(R.id.contentTextview);
        if(titlearray[position].equals("NoTitle"))
            contentTextview.setText(contentArr[position]);
        else
        {
            String postString=titlearray[position]+"\n"+contentArr[position];
            contentTextview.setText(postString);
        }


        RelativeLayout bottomLayout= v.findViewById(R.id.bottomLayout);
        bottomLayout.setLayoutParams(new LinearLayout.LayoutParams(screenWidth,(int)(screenHeight*0.04)));

        LinearLayout bottomLayoutLinearLayout= bottomLayout.findViewById(R.id.bottomLayoutLinearLayout);
        bottomLayoutLinearLayout.setLayoutParams(new RelativeLayout.LayoutParams((int)(screenWidth*0.40),(int)(screenHeight*0.04)));
        RelativeLayout.LayoutParams param3= (RelativeLayout.LayoutParams) bottomLayoutLinearLayout.getLayoutParams();
        param3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        bottomLayoutLinearLayout.setLayoutParams(param3);

        int imageview_width=(int)(screenWidth*0.60);
        final ImageView like_dislike_imageview = bottomLayoutLinearLayout.findViewById(R.id.like_dislike_imageview);
        like_dislike_imageview.setLayoutParams(new LinearLayout.LayoutParams((int)(imageview_width*0.15),(int)(screenHeight*0.04)));
        like_dislike_imageview.setImageResource(R.drawable.dislike);
        like_dislike_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dislikeFlag)
                {
                    like_dislike_imageview.setImageResource(R.drawable.like);
                    dislikeFlag=false;
                    likeFlag=true;
                }
                else if(likeFlag)
                {
                    like_dislike_imageview.setImageResource(R.drawable.dislike);
                    likeFlag=false;
                    dislikeFlag=true;
                }

            }
        });

        final LinearLayout emptyLayout1 = bottomLayoutLinearLayout.findViewById(R.id.emptyLayout1);
        emptyLayout1.setLayoutParams(new LinearLayout.LayoutParams((int)(imageview_width*0.04),(int)(screenHeight*0.04)));

        final ImageView comment_imageview = bottomLayoutLinearLayout.findViewById(R.id.comment_imageview);
        comment_imageview.setLayoutParams(new LinearLayout.LayoutParams((int)(imageview_width*0.15),(int)(screenHeight*0.04)));
        comment_imageview.setImageResource(R.drawable.ic_chat);
        comment_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutinflater=LayoutInflater.from(Global.getContext());
                View dialogview=layoutinflater.inflate(R.layout.user_comments_dialog, null);
                comment_dialog_init(dialogview,Global.getscreenWidthDp(),Global.getmainActivityscreenHeightDp());
            }
        });

        final LinearLayout emptyLayout2 = bottomLayoutLinearLayout.findViewById(R.id.emptyLayout2);
        emptyLayout2.setLayoutParams(new LinearLayout.LayoutParams((int)(imageview_width*0.05),(int)(screenHeight*0.04)));

        final ImageView message_imageview = bottomLayoutLinearLayout.findViewById(R.id.message_imageview);
        message_imageview.setLayoutParams(new LinearLayout.LayoutParams((int)(imageview_width*0.15),(int)(screenHeight*0.04)));
        message_imageview.setImageResource(R.drawable.message);
        message_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LayoutInflater layoutinflater=LayoutInflater.from(Global.getContext());
                View dialogview=layoutinflater.inflate(R.layout.user_message_dialog, null);
                message_dialog_init(dialogview,Global.getscreenWidthDp(),Global.getmainActivityscreenHeightDp(),position);
            }
        });

    }

    private void comment_dialog_init(View dialogview,int screenWidthDp, int screenHeightDp) {
        // TODO Auto-generated method stub
		/*To display alert dialog box on onclick button starts here*/

        AlertDialog.Builder builder= new AlertDialog.Builder(Global.getContext());
        builder.setView(dialogview);
        final AlertDialog alert=builder.create();
        alert.setCancelable(true);
        // alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alert.show();
    	/*To display alert dialog box on onclick button starts here*/
    	/*set width and height of dialogbox  starts here*/
        int screenWidthDpDialog=(int)(screenWidthDp*0.90);
        int screenHeightDpDialog=(int)(screenHeightDp*0.25);
        alert.getWindow().setLayout(screenWidthDpDialog,screenHeightDpDialog);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//    	alert.getWindow().findViewById(R.id.user_details_layout).setLayoutParams(new FrameLayout.LayoutParams(screenWidthDpDialog,screenHeightDpDialog));
//    	alert.getWindow().findViewById(R.id.user_details_layout).setBackgroundColor(Color.GREEN);
        RelativeLayout commentRootLayout=alert.getWindow().findViewById(R.id.commentRootLayout);
        commentRootLayout.setLayoutParams(new FrameLayout.LayoutParams(screenWidthDpDialog,screenHeightDpDialog));
        commentRootLayout.setBackgroundColor(Color.WHITE);

        final EditText commentEdittext=alert.getWindow().findViewById(R.id.commentEdittext);
        commentEdittext.setLayoutParams(new RelativeLayout.LayoutParams((int)(screenWidthDpDialog*0.90),(int)(screenHeightDpDialog*0.30)));
       // commentEdittext.setBackgroundColor(Color.BLUE);
        RelativeLayout.LayoutParams param1=(RelativeLayout.LayoutParams)commentEdittext.getLayoutParams();
        param1.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        commentEdittext.setLayoutParams(param1);

        Button commentButton=alert.getWindow().findViewById(R.id.commentButton);
        commentButton.setLayoutParams(new RelativeLayout.LayoutParams((int)(screenWidthDpDialog*0.30),(int)(screenHeightDpDialog*0.30)));
        //commentButton.setBackgroundColor(Color.GREEN);
        RelativeLayout.LayoutParams param2=(RelativeLayout.LayoutParams)commentButton.getLayoutParams();
        param2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
        param2.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        commentButton.setLayoutParams(param2);
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!commentEdittext.getText().toString().isEmpty())
                {
                    Toast.makeText(Global.getContext(),"Comment Sent",Toast.LENGTH_LONG).show();
                    alert.dismiss();
                }

            }
        });



    }


    private void message_dialog_init(View dialogview,int screenWidthDp, int screenHeightDp,int position) {
        // TODO Auto-generated method stub
		/*To display alert dialog box on onclick button starts here*/

        AlertDialog.Builder builder= new AlertDialog.Builder(Global.getContext());
        builder.setView(dialogview);
        final AlertDialog alert=builder.create();
        alert.setCancelable(true);
        // alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alert.show();
    	/*To display alert dialog box on onclick button starts here*/
    	/*set width and height of dialogbox  starts here*/
        int screenWidthDpDialog=(int)(screenWidthDp*0.90);
        int screenHeightDpDialog=(int)(screenHeightDp*0.25);
        alert.getWindow().setLayout(screenWidthDpDialog,screenHeightDpDialog);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//    	alert.getWindow().findViewById(R.id.user_details_layout).setLayoutParams(new FrameLayout.LayoutParams(screenWidthDpDialog,screenHeightDpDialog));
//    	alert.getWindow().findViewById(R.id.user_details_layout).setBackgroundColor(Color.GREEN);
        RelativeLayout messageRootLayout=alert.getWindow().findViewById(R.id.messageRootLayout);
        messageRootLayout.setLayoutParams(new FrameLayout.LayoutParams(screenWidthDpDialog,screenHeightDpDialog));
        messageRootLayout.setBackgroundColor(Color.WHITE);

        final EditText messageEdittext=alert.getWindow().findViewById(R.id.messageEdittext);
        messageEdittext.setLayoutParams(new RelativeLayout.LayoutParams((int)(screenWidthDpDialog*0.90),(int)(screenHeightDpDialog*0.30)));
        // commentEdittext.setBackgroundColor(Color.BLUE);
        messageEdittext.setHint("Message "+usernames[position]);
        RelativeLayout.LayoutParams param1=(RelativeLayout.LayoutParams)messageEdittext.getLayoutParams();
        param1.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        messageEdittext.setLayoutParams(param1);

        final Button messagebutton=alert.getWindow().findViewById(R.id.messagebutton);
        messagebutton.setLayoutParams(new RelativeLayout.LayoutParams((int)(screenWidthDpDialog*0.30),(int)(screenHeightDpDialog*0.30)));
        //commentButton.setBackgroundColor(Color.GREEN);
        RelativeLayout.LayoutParams param2=(RelativeLayout.LayoutParams)messagebutton.getLayoutParams();
        param2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
        param2.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        messagebutton.setLayoutParams(param2);
        messagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!messageEdittext.getText().toString().isEmpty())
                {
                    Toast.makeText(Global.getContext(),"Message Sent",Toast.LENGTH_LONG).show();
                    alert.dismiss();
                }
            }
        });



    }


}
