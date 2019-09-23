package com.example.code.inratingapp.di

import com.example.code.inratingapp.api.InRatingService
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    @ViewModelScope
    fun provideInRatingService() = InRatingService()

}