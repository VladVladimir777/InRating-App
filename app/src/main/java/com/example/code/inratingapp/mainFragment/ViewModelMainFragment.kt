package com.example.code.inratingapp.mainFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.code.inratingapp.adapterUsers.UsersDataSourceFactory
import com.example.code.inratingapp.api.*
import com.example.code.inratingapp.api.apiData.Data
import com.example.code.inratingapp.appSet.AppSet
import javax.inject.Inject

class ViewModelMainFragment(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var appSet: AppSet
    @Inject
    lateinit var inRatingService: InRatingService
    private var dataLikes: LiveData<PagedList<Data>>
    private var dataReposts: LiveData<PagedList<Data>>
    private var dataCommentators: LiveData<PagedList<Data>>
    private var dataMentions: LiveData<PagedList<Data>>
    private var arrayFactories = ArrayList<UsersDataSourceFactory>()


    init {

        (application as com.example.code.inratingapp.Application).viewModelComponent.inject(this)

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .build()

        val dataSourceFactoryLikes = UsersDataSourceFactory(appSet, LIKES_VALUE, inRatingService)
        dataLikes = LivePagedListBuilder(dataSourceFactoryLikes, pagedListConfig).build()
        arrayFactories.add(dataSourceFactoryLikes)

        val repostsFactoryLikes = UsersDataSourceFactory(appSet, REPOSTS_VALUE, inRatingService)
        dataReposts = LivePagedListBuilder(repostsFactoryLikes, pagedListConfig).build()
        arrayFactories.add(repostsFactoryLikes)

        val commentatorsFactoryLikes =
            UsersDataSourceFactory(appSet, COMMENTATORS_VALUE, inRatingService)
        dataCommentators = LivePagedListBuilder(commentatorsFactoryLikes, pagedListConfig).build()
        arrayFactories.add(commentatorsFactoryLikes)

        val mentionsFactoryLikes = UsersDataSourceFactory(appSet, MENTIONS_VALUE, inRatingService)
        dataMentions = LivePagedListBuilder(mentionsFactoryLikes, pagedListConfig).build()
        arrayFactories.add(mentionsFactoryLikes)

    }

    fun getDataLikes(): LiveData<PagedList<Data>> {
        return dataLikes
    }

    fun getDataReposts(): LiveData<PagedList<Data>> {
        return dataReposts
    }

    fun getDataCommentators(): LiveData<PagedList<Data>> {
        return dataCommentators
    }

    fun getDataMentions(): LiveData<PagedList<Data>> {
        return dataMentions
    }


    fun invalidateData() {
        arrayFactories.forEach { it.itemLiveDataSource.value?.invalidate() }
    }

}