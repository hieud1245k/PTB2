package com.hieuminh.exercise3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class detailActivity extends AppCompatActivity {
    private TextView text1, text2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        connectView();
        Intent intent = getIntent();
        if(intent != null) {
            text1.setText(intent.getStringExtra("text1"));
            text2.setText(intent.getStringExtra("text2"));
        }
    }
    private void connectView() {
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
    }
}
