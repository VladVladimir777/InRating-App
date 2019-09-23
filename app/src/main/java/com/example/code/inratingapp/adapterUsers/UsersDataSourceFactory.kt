package com.example.code.inratingapp.adapterUsers

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.code.inratingapp.api.InRatingService
import com.example.code.inratingapp.api.apiData.Data
import com.example.code.inratingapp.appSet.AppSet

class UsersDataSourceFactory(
    private val appSet: AppSet,
    private val value: String,
    private val inRatingService: InRatingService) :
    DataSource.Factory<Int, Data>() {

    var itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Data>>()


    override fun create(): DataSource<Int, Data> {
        val newsDataSource = UsersDataSource(appSet, value, inRatingService)
        itemLiveDataSource.postValue(newsDataSource)
        return newsDataSource
    }

}