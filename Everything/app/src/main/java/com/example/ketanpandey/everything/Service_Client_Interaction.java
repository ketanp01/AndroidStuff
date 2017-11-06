package com.example.ketanpandey.everything;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by KetanPandey on 03-11-2017.
 */

public class Service_Client_Interaction
{
    static JSONObject objJSON;
    private String IPAddressURL="http://pokerpatti.com/jd/Service.svc";
    private String signIn_status;
    private int signup_status;
    private int logOut_status;

    public String login_Json(String deviceId)
    {

        String s=IPAddressURL+ "/json/logInBlog/"+deviceId+"";

        JSONObject jsonObject= func(s);

        try {
            signIn_status=jsonObject.getString("logInBlogResult");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signIn_status;
    }

    public int signup_Json(String username, String pass) {
        // TODO Auto-generated method stub
        String s=IPAddressURL+ "/json/signUpBlog/"+username+"/"+pass+"/"+Global.getWindow_id()+"";

        JSONObject jsonObject= func(s);

        try {
            signup_status=jsonObject.getInt("signUpBlogResult");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return signup_status;


    }

    public int logout_Json(String username) {
        // TODO Auto-generated method stub
        String s=IPAddressURL+ "/json/logOutBlog/"+username+"";

        JSONObject jsonObject= func(s);

        try {
            logOut_status=jsonObject.getInt("logOutBlogResult");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return logOut_status;


    }

    public int checkUserCredential_Json(String username, String pass) {
        // TODO Auto-generated method stub
        String s=IPAddressURL+ "/json/checkUserCredential/"+username+"/"+pass+"";

        JSONObject jsonObject= func(s);

        try {
            signup_status=jsonObject.getInt("checkUserCredentialResult");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return signup_status;


    }
    public static JSONObject func(String url)
    {


        try
        {    HttpGet request=new HttpGet(url);
            request.setHeader("Accept","application/json");
            request.setHeader("Content-type","application/json");
            @SuppressWarnings("deprecation")
            DefaultHttpClient httpClient=new DefaultHttpClient();
            HttpResponse response=httpClient.execute(request);
            HttpEntity responseEntity=response.getEntity();
            char[] buffer=new char[(int) responseEntity.getContentLength()];
            InputStream stream=responseEntity.getContent();
            InputStreamReader reader=new InputStreamReader(stream);
            reader.read(buffer);
            stream.close();
            objJSON =new JSONObject(new String(buffer));

        }catch(Exception e){
            android.util.Log.i("loi Roi :",e.toString());
        }

        return objJSON;
    }

}
