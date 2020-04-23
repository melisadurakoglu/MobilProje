package com.example.mobilproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    Button home, sendMail, shared, sensor, users;
    TextView txthome,txtmail, txtshared, txtsensors, txtuserlist;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        home=(Button)findViewById(R.id.home);
        sendMail=(Button)findViewById(R.id.sendMail);
        shared=(Button)findViewById(R.id.shared);
        sensor=(Button)findViewById(R.id.sensor);
        users=(Button)findViewById(R.id.users);

        /*
        txthome=(TextView)findViewById(R.id.txthome);
        txtmail=(TextView)findViewById(R.id.txtmail);
        txtshared=(TextView)findViewById(R.id.txtshared);
        txtsensors=(TextView)findViewById(R.id.txtsensors);
        txtuserlist=(TextView)findViewById(R.id.txtuserlist);

         */

        home=(Button)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MainActivity.class);
                MenuActivity.this.startActivity(intent);

            }
        });

        sendMail=(Button)findViewById(R.id.sendMail);
        sendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MailActivity.class);
                MenuActivity.this.startActivity(intent);

            }
        });

        shared=(Button)findViewById(R.id.shared);
        shared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SharedActivity.class);
                MenuActivity.this.startActivity(intent);

            }
        });

       sensor=(Button)findViewById(R.id.sensor);
        sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SensorActivity.class);
                MenuActivity.this.startActivity(intent);

            }
        });
        users=(Button)findViewById(R.id.users);
        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, UserListActivity.class);
                MenuActivity.this.startActivity(intent);

            }
        });

    }
}
