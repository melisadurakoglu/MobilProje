package com.example.mobilproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edittextUsername,edittextPassword;
    TextView textViewUsername,textViewPassword;
    Button buttonLogin, buttonRegister;
    Context context=this;
    Database db=new Database(context);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittextUsername=(EditText)findViewById(R.id.edittextUsername);
        edittextPassword=(EditText)findViewById(R.id.edittextPassword);
        buttonLogin=(Button)findViewById(R.id.buttonLogin);
        buttonRegister=(Button)findViewById(R.id.buttonRegister);
        textViewUsername=(TextView)findViewById(R.id.textviewUsername);
        textViewPassword=(TextView)findViewById(R.id.textviewPassword);

        db.getWritableDatabase();

       /* db.onUpgrade(db.getWritableDatabase(),1,2);
        // db.AddUser(new Users("Melisaa","12345",R.mipmap.user));
       // db.AddUser(new Users("Bihter","12345",R.mipmap.user));
      db.AddUser(new Users("TestUser","12345",R.mipmap.user)); */

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUserName=edittextUsername.getText().toString();
                String getPassword=edittextPassword.getText().toString();
                Boolean control=db.login(getUserName, getPassword); //databasede yer alan kullanıcı adı ve şifrenin kontrolü.

                if (control==true)
                {
                    Intent myIntent=new Intent(context,MenuActivity.class);
                    MainActivity.this.startActivity(myIntent); //menu activitiyi açtık.
                    Toast.makeText(context,"Başarılı!",Toast.LENGTH_SHORT).show();
                }
                else {

                    Toast.makeText(context,"Kullanıcı adı veya şifre yanlış.",Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUserName=edittextUsername.getText().toString();
                String getPassword=edittextPassword.getText().toString();

                if (getUserName.matches("") && getPassword.matches("")) //alanlar boş girilirse uyarı verecektir.
                {
                    Toast.makeText(context,"Lütfen alanları doldurunuz.",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    db.AddUser(new Users(getUserName, getPassword, R.mipmap.user)); //alanlar doluysa db'ye kayıt eklenecektir.
                    Toast.makeText(context,"Kayıt başarılı, giriş yapınız.",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}


