package com.example.exam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RadioExample extends AppCompatActivity {

    RadioGroup r;
    RadioButton rb;

    EditText e1, e2;

    String gender="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_example);

        e1 = findViewById(R.id.editTextText4);
        e2 = findViewById(R.id.editTextText5);
        r = findViewById(R.id.radioGroup);


    }

    public void submit(View v){
        int bid = r.getCheckedRadioButtonId();
        rb = findViewById(bid);

        if("Male".equals(rb.getText().toString())){
            gender = "Male";
        }else{
            gender = "Female";
        }

        String message = "name:"+e1.getText()+"\n Age:"+e2.getText()+"\nGender:"+gender;

        new AlertDialog.Builder(this)
                .setTitle("details")
                .setMessage(message)
                .setPositiveButton("OK",(dialog,which)->{}).show();

    }


}