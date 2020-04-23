package com.example.mobilproje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    ArrayList<Users> UserList=new ArrayList<>();
    private ArrayList<Users> allContacts=new ArrayList<>();
    private Database database;
    RecyclerView recyclerView;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerView=findViewById(R.id.recycler_view);
        database=new Database(context);
        allContacts=database.GetUsers();
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        RecyclerAdapter customAdapter=new RecyclerAdapter(allContacts,context);
        recyclerView.setAdapter(customAdapter);

    }


}
