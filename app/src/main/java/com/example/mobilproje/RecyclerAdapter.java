package com.example.mobilproje;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<Users> Users=new ArrayList<>();
    ArrayList<Users> listContact=new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;
    private Database database;

    public RecyclerAdapter(ArrayList<Users> Users, Context context) {
        this.context = context;
        this.Users = Users;

        this.listContact=Users;
        database=new Database(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater=LayoutInflater.from(context);
        View v=layoutInflater.inflate(R.layout.user_layout_list,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtUsername.setText(Users.get(position).UserName);
        holder.txtPassword.setText(Users.get(position).Password);
        holder.imageView.setImageResource(Users.get(position).ImageSource);
        holder.linearLayout.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return Users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtUsername,txtPassword;
        ImageView imageView;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUsername=itemView.findViewById(R.id.txtUsername);
            txtPassword=itemView.findViewById(R.id.txtPassword);
            imageView=itemView.findViewById(R.id.image);
            linearLayout=itemView.findViewById(R.id.linear);

        }
    }
}
