package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CallSms extends AppCompatActivity {

    ListView lv;
    String[] numbers = {"9495434706","9495434706","9495434706"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_sms);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,numbers);

        lv = findViewById(R.id.lv);
        lv.setAdapter(adapter);

        registerForContextMenu(lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openContextMenu(view);
            }
        });

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo ){
            super.onCreateContextMenu(menu, v, menuInfo);
            getMenuInflater().inflate(R.menu.context_menu,menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String number = numbers[info.position];

        if (item.getItemId() == R.id.action_call) {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + number));
            startActivity(callIntent);
            return true;
        } else if (item.getItemId() == R.id.action_sms) {
            Intent send_sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + number));
            startActivity(send_sms);
        } else {
            return super.onContextItemSelected(item);
        }
        return false;
    }
}





















//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
//        super.onCreateContextMenu(menu, v, menuInfo);
//        getMenuInflater().inflate(R.menu.context_menu, menu);
//    }


//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        getMenuInflater().inflate(R.menu.context_menu, menu);
//    }

//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        String number = numbers[info.position];
//
//        if (item.getItemId() == R.id.action_call) {
//            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
//            dialIntent.setData(Uri.parse("tel:" + number));
//            startActivity(dialIntent);
//            return true;
//        } else if (item.getItemId() == R.id.action_sms) {
//            // Create an intent to send an SMS
//            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + number));
//            startActivity(smsIntent);
//            return true;
//        } else {
//            return super.onContextItemSelected(item);
//        }
//    }


