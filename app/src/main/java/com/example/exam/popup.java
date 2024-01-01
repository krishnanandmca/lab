package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

public class popup extends AppCompatActivity {
    Button btn;
    ConstraintLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        btn = findViewById(R.id.button6);
        layout = findViewById(R.id.layout);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(popup.this,btn);
                popup.getMenuInflater().inflate(R.menu.popup_menu,popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int i = item.getItemId();
                        if(i == R.id.red){
                            layout.setBackgroundResource(R.color.red);
                        }else if(i == R.id.green){
                            layout.setBackgroundResource(R.color.green);
                        }else{
                            layout.setBackgroundResource(R.color.blue);
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });

    }
}

//
//
//
//        btn = findViewById(R.id.button6);
//                layout = findViewById(R.id.layout);
//
//                btn.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        PopupMenu menu = new PopupMenu(popup.this, btn);
//        menu.getMenuInflater().inflate(R.menu.popup_menu,menu.getMenu());
//        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//@Override
//public boolean onMenuItemClick(MenuItem item) {
//        int i = item.getItemId();
//        if(i == R.id.red){
//        layout.setBackgroundResource(R.color.red);
//        }else if(i == R.id.green){
//        layout.setBackgroundResource(R.color.green);
//        }else{
//        layout.setBackgroundResource(R.color.blue);
//        }
//        return true;
//        }
//        });
//        menu.show();
//        }
//        });