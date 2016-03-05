package com.edufi.sebuku.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edufi.sebuku.R;
import com.edufi.sebuku.helper.AsyncLogin;
import com.edufi.sebuku.helper.Constant;
import com.edufi.sebuku.helper.DBHandler;

/**
 * Created by habibfikri on 3/4/2016.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHandler db = new DBHandler(this);
        if (db.getSession().isStatus()) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            setContentView(R.layout.activity_login);

            final EditText editEmail = (EditText) findViewById(R.id.edit_email);
            final EditText editPassword = (EditText) findViewById(R.id.edit_password);
            editPassword.setTypeface(Typeface.DEFAULT);
            editPassword.setTransformationMethod(new PasswordTransformationMethod());

            final Button btnLogin = (Button) findViewById(R.id.btn_login);
            btnLogin.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == event.ACTION_DOWN) {
                        btnLogin.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    } else {
                        btnLogin.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    }
                    return false;
                }
            });
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = editEmail.getText().toString();
                    String password = editPassword.getText().toString();
                    if (email.equals("") || password.equals("")) {
                        Toast.makeText(LoginActivity.this, Constant.BLANKWARN, Toast.LENGTH_SHORT).show();
                    } else {
                        new AsyncLogin(LoginActivity.this).execute(email, password);
                    }
                }
            });
        }
    }
}
