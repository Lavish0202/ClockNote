package com.example.clocknote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.suke.widget.SwitchButton;

public class MainActivity extends AppCompatActivity {

    SwitchButton switchButton;
    SharedPreferences.Editor prefeditor;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchButton=findViewById(R.id.swithButton);
        prefeditor=PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit();
        preferences=PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(MainActivity.this,"App is Enabled",Toast.LENGTH_SHORT).show();

                    prefeditor.putString("checked","True");
                    prefeditor.apply();
                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this,"Disabled",Toast.LENGTH_SHORT).show();
                    prefeditor.putString("checked","False");
                    prefeditor.apply();
                }

            }
        });
        if(preferences.getString("checked","no").equals("True")){
            switchButton.setChecked(true);
        }
        else{
            switchButton.setChecked(false);
        }


    }

}
