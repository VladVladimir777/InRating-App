package com.example.code.inratingapp.di

import com.example.code.inratingapp.mainFragment.ViewModelMainFragment
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelModule::class])
@ViewModelScope
interface ViewModelComponent {

    fun inject(viewModelMainFragment: ViewModelMainFragment)

}