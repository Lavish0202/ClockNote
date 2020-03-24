package com.example.clocknote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class inputschedule extends AppCompatActivity {
    EditText name,from,to,date;
     Button submit;
    //FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputschedule);
        name=(EditText) findViewById(R.id.name);
        from=(EditText)findViewById(R.id.timefrom);
        to=(EditText)findViewById(R.id.timeto);
        date=(EditText)findViewById(R.id.evdate);
        submit=(Button) findViewById(R.id.inputbtn);
        reference=FirebaseDatabase.getInstance().getReference("Inputdata");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
                Intent intent=new Intent(inputschedule.this,MySchedule.class);
                startActivity(intent);

            }
        });
    }
    public void add(){
        String eventname=name.getText().toString();
        String evdate=date.getText().toString();
        String evtotime=to.getText().toString();
        String fromtime=from.getText().toString();
        if(!TextUtils.isEmpty(eventname)&&!TextUtils.isEmpty(evdate)&&!TextUtils.isEmpty(evtotime)&&!TextUtils.isEmpty(fromtime)) {
           String id=reference.push().getKey();
           Inputdata inputdata=new Inputdata(eventname,evtotime,fromtime,evdate,id);
           reference.child(id).setValue(inputdata);
           name.setText("");
            date.setText("");
            to.setText("");
            from.setText("");
            Toast.makeText(inputschedule.this, "Input successful", Toast.LENGTH_LONG).show();
            //return true;
        }
        else {
            Toast.makeText(inputschedule.this, "Value not filled or incorrect", Toast.LENGTH_LONG).show();
            //return false;
        }
    }
}
