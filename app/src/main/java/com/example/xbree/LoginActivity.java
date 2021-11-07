package com.example.xbree;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.xbree.Entities.User;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    SharedPreferences sharedPreferences;
    private TextView tvLogin;
    Button Go;
    CheckBox check;
    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvLogin = findViewById(R.id.tvLogin);
        Go = findViewById(R.id.login_btn);
        email = findViewById(R.id.emailEdit);
        password = findViewById(R.id.passwordEdit);
        check = findViewById(R.id.tvremember);

        //sharedPreferences
        sharedPreferences = getSharedPreferences("testt", Context.MODE_PRIVATE);
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                email.setText(sharedPreferences.getString("test", ""));
                password.setText(sharedPreferences.getString("test1", ""));
            }
        });

        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString();
                String p = password.getText().toString();
                if (e.equals("")) {
                    email.setError("email is empty");
                }
                if (p.equals("")) {
                    password.setError("password is empty");
                    //Toast.makeText(LoginActivity.this, "Check Your Entries!", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("test", e);
                    editor.putString("test1", p);
                    editor.apply();
                   // loginUser(e, p);
                    Intent i=new Intent(LoginActivity.this,Accueil.class);
                    startActivity(i);
                }
            }
        });
    }

    }