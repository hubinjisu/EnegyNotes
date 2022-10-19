package com.bin.presentation

import androidx.lifecycle.ViewModel
import com.bin.presentation.viewmodel.AboutViewModel
import com.bin.presentation.viewmodel.NotesViewModel
import com.bin.presentation.viewmodel.RecordViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoMap

@Module
@InstallIn(ViewModelComponent::class)
abstract class PresentationModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRouteNavigator(routeNavigator: AppRouteNavigator): RouteNavigator

    @Binds
    @IntoMap
    @ViewModelKey(RecordViewModel::class)
    abstract fun bindRecodeViewModel(viewModel: RecordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NotesViewModel::class)
    abstract fun bindSummaryViewModel(viewModel: NotesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AboutViewModel::class)
    abstract fun bindAboutViewModel(viewModel: AboutViewModel): ViewModel
}