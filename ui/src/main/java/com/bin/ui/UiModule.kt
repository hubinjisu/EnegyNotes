package com.bin.ui

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import com.bin.presentation.AppRouteNavigator
import com.bin.presentation.PresentationModule
import com.bin.presentation.RouteNavigator

@Module
@InstallIn(SingletonComponent::class)
abstract class UiModule {

}