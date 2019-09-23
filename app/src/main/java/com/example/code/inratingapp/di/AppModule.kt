package com.example.code.inratingapp.di

import android.content.Context
import com.example.code.inratingapp.api.InRatingService
import com.example.code.inratingapp.appSet.AppSet
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var context: Context) {


    @Provides
    @Singleton
    fun provideSettings() = AppSet(context)

}