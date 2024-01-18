package com.sku.myapplication.network;

import com.sku.myapplication.model.Post;
import com.sku.myapplication.model.UserAccount;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface ApiInterface {
    @POST("user/login")
    Call<UserAccount> loginUser(@Body UserAccount user);

    @GET("/board/posts")
    Call<List<Post>> getAllPosts();

    @GET("/board/posts/{postId}")
    Call<Post> getPostDetails(@Path("postId") int postId);
}
