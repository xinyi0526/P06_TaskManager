package com.myapplicationdev.android.p06_taskmanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    ListView lv;
    TaskArrayAdapter aa;
    ArrayList<Task> tasks = new ArrayList<Task>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        lv = findViewById(R.id.lv);

        DBHelper dbh = new DBHelper(this);
        tasks = dbh.getAllTasks();

        aa = new TaskArrayAdapter(this,R.layout.row,tasks);
        lv.setAdapter(aa);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AddActivity.class);
                startActivityForResult(i,9);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == 9){
            DBHelper db = new DBHelper(MainActivity.this);
            tasks.clear();
            tasks.addAll(db.getAllTasks());
            db.close();
            lv.setAdapter(aa);
            aa.notifyDataSetChanged();

        }
    }
}
