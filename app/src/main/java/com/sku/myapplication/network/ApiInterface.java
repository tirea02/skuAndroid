package com.sku.myapplication.network;

import com.sku.myapplication.model.UserAccount;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("user/login")
    Call<UserAccount> loginUser(@Body UserAccount user);
}
