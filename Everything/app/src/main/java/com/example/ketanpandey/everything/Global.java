package com.example.ketanpandey.everything;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KetanPandey on 03-11-2017.
 */

public class Global extends Application
{

    public static int screenWidthDp;
    public static int screenHeightDp;
    private static String window_id;
    private static String user_availability_status;
    private static int mainActivityScreenheight;
    public static List<String> blogTitleList = new ArrayList<>();
    public static List<String> blogContentList = new ArrayList<>();
    public static List<String> userNameList = new ArrayList<>();
    public static boolean blogPostedFlag=false;
    public static String username;
    public static Context context;

    public static int getscreenWidthDp() { return screenWidthDp ; }
    public static void setscreenWidthDp(int a) {screenWidthDp=a; }
    public static int getscreenHeightDp() { return screenHeightDp ; }
    public static void setscreenHeightDp(int a) {screenHeightDp=a; }
    public static String getWindow_id() {		return window_id;	}
    public static void setWindow_id(String windowid) {		window_id = windowid;	}
    public static void setUserAvailabilityStatus(String s){user_availability_status=s;}
    public static String getUser_availability_status(){return user_availability_status;}
    public static int getmainActivityscreenHeightDp() { return mainActivityScreenheight ; }
    public static void setmainActivityscreenHeightDp(int a) {mainActivityScreenheight=a; }
    public static void setBlogPostFlag(boolean b){blogPostedFlag=b;}
    public static boolean getBlogPostFlag(){return blogPostedFlag;}
    public static String getUserName() { return username; }
    public static void setUserName(String user) { username = user; }
    public static void setContext(Context c){context=c;}
    public static Context getContext(){return context;}

    public static String[] getBlogTitlesList()
    {
        String myArray[] =new String[blogTitleList.size()];
        for (int x = 0;  x< blogTitleList.size(); x++) {myArray[x] =blogTitleList.get(x);}
        return myArray;
    }
    public static void setBlogTitlesList(String titles)
    {
        blogTitleList.add(titles);
    }

    public static String[] getBlogContentList()
    {
        String myArray[] =new String[blogContentList.size()];
        for (int x = 0;  x< blogContentList.size(); x++) {myArray[x] =blogContentList.get(x);}
        return myArray;
    }
    public static void setBlogContentList(String contents)
    {
        blogContentList.add(contents);
    }

    public static String[] getUsernameList()
    {
        String myArray[] =new String[userNameList.size()];
        for (int x = 0;  x< userNameList.size(); x++) {myArray[x] =userNameList.get(x);}
        return myArray;
    }
    public static void setUsernameList(String usernames)
    {
        userNameList.add(usernames);
    }

}
