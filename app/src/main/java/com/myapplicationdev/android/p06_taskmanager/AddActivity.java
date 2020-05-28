package com.myapplicationdev.android.p06_taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    Button btnInsert, btnCancel;
    EditText etname, etdesc, etDuration;
    int reqCode = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnInsert = findViewById(R.id.btnInsert);
        btnCancel = findViewById(R.id.btnCancel);
        etname = findViewById(R.id.etName);
        etdesc = findViewById(R.id.etDesc);
        etDuration = findViewById(R.id.etDuration);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etname.getText().toString().trim();
                String desc = etdesc.getText().toString().trim();
                String duration = etDuration.getText().toString().trim();

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, Integer.parseInt(duration));

                Intent intent = new Intent(AddActivity.this,
                        MyReceiver.class);
                intent.putExtra("name",name);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        AddActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);

                if(name.length() == 0 && desc.length() == 0){
                    return;
                }

                DBHelper dbh = new DBHelper(AddActivity.this);
                dbh.insertTask(name,desc);
                dbh.close();
                Toast.makeText(AddActivity.this,"Inserted",Toast.LENGTH_LONG).show();
                Intent i = new Intent(AddActivity.this,MainActivity.class);
                setResult(RESULT_OK,i);
                finish();


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }
}
