package com.example.ketanpandey.everything;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class signUp extends Fragment {


    String username;
    String pass;
    String passRetype;
    EditText userNameSignup;
    EditText passwordSignup;
    EditText passwordSignupRetype;

    public signUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v= inflater.inflate(R.layout.fragment_sign_up, container, false);
        int height=Global.getscreenHeightDp();
        int width=Global.getscreenWidthDp();
        signUp_screen_init(v,width,height);
        return v;
    }

    private void signUp_screen_init(View rootLayout, int screenWidthDp, int screenHeightDp)
    {
        int signupLayout_width=(int)(screenWidthDp*0.90);
        int signupLayout_height=(int)(screenHeightDp*0.30);
        LinearLayout signupLayout=rootLayout.findViewById(R.id.signupLayout);
        signupLayout.setLayoutParams(new RelativeLayout.LayoutParams(signupLayout_width,signupLayout_height));

        RelativeLayout.LayoutParams param=(RelativeLayout.LayoutParams) signupLayout.getLayoutParams();
        param.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        signupLayout.setLayoutParams(param);

        int userNameSignup_width=(int)(signupLayout_width*0.90);
        int userNameSignup_height=(int)(signupLayout_height*0.30);
         userNameSignup=signupLayout.findViewById(R.id.userNameSignup);
        userNameSignup.setLayoutParams(new LinearLayout.LayoutParams(userNameSignup_width,userNameSignup_height));
         passwordSignup=signupLayout.findViewById(R.id.passwordSignup);
        passwordSignup.setLayoutParams(new LinearLayout.LayoutParams(userNameSignup_width,userNameSignup_height));
         passwordSignupRetype=signupLayout.findViewById(R.id.passwordSignupRetype);
        passwordSignupRetype.setLayoutParams(new LinearLayout.LayoutParams(userNameSignup_width,userNameSignup_height));

        int signupButton_width=(int)(screenWidthDp*0.25);
        int signupButton_height=(int)(screenHeightDp*0.08);
        Button signupButton=rootLayout.findViewById(R.id.signupButton);
        signupButton.setLayoutParams(new RelativeLayout.LayoutParams(signupButton_width,signupButton_height));

        RelativeLayout.LayoutParams param2=(RelativeLayout.LayoutParams) signupButton.getLayoutParams();
        param2.addRule(RelativeLayout.BELOW,R.id.signupLayout);
        param2.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        signupButton.setLayoutParams(param2);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username=userNameSignup.getText().toString();
                pass=passwordSignup.getText().toString();
                passRetype=passwordSignupRetype.getText().toString();
                if(!username.isEmpty() && !pass.isEmpty() && !passRetype.isEmpty())
                    new SignupClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                else
                    Toast.makeText(getActivity(),"Enter Details",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class SignupClass extends AsyncTask<Void, Void, Void>
    {
        private int Sign_up_status;
        Service_Client_Interaction client1=new Service_Client_Interaction();
        @Override
        protected Void doInBackground(Void... arg0)
        {

                try
                {
                    Sign_up_status= client1.signup_Json(username,pass);

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //start
            if (Sign_up_status==1 )
            {
                userNameSignup.setText("");
                passwordSignup.setText("");
                passwordSignupRetype.setText("");
                Global.setUserName(username);
                getActivity().finish();
                startActivity(new Intent(getActivity().getApplicationContext(),MainActivity.class));

            }
            else if(Sign_up_status==8 || Sign_up_status==0)
            {
                Toast.makeText(getActivity(),"User Already Exist",Toast.LENGTH_SHORT).show();
            }
        }
    }


}
