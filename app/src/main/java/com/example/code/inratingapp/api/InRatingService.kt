package com.example.code.inratingapp.api

import com.example.code.inratingapp.api.apiData.PostsUsersResponse
import com.example.code.inratingapp.api.apiData.PostsResponse
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InRatingService {

    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

        .build()
    private val getNewsApi: InRatingApi = retrofit.create(InRatingApi::class.java)


    fun loadPost(id: Int, callback: Callback<PostsResponse>) {
        getNewsApi.getPost(id).enqueue(callback)
    }

    fun loadPostsValue(id: String, value: String, callback: Callback<PostsUsersResponse>) {
        getNewsApi.getPostsValue(value, id).enqueue(callback)
    }

}