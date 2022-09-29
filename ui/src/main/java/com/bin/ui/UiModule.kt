package com.bin.ui

import androidx.lifecycle.ViewModel
import com.bin.presentation.DataViewModel
import com.bin.presentation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
abstract class UiModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(DataViewModel::class)
//    abstract fun bindDataViewModel(viewModel: DataViewModel): ViewModel
}