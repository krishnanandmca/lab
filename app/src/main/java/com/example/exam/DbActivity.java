
package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.Buffer;

public class DbActivity extends AppCompatActivity {

    SQLiteDatabase db = null;
    EditText  e2, e3,e4 ,search;

    int recordId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        db = openOrCreateDatabase("Database", MODE_PRIVATE,null);


        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        e4 = findViewById(R.id.e4);
        search = findViewById(R.id.search);
    }

    public void button(View v){
        if(db.isOpen()){
                String sql = "CREATE TABLE IF NOT EXISTS EMPIN(id INTEGER PRIMARY KEY AUTOINCREMENT ,name VARCHAR(20), address VARCHAR(10), dept VARCHAR(10) )";
            db.execSQL(sql);

            Toast.makeText(this,"table created",Toast.LENGTH_SHORT).show();


            String name = e2.getText().toString();
            String add = e3.getText().toString();
            String dept = e4.getText().toString();

            String insertSql = "INSERT INTO EMPIN(name, address, dept) VALUES('"+name+"','"+add+"','"+dept+"')";

            try {
                db.execSQL(insertSql);
                Toast.makeText(this,"inserted sucessfully",Toast.LENGTH_SHORT).show();
            }catch(SQLException e){
                Toast.makeText(this,String.valueOf(e),Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void display(View v){
        LinearLayout linearLayout = findViewById(R.id.linearLayout); // Assuming your parent layout is a LinearLayout
        linearLayout.removeAllViews();
        try {
            String viewQry = "SELECT * from EMPIN";
            Cursor c = db.rawQuery(viewQry, null);

            if (c.getCount() == 0) {
                Toast.makeText(this, "no record", Toast.LENGTH_LONG).show();
            } else {
                while (c.moveToNext()) {
                    // Create a new LinearLayout for each record
                    LinearLayout rowLayout = new LinearLayout(this);
                    rowLayout.setOrientation(LinearLayout.HORIZONTAL);


                    // Create a new TextView for each record
                    TextView tv = new TextView(this);
                    // Append record details to the buffer

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("EmpName:" + c.getString(0) + "\n");

                    buffer.append("EmpName:" + c.getString(1) + "\n");
                    buffer.append("Email:" + c.getString(2) + "\n");
                    buffer.append("phone:" + c.getString(3) + "\n");

                    // Set the text in the TextView
                    tv.setText(buffer.toString());

                    // Create a new Edit button for each record
                    Button editButton = new Button(this);
                    editButton.setText("Edit");

                   // Assuming the primary key is in the first column, adjust accordingly
                    // Set an OnClickListener to handle the edit action
                    editButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Retrieve the record ID from the button's tag
                            recordId = (int) v.getTag();
                            String qry = "SELECT * FROM EMPIN WHERE id ="+recordId;

                            try{
                                Cursor c = db.rawQuery(qry,null);
                                if(c.moveToFirst()){
                                    e2.setText(c.getString(1));
                                    e3.setText(c.getString(2));
                                    e4.setText(c.getString(3));
                                }
                                Toast.makeText(getApplicationContext(),"fetched Data",Toast.LENGTH_SHORT).show();


                            }catch(SQLException e){
                                Log.d("selecterror",String.valueOf(e));
                            }
                            // Implement your logic to handle the edit action for the specific record
                            // For example, you can open an edit activity with the selected record details
                            // You can pass the recordId to the next activity to identify the record to be edited
                            Toast.makeText(getApplicationContext(), "Edit record with ID: " + recordId, Toast.LENGTH_SHORT).show();
                        }
                    });

                    Button deleteButton = new Button(this);
                    deleteButton.setText("Delete");
                    // Set a tag to identify which record this button corresponds to
                    deleteButton.setTag(c.getInt(0));


                    deleteButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int id = (int) v.getTag();
                            String sql = "DELETE FROM EMPIN WHERE id="+id;
                            try{
                                db.execSQL(sql);
                                Toast.makeText(DbActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }catch(SQLException e){
                                Log.d("deleteerror",String.valueOf(e));
                            }
                        }
                    });

                    // Add the TextView and Edit button to the LinearLayout
                    rowLayout.addView(tv);
                    rowLayout.addView(editButton);
                    rowLayout.addView(deleteButton);

                    // Add the LinearLayout to the parent layout
                    linearLayout.addView(rowLayout);
                }
            }
        } catch (Exception e) {
            Log.d("error", String.valueOf(e));
            Toast.makeText(getApplicationContext(), String.valueOf(e), Toast.LENGTH_SHORT).show();
        }


    }

    public void update(View v){
        String qry = "UPDATE EMPIN SET name = '"+e2.getText().toString()+"', address='"+e3.getText().toString()+"',dept='"+e4.getText().toString()+"' WHERE id="+recordId;

        Toast.makeText(getApplicationContext(), "update success", Toast.LENGTH_LONG).show();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        try{
            db.execSQL(qry);
        }catch(SQLException e){
            Log.d("updateerror",String.valueOf(e));
        }
    }

    public void search(View v){


    LinearLayout lv = findViewById(R.id.linearLayout);
    lv.setOrientation(LinearLayout.VERTICAL);

    lv.removeAllViews();

    int searchId = Integer.parseInt(search.getText().toString());
        try{
            String qry = "SELECT * from EMPIN Where id="+searchId;
            Cursor c = db.rawQuery(qry,null);

            TextView tv = new TextView(this);


            if(c.getCount() == 0){
                Toast.makeText(this, "no record",Toast.LENGTH_SHORT).show();
            }else{
                if(c.moveToFirst()){
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("id:"+c.getString(0).toString()+"\n");
                    buffer.append("name:"+c.getString(1).toString()+"\n");
                    buffer.append("address:"+c.getString(2).toString()+"\n");
                    buffer.append("dept:"+c.getString(3).toString()+"\n");

                    tv.setText(buffer.toString());
                    lv.addView(tv);

                    buffer.setLength(0);
                }
            }

        }catch(SQLException e){
            Log.d("searchError",String.valueOf(e));
        }
    }


}