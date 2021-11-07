package com.example.xbree;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.airbnb.lottie.LottieAnimationView;

public class Update_profile extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText name1, lname1, email1, phone1;
    Button update;
    private static int id = 1;
    int idd;
    LottieAnimationView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        v = findViewById(R.id.animm);
        email1 = findViewById(R.id.emailll);
        name1 = findViewById(R.id.nameee);
        lname1 = findViewById(R.id.lnameee);
        phone1 = findViewById(R.id.phoneee);
        update = findViewById(R.id.btn_updt);
        loadClientData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateuser(idd, email1.getText().toString(), name1.getText().toString(), lname1.getText().toString(), Integer.valueOf(phone1.getText().toString()));
                System.out.println(idd);
            }
        });

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog();
            }

        });

    }

    void showDialog() {

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.alert_dialog, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();

        alertDialog.show();

        Button acceptButton = view.findViewById(R.id.acceptButton);
        Button cancelButton = view.findViewById(R.id.cancelButton);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser();

            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

    }

    private void deleteUser() {
        sharedPreferences = getApplicationContext().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        int idu = sharedPreferences.getInt("idUser", 0);
                Intent i = new Intent(Update_profile.this, LoginActivity.class);
                startActivity(i);
            }

    private void updateuser(int id, final String email, final String name, final String prenom, final int tel) {

    }

    public void loadClientData() {
        sharedPreferences = getApplicationContext().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        String idus = sharedPreferences.getString("EmailUser", "");
    }
}