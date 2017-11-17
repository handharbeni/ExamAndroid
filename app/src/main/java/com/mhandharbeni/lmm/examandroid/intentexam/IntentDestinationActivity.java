package com.mhandharbeni.lmm.examandroid.intentexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mhandharbeni.lmm.examandroid.R;

/**
 * Created by LMM on 11/17/2017.
 */

public class IntentDestinationActivity extends AppCompatActivity {
    private TextView txtName, txtEmail, txtPhone;
    private String name, email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetch_bundle();
        setContentView(R.layout.intent_layout_destination_activity);
        fetch_component();
        fetch_data();
    }

    private void fetch_bundle(){
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        email = bundle.getString("email");
        phone = bundle.getString("phone");
    }
    private void fetch_component(){
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
    }
    private void fetch_data(){
        txtName.setText(name);
        txtEmail.setText(email);
        txtPhone.setText(phone);
    }

}
