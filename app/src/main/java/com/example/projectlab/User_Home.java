package com.example.projectlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class User_Home extends AppCompatActivity {
    Button b1;
    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        b1 = findViewById(R.id.button);
        aSwitch = findViewById(R.id.switch1);

        Boolean switchState = aSwitch.isChecked();



        if (switchState == true)
        {

        }
        else
        {

        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(User_Home.this,Accident.class);
                startActivity(i);
            }
        });


    }
}