package com.bin.app

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
@Suppress("UnnecessaryAbstractClass")
abstract class ApplicationModule {

    @Binds
    abstract fun bindContext(application: BaseApplication): Context
}
