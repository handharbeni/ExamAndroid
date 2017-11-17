package com.mhandharbeni.lmm.examandroid.intentexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mhandharbeni.lmm.examandroid.R;

/**
 * Created by LMM on 11/17/2017.
 */

public class IntentActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtName, txtEmail, txtPhone;
    private Button btnClickHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intent_layout_activity);

        fetch_component();
        fetch_listener();
    }

    private void fetch_component(){
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);

        btnClickHere = findViewById(R.id.btnClickHere);
    }

    private void fetch_listener(){
        btnClickHere.setOnClickListener(this);
    }

    private void call_destination(){
        Bundle bundle = new Bundle();
        bundle.putString("name", txtName.getText().toString());
        bundle.putString("email", txtEmail.getText().toString());
        bundle.putString("phone", txtPhone.getText().toString());

        Intent intent = new Intent(this, IntentDestinationActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private Boolean validate_input(EditText editText){
        return !editText.getText().toString().isEmpty();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnClickHere){
            if (validate_input(txtName) && validate_input(txtEmail) && validate_input(txtPhone)){
                call_destination();
            }
        }
    }
}
