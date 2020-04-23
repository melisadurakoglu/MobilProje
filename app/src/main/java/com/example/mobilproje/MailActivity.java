package com.example.mobilproje;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MailActivity extends AppCompatActivity {

    EditText edittextGonderilecek, edittextKonu, edittextAciklama;
    Button buttonGonder, buttonAttach;
    Uri URI;  //attachment için.
    private static final int PICK_FROM_GALLERY = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        edittextGonderilecek=(EditText)findViewById(R.id.edittextGonderilecek);
        edittextKonu=(EditText)findViewById(R.id.edittextKonu);
        edittextAciklama=(EditText)findViewById(R.id.edittextAciklama);
        buttonGonder=(Button)findViewById(R.id.buttonGonder);
        buttonAttach=(Button)findViewById(R.id.buttonAttach);

        buttonAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //attachment için.
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra("return-data", true); //veri döndürüp maile ekliyor.
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);
            }
        });

        buttonGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailAdres = edittextGonderilecek.getText().toString();
                String mailKonu = edittextKonu.getText().toString();
                String aciklama = edittextAciklama.getText().toString();
                final Intent email = new Intent(android.content.Intent.ACTION_SEND); //attachment için.
                email.setType("plain/text");

                // Intent email = new Intent(Intent.ACTION_SEND);
                //   email.setType(HTTP.PLAIN_TEXT_TYPE);
                //Intent email = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + mailAdres));
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{mailAdres}); //dizi şeklinde verdik.
                email.putExtra(Intent.EXTRA_SUBJECT, mailKonu);
                email.putExtra(Intent.EXTRA_TEXT, aciklama);
                if (URI != null) {
                    email.putExtra(Intent.EXTRA_STREAM, URI); //attachment için.
                }

                //email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Mail Gönderildi."));
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //attachment için.
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            URI = data.getData();
        }
    }
}