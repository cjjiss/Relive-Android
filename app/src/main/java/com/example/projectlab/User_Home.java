package com.example.projectlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class User_Home extends AppCompatActivity {
    private  int SMS_PERMISSION_CODE = 1;
    Button b1;
    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        b1 = findViewById(R.id.button);
        aSwitch = findViewById(R.id.switch1);


        Boolean switchState = aSwitch.isChecked();

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                   // aSwitch.setText("enabled");

                    //permission already granted
                    if (ContextCompat.checkSelfPermission(User_Home.this,
                            Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(User_Home.this, "Permssion already granted!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        requestSmsPermission();
                    }

                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(User_Home.this,Accident.class);
                            startActivity(i);
                        }
                    });
                }
                else {
                    //aSwitch.setText("not enabled");

                }
            }
        });

    }
    private void requestSmsPermission(){
        //explains why we need this permission
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS)){

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to enable the emergency alert in case of any occurrence of accident")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(User_Home.this,new String[]{Manifest.permission.SEND_SMS},SMS_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();
        }
        else {
            //dont have to show
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},SMS_PERMISSION_CODE);
        }
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {

        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
        }
    }
}