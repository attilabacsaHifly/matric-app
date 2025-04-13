package com.appic.matricapp.injection

import com.appic.matricapp.common.DataCache
import com.appic.matricapp.common.DataCacheImpl
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
    fun provideDataCache(): DataCache {
        return DataCacheImpl()
    }
}
