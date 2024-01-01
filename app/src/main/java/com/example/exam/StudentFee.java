package com.example.exam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class StudentFee extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText e1, e2, e3,e4;
    Spinner s;

    String item = "";

    String[] courses = {"MCA", "MBA", "INMCA"};

    int fee=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_fee);

        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        e4 = findViewById(R.id.e4);
        s = findViewById(R.id.spinner);
        s.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,courses);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s.setAdapter(arrayAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = courses[position];
        if(item == "MCA"){
            fee=40000;
        }else if(item == "MBA"){
            fee=60000;
        }else if(item == "INMCA"){
            fee=38000;
        }
    }

    public void onSubmit(View v){
        String message = "id:"+e1.getText().toString()+"\n name:"+e2.getText()+"\n Address:"+e3.getText()+"\n DOB:"+e4.getText()+"\n Fee:"+String.valueOf(fee);

        new AlertDialog.Builder(this)
                .setTitle("Details")
                .setMessage(message)
                .setPositiveButton("OK",(dialog,which)->{

                })
                .show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}