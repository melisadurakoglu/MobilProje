package com.example.mobilproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SharedActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "Melisa" ;
    public static final String User = "Kullanıcı";
    public static final String Age = "Yaş";
    public static final String Height = "Boy";
    public static final String Weight="Kilo";
    public static final String Check="check";
    public static final String Check2="check2";
    EditText editTextUser,editTextAge,editTextHeight,editTextWeight;
    CheckBox checkBox,checkBox1;
    //  ToggleButton toggleButton;
    Button buttonKaydet;
    SharedPreferences sharedPreferences;
    private String Ad, Yaş,Boy,Kilo;
    private boolean checkOnOffMale,checkOnOffFemale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        editTextUser=(EditText)findViewById(R.id.editTextUser);
        editTextAge=(EditText)findViewById(R.id.editTextAge);
        editTextHeight=(EditText)findViewById(R.id.editTextHeight);
        editTextWeight=(EditText)findViewById(R.id.editTextWeight);
        buttonKaydet=(Button)findViewById(R.id.buttonKaydet);
        checkBox=(CheckBox)findViewById(R.id.checkBox);
        checkBox1=(CheckBox)findViewById(R.id.checkBox1);
        //  toggleButton=(ToggleButton)findViewById(R.id.toggleButton);

        sharedPreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences=getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(User,editTextUser.getText().toString());
                editor.putString(Age,editTextAge.getText().toString());
                editor.putString(Height,editTextHeight.getText().toString());
                editor.putString(Weight,editTextWeight.getText().toString());
                editor.putBoolean(Check,checkBox.isChecked());
                editor.putBoolean(Check2,checkBox1.isChecked());
                editor.apply();
                Toast.makeText(SharedActivity.this,"Başarılı!",Toast.LENGTH_LONG).show();
            }
        });

        SharedPreferences sharedPreferences=getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        Ad=sharedPreferences.getString(User,"");
        Yaş=sharedPreferences.getString(Age,"");
        Kilo=sharedPreferences.getString(Weight,"");
        Boy=sharedPreferences.getString(Height,"");
        checkOnOffMale=sharedPreferences.getBoolean(Check,false);
        checkOnOffFemale=sharedPreferences.getBoolean(Check2,false);

        editTextUser.setText(Ad);
        editTextAge.setText(Yaş);
        editTextHeight.setText(Boy);
        editTextWeight.setText(Kilo);
        checkBox.setChecked(checkOnOffMale);
        checkBox1.setChecked(checkOnOffFemale);

    }
}

