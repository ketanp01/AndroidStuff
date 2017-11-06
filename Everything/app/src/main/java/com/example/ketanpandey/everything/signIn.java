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
public class signIn extends Fragment {

    String username;
    String pass;
    EditText userName;
    EditText password;

    public signIn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v= inflater.inflate(R.layout.fragment_sign_in, container, false);
        int height=Global.getscreenHeightDp();
        int width=Global.getscreenWidthDp();
        signIn_screen_init(v,width,height);
        return v;
    }

    private void signIn_screen_init(View rootLayout, int screenWidthDp, int screenHeightDp)
    {
        int loginLayout_width=(int)(screenWidthDp*0.90);
        int loginLayout_height=(int)(screenHeightDp*0.30);
        LinearLayout loginLayout=rootLayout.findViewById(R.id.loginLayout);
        loginLayout.setLayoutParams(new RelativeLayout.LayoutParams(loginLayout_width,loginLayout_height));

        RelativeLayout.LayoutParams param=(RelativeLayout.LayoutParams) loginLayout.getLayoutParams();
        param.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        loginLayout.setLayoutParams(param);

        int userName_width=(int)(loginLayout_width*0.90);
        int userName_height=(int)(loginLayout_height*0.30);
        userName=loginLayout.findViewById(R.id.userName);
        userName.setLayoutParams(new LinearLayout.LayoutParams(userName_width,userName_height));
        password=loginLayout.findViewById(R.id.pass);
        password.setLayoutParams(new LinearLayout.LayoutParams(userName_width,userName_height));

        int loginButton_width=(int)(screenWidthDp*0.25);
        int loginButton_height=(int)(screenHeightDp*0.08);
        Button loginButton=rootLayout.findViewById(R.id.loginButton);
        loginButton.setLayoutParams(new RelativeLayout.LayoutParams(loginButton_width,loginButton_height));

        RelativeLayout.LayoutParams param2=(RelativeLayout.LayoutParams) loginButton.getLayoutParams();
        param2.addRule(RelativeLayout.BELOW,R.id.loginLayout);
        param2.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        loginButton.setLayoutParams(param2);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Global.getUser_availability_status().equals("flagNotExist_NotLogin"))
                {
                    Toast.makeText(getActivity(),"User Does not Exist",Toast.LENGTH_SHORT).show();
                }
                else if(Global.getUser_availability_status().equals("flagExist_Login"))
                {

                    username=userName.getText().toString();
                    pass=password.getText().toString();
                    if(!username.isEmpty() && !pass.isEmpty())
                        new CheckUserCredentialClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    else
                        Toast.makeText(getActivity(),"Enter Details",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class CheckUserCredentialClass extends AsyncTask<Void, Void, Void>
    {
        private int userCredential_status;
        Service_Client_Interaction client=new Service_Client_Interaction();
        @Override
        protected Void doInBackground(Void... arg0)
        {

            userCredential_status= client.checkUserCredential_Json(username,pass);
            //start
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if(userCredential_status==1)
            {
                userName.setText("");
                password.setText("");
                Global.setUserName(username);
                getActivity().finish();
                startActivity(new Intent(getActivity().getApplicationContext(),MainActivity.class));
            }
            else if(userCredential_status==8)
            {
                Toast.makeText(getActivity(),"Incorrect Details",Toast.LENGTH_SHORT).show();
            }

        }

    }


}
