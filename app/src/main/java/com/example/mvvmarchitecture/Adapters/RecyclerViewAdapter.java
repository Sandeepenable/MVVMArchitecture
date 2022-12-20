package com.example.mvvmarchitecture.Adapters;

import android.content.Context;
import android.icu.lang.UScript;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmarchitecture.Models.User;
import com.example.mvvmarchitecture.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> userArrayList;

    public  RecyclerViewAdapter() {
        this.userArrayList=new ArrayList<User>();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_data,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {

        User user=userArrayList.get(position);
        holder.title.setText(user.getApplied());
        holder.desc.setText(user.getApproved());

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public void updateUserList(final ArrayList<User> userArrayList){

        this.userArrayList.clear();
        this.userArrayList=userArrayList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title,desc;
        ImageView imgIcon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.txtView_title);
            desc=itemView.findViewById(R.id.txtView_description);
            imgIcon=itemView.findViewById(R.id.imgView_icon);

        }
    }
}
