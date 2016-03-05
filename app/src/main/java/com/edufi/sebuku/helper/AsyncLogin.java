package com.edufi.sebuku.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import com.edufi.sebuku.activity.MainActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by habibfikri on 3/4/2016.
 */
public class AsyncLogin extends AsyncTask<String, Void, Void> {

    private Context context;
    private ProgressDialog dialog;

    private String email;
    private String password;
    private String response;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AsyncLogin(Context context) {
        this.context = context;
        this.dialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        dialog.setTitle("Login");
        dialog.setMessage("Mohon tunggu...");
        dialog.show();
    }

    @Override
    protected Void doInBackground(String... params) {
        setEmail(params[0]);
        setPassword(params[1]);
        Handler handler = new Handler(context.getMainLooper());
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(Constant.HOST + "/nbcuvuyswdkajdh");
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("email", getEmail()));
            nameValuePairs.add(new BasicNameValuePair("password", getPassword()));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            setResponse(EntityUtils.toString(entity));

            JSONObject reader = new JSONObject(getResponse());
            String status = reader.getString("status");
            if (status.equals("true")) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, Constant.GREETINGS, Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, Constant.LOGINFAILED, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        dialog.dismiss();
        try {
            JSONObject reader = new JSONObject(getResponse());
            if (reader.getString("status").equals("true")) {
                Session session = new Session();
                session.setUser_id(reader.getInt("user_id"));
                session.setFullname(reader.getString("fullname"));
                session.setEmail(reader.getString("email"));
                session.setToken(reader.getString("token"));
                session.setStatus(true);

                DBHandler db = new DBHandler(context);
                db.login(session);

                context.startActivity(new Intent(context, MainActivity.class));
            }
        } catch (Exception ex) {
            Handler handler = new Handler(context.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, Constant.ERRORMSG, Toast.LENGTH_LONG).show();
                }
            });
            ex.printStackTrace();
        }
    }
}
