package com.edufi.sebuku.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.edufi.sebuku.R;
import com.edufi.sebuku.helper.AsyncLogin;

/**
 * Created by habibfikri on 3/4/2016.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

                AsyncLogin login = new AsyncLogin(LoginActivity.this);
                login.execute(email, password);
            }
        });
    }
}
