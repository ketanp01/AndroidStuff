package com.example.ketanpandey.everything;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

class NetworkThread extends HandlerThread {

    private Handler mWorkerHandler;

    public NetworkThread(String name) {
        super(name);
    }

    @Override
    protected void onLooperPrepared() {
        mWorkerHandler = new Handler(getLooper());
    }

    public void postTask(Runnable task){
        mWorkerHandler.post(task);
    }
}

public class SplashScreen extends Activity {

    /** Duration of wait **/
   // private final int SPLASH_DISPLAY_LENGTH = 5000;

    int screenWidthDp;
    int screenHeightDp;
    ImageView loadingImageview;
    RelativeLayout splashScreenRootLayout;

    //Handler variable initialization
    static  boolean handler_internet_connection_status=false;
    Timer timer=null;
    Handler myhandler_internet_connection_status=new Handler();
    private NetworkThread mNetworkThread;
    Runnable myupdateresults_internet_connection_status;
    boolean checkNetConnectionFlag=true;

    AsyncTask<Void, Void, Void> checkUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*to make display full screen starts (it requires uses-permission : ACCESS_NETWORK_STATE */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/*to make display full screen ends*/

        setContentView(R.layout.activity_splash_screen);

        //to get screen width and height of real device starts
        Point size=new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        screenWidthDp=size.x;
        screenHeightDp=size.y;

        Global.setscreenHeightDp(screenHeightDp);
        Global.setscreenWidthDp(screenWidthDp);
           /* finding the window or device ID starts   */
       // Global.setWindow_id(GetWindowId());
        GetWindowId();
	/* finding the window or device ID ends   */

//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run() {
//                /* Create an Intent that will start the Menu-Activity. */
//                startActivity(new Intent(SplashScreen.this,UserAuthentication.class));
//                finish();
//            }
//        }, SPLASH_DISPLAY_LENGTH);

        //Toast.makeText(getApplicationContext(),GetWindowId(),Toast.LENGTH_LONG).show();


        mNetworkThread = new NetworkThread("NetworkThread");
        myupdateresults_internet_connection_status = new Runnable() {
            @Override
            public void run()
            {
                myhandler_internet_connection_status.post(new Runnable() {
                    @Override
                    public void run()
                    {
                        if(checkNetConnectionFlag)
                        {

                            handler_internet_connection_status= get_Internet_Connection_Status();
                            if(handler_internet_connection_status)
                            {
                                checkUser=new CheckUserAvailabilityClass().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                                checkNetConnectionFlag=false;
                            }
                            else
                            {
                                Toast.makeText(getApplication(),"Check Your Internet Connection",Toast.LENGTH_SHORT).show();
                            }

                        }



                    }
                });


            }
        };

        int delay=4000;//delay for 4 sec
        int period=4000; //repeat every 4 sec
        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run()
            {
                mNetworkThread.postTask(myupdateresults_internet_connection_status);
            }
        },delay,period);

    }

    @Override
    protected void onResume() {
        super.onResume();

        splashScreenRootLayout=findViewById(R.id.splashScreenRootLayout);
        splashScreenRootLayout.setBackgroundResource(R.drawable.backgroundsplash);
       // splashScreenRootLayout.setAlpha(0.5f);
        ImageView splashScreenTitleImage=splashScreenRootLayout.findViewById(R.id.splashScreenTitleImage);
        int splashScreenTitleImageWidth=(int)(screenWidthDp);
        int splashScreenTitleImageHeight=(int)(screenHeightDp*0.40);
        splashScreenTitleImage.setLayoutParams(new RelativeLayout.LayoutParams(splashScreenTitleImageWidth,splashScreenTitleImageHeight));
        splashScreenTitleImage.setBackgroundResource(R.drawable.everythingapplogo);
        splashScreenTitleImage.setScaleType(ImageView.ScaleType.FIT_XY);

        RelativeLayout.LayoutParams param=(RelativeLayout.LayoutParams) splashScreenTitleImage.getLayoutParams();
        param.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        splashScreenTitleImage.setLayoutParams(param);

        loadingImageview=splashScreenRootLayout.findViewById(R.id.loadingImageview);
        int loadingImageview_width=(int)(screenWidthDp*0.28);
        int loadingImageview_height=(int)(screenHeightDp*0.08);
        loadingImageview.setLayoutParams(new RelativeLayout.LayoutParams(loadingImageview_width,loadingImageview_height));
        loadingImageview.setBackgroundResource(R.drawable.loading);
        //loadingImageview.setScaleType(ImageView.ScaleType.FIT_XY);

        RelativeLayout.LayoutParams param2=(RelativeLayout.LayoutParams) loadingImageview.getLayoutParams();
        param2.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        param2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
        loadingImageview.setLayoutParams(param2);



    }


    public boolean get_Internet_Connection_Status()
    {
        boolean internet_connection_status=false;

        try {
            ConnectivityManager cManager=(ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo=cManager.getActiveNetworkInfo();

            if(nInfo!=null && nInfo.isConnected())
            {
                internet_connection_status=true;
            }
            else
            {
                internet_connection_status=false;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
//        try
//        {
//            if(internet_connection_status==false)
//            {
//
//
//            }
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }


        return internet_connection_status;


    }

    public void GetWindowId()
    {
       int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        try{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                if(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
                }
            }
           else if (permissionCheck != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        String deviceId="";
        String tmDevice="", tmSerial="", androidId="";

        switch (requestCode) {
            case 1:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //TODO
                }
                break;

            default:
                break;
        }

        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        deviceId = deviceUuid.toString();
        Global.setWindow_id(deviceId);
    }

    @Override
    protected void onStart() {
        super.onStart();
        boolean mWorkerThread_status=mNetworkThread.isAlive();


        if(!mWorkerThread_status)
        {
            try
            {
                mNetworkThread.start();
                mNetworkThread.onLooperPrepared();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }



    public class CheckUserAvailabilityClass extends AsyncTask<Void, Void, Void>
    {
        private String user_availability_status;
        private String openApp="";
        private String username="";
        Service_Client_Interaction client=new Service_Client_Interaction();
        @Override
        protected Void doInBackground(Void... arg0)
        {

            user_availability_status= client.login_Json(Global.getWindow_id());
            Global.setUserAvailabilityStatus(user_availability_status);

            String[] words=user_availability_status.split("_");
            openApp=words[0];
            username=words[1];
            //start



            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if(user_availability_status.equals("flagExist_Login") || user_availability_status.equals("flagNotExist_NotLogin") )
            {
                startActivity(new Intent(SplashScreen.this,UserAuthentication.class));
                finish();
            }
            else if(openApp.equals("opneApp"))
            {

                Global.setUserName(username);
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();

            }

        }

    }
}

