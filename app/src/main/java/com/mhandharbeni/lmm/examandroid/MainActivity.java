package com.mhandharbeni.lmm.examandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mhandharbeni.lmm.examandroid.intentexam.IntentActivity;
import com.mhandharbeni.lmm.examandroid.sqliteexam.BiodataActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnIntentExam, btnSqliteExam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetch_component();
        fetch_listener();
    }

    private void fetch_component(){
        btnIntentExam = findViewById(R.id.btnIntentExam);
        btnSqliteExam = findViewById(R.id.btnSqliteExam);
    }

    private void fetch_listener(){
        btnIntentExam.setOnClickListener(this);
        btnSqliteExam.setOnClickListener(this);
    }

    private void call_activity(Class clas){
        Intent intent = new Intent(this, clas);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnIntentExam){
            call_activity(IntentActivity.class);
        }else if(view.getId() == R.id.btnSqliteExam){
            call_activity(BiodataActivity.class);
        }
    }
}
