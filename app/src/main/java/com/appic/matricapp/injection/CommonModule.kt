package com.appic.matricapp.injection

import com.appic.matricapp.common.Cache
import com.appic.matricapp.common.CacheImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Provides
    @Singleton
    fun provideCache(): Cache {
        return CacheImpl()
    }
}
