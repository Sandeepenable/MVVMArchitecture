package com.example.mvvmarchitecture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.mvvmarchitecture.Adapters.RecyclerViewAdapter;
import com.example.mvvmarchitecture.Models.User;
import com.example.mvvmarchitecture.ViewModels.MainViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {


    Context context=MainActivity.this;
    MainViewModel viewModel;
    RecyclerView mvvmRecycler;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mvvmRecycler=findViewById(R.id.mvvm_recycler);
        recyclerViewAdapter=new RecyclerViewAdapter();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this);
        mvvmRecycler.setLayoutManager(manager);
        mvvmRecycler.setAdapter(recyclerViewAdapter);

        viewModel= ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getUserLiveData().observe(this,userListUpdateObserver);
    }

    Observer<ArrayList<User>> userListUpdateObserver = new Observer<ArrayList<User>>() {
        @Override
        public void onChanged(ArrayList<User> userArrayList) {
            recyclerViewAdapter.updateUserList(userArrayList);
        }
    };
}