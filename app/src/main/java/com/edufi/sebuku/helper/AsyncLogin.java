package com.edufi.sebuku.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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
 * Created by basisdata on 3/4/2016.
 */
public class AsyncLogin extends AsyncTask<String, Void, Void> {

    private Context context;
    private ProgressDialog dialog;

    private String email;
    private String password;
    private String response;

    private final String HOST = "http://sebuku.vidukasi.com";

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
        dialog.setMessage("Logging in...");
        dialog.show();
    }

    @Override
    protected Void doInBackground(String... params) {
        setEmail(params[0]);
        setPassword(params[1]);
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(HOST + "/nbcuvuyswdkajdh");
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("email", getEmail()));
            nameValuePairs.add(new BasicNameValuePair("password", getPassword()));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            setResponse(EntityUtils.toString(entity));

            JSONObject reader = new JSONObject(getResponse());
            String status = reader.getString("status");
            Log.i("Response", getResponse());
            Log.i("Status", status);
        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        dialog.dismiss();
    }
}
