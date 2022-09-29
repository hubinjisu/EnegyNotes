package com.bin.presentation

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ViewModelComponent::class)
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(DataViewModel::class)
    abstract fun bindDataViewModel(viewModel: DataViewModel): ViewModel
}