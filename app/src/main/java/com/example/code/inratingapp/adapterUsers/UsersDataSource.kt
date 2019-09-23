package com.example.code.inratingapp.adapterUsers

import androidx.paging.PageKeyedDataSource
import com.example.code.inratingapp.api.InRatingService
import com.example.code.inratingapp.api.apiData.Data
import com.example.code.inratingapp.api.apiData.PostsUsersResponse
import com.example.code.inratingapp.appSet.AppSet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersDataSource(
    private val appSet: AppSet,
    private val value: String,
    private val inRatingService: InRatingService
) :
    PageKeyedDataSource<Int, Data>() {

    private val firstPage = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Data>
    ) {
        inRatingService.loadPostsValue(
            appSet.currentPostId,
            value,
            object : Callback<PostsUsersResponse> {
                override fun onResponse(
                    call: Call<PostsUsersResponse>,
                    response: Response<PostsUsersResponse>
                ) {
                    response.body()?.data?.let { callback.onResult(it, null, firstPage + 1) }
                }

                override fun onFailure(call: Call<PostsUsersResponse>, t: Throwable) {

                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Data>) {
        inRatingService.loadPostsValue(
            appSet.currentPostId,
            value,
            object : Callback<PostsUsersResponse> {
                override fun onResponse(
                    call: Call<PostsUsersResponse>,
                    response: Response<PostsUsersResponse>
                ) {
                    response.body()?.data?.let { callback.onResult(it, params.key + 1) }
                }

                override fun onFailure(call: Call<PostsUsersResponse>, t: Throwable) {

                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Data>) {
        inRatingService.loadPostsValue(
            appSet.currentPostId,
            value,
            object : Callback<PostsUsersResponse> {
                override fun onResponse(
                    call: Call<PostsUsersResponse>,
                    response: Response<PostsUsersResponse>
                ) {
                    val adjacentKey = (if (params.key > 1) params.key - 1 else null)?.toInt()
                    response.body()?.data?.let { callback.onResult(it, adjacentKey) }
                }

                override fun onFailure(call: Call<PostsUsersResponse>, t: Throwable) {

                }
            })
    }

}