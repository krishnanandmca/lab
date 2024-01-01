package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class NormMenuActivity extends AppCompatActivity {

    EditText e1,e2;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_norm_menu);

        e1 = findViewById(R.id.Number1);
        e2 = findViewById(R.id.Number2);
        t1 = findViewById(R.id.result);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.normal_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int i = item.getItemId();
        int n = Integer.parseInt(e1.getText().toString());
        int m = Integer.parseInt(e2.getText().toString());
        int res =0;
        if(i == R.id.add){
                res = n+m;
                t1.setText(String.valueOf(res));
        }else if(i == R.id.sub){
            res = n-m;
            t1.setText(String.valueOf(res));
        }
        else if(i == R.id.mul){
            res = n*m;
            t1.setText(String.valueOf(res));
        }else if(i == R.id.div){
            res = n/m;
            t1.setText(String.valueOf(res));
        }
        return true;
    }
}