package com.example.code.inratingapp.api

import com.example.code.inratingapp.api.apiData.PostsUsersResponse
import com.example.code.inratingapp.api.apiData.PostsResponse
import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface InRatingApi {

    // Get posts
    @Headers(
        "Content-Type:application/json",
        "Accept:application/json",
        "Authorization:Bearer $API_TOKEN"
    )
    @POST("users/posts/get")
    fun getPost(@Query("id") id: Int): Call<PostsResponse>


    // Get values
    @Headers(
        "Content-Type:application/json",
        "Accept:application/json",
        "Authorization:Bearer $API_TOKEN"
    )
    @POST("users/posts/{value}/all")
    fun getPostsValue(@Path("value") value: String, @Query("id") id: String): Call<PostsUsersResponse>

}