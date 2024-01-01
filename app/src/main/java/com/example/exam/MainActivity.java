package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = getIntent().getStringExtra("username");
        if (username != null && !username.isEmpty()) {
            Toast.makeText(this, username, Toast.LENGTH_LONG).show();
        }


    }


    public void go(View v) {
        Intent intent = new Intent(this, DbActivity.class);
        startActivity(intent);
    }
}