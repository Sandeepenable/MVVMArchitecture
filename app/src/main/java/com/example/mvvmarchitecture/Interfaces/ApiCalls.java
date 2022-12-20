package com.example.mvvmarchitecture.Interfaces;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiCalls {

        @FormUrlEncoded
        @POST("get_LeaveDetails")
        Call<JsonObject> createPosts(@Field("empId") String empId,
                                     @Field("companyId") String companyId,
                                     @Field("device") String device,
                                     @Field("authkey") String authkey,
                                     @Field("year") String year);
}
