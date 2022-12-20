package com.example.mvvmarchitecture.ViewModels;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmarchitecture.Classes.AllApis;
import com.example.mvvmarchitecture.Interfaces.ApiCalls;
import com.example.mvvmarchitecture.Models.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends ViewModel {

    MutableLiveData<ArrayList<User>> userLiveData;
    ArrayList<User> userArrayList;

    public MainViewModel() {
        userLiveData = new MutableLiveData<>();
        init();
    }


    public MutableLiveData<ArrayList<User>> getUserLiveData() {
        return userLiveData;
    }

    public void init() {
        populateList();

    }

    private void populateList() {

         getLeaveDetails();

    }


    private void getLeaveDetails() {

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(AllApis.LEAVES_DETAILS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiCalls mapiCall = mRetrofit.create(ApiCalls.class);
        Call<JsonObject> mCall = mapiCall.createPosts("696", "2002", "Android",
                "7348529f04859b882837087ea7e9413b", "2022");

        mCall.enqueue(new Callback<JsonObject>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject jsonObject = response.body();
                userArrayList = new ArrayList<>();

                try {
                    JsonArray jsonArray = response.body().getAsJsonArray("data");
                    String balanceInformation = jsonArray.get(0).getAsJsonObject().get("leaves").toString();

                    JSONObject jObject = new JSONObject(balanceInformation);
                    Iterator<String> keys = jObject.keys();

                    while (keys.hasNext()) {
                        String key = keys.next();

                        Log.d("list key", key);
                        if (jObject.get(key) instanceof JSONObject) {
                            JSONObject innerJObject = jObject.getJSONObject(key);
                            String id = innerJObject.getString("id");
                            String earnedLeaves = innerJObject.getString("EarnedLeaves");
                            String applied = innerJObject.getString("applied");
                            String approved = innerJObject.getString("approved");
                            String rejected = innerJObject.getString("rejected");
                            String available = innerJObject.getString("available");
                            String pending = innerJObject.getString("Pending");
                            Log.v("details", "id = " + id + ", " + "id = " + id + ", " + "earnedLeaves = " + earnedLeaves + ", " + "applied = " + applied + ", " + "approved = " + approved + ", "
                                    + "rejected = " + rejected + ", " + "available = " + available + ", " + "Pending= " + pending);


                            User user=new User();
                            user.setApplied(earnedLeaves);
                            user.setApproved(available);


                            userArrayList.add(user);


                        }


                    }

                    userLiveData.setValue(userArrayList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

//                Toast.makeText(PieChartSample.this, "getting api error", Toast.LENGTH_SHORT).show();
                Log.d("Errror", call.toString());
            }
        });
    }

}
