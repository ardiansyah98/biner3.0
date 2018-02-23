package com.ilkom.biner.biner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button loginButton;

    public static final String myPrefs = "MyPrefs" ;
    public static final String status = "isLogin";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedpreferences = getSharedPreferences(myPrefs, Context.MODE_PRIVATE);


        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        loginButton = findViewById(R.id.btnLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedpreferences.edit();

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (username.equals("biner") && password.equals("tiga")){
                    //buat pindah dari mainactivity ke home
                    editor.putBoolean(status, true);
                    editor.commit();
                    Intent pindahKe = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(pindahKe);
                } else {
                    editor.putBoolean(status, false);
                    editor.commit();
                    Toast.makeText(LoginActivity.this, "username atau password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
