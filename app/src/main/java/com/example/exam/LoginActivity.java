package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText e1, e2;
    TextView t1;

    int attempt=0;

    final int max = 2;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1 = findViewById(R.id.username);
        e2 = findViewById(R.id.pass);
        t1 = findViewById(R.id.textView);
        btn = findViewById(R.id.button8);

    }

    public void login(View v){
        String username = e1.getText().toString();
        String password = e2.getText().toString();
        if(TextUtils.isEmpty(e1.getText().toString()) || TextUtils.isEmpty(e2.getText().toString())){
            Toast.makeText(this, "Username and password is required", Toast.LENGTH_SHORT).show();
        }
        if("test".equals(username) &&  "Abc@1234".equals(password)){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
        }else{
            attempt++;
            if(attempt >= max){
                btn.setEnabled(false);
                t1.setText("Reached maximum failed attempts");
            }
        }

        if(!isValidPassword(password)){
            Toast.makeText(this, "incorrect password",Toast.LENGTH_LONG).show();
        }

    }

    public boolean isValidPassword(String password){
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@=%&+=#])(?=\\s+$).{8,}$");
    }
}