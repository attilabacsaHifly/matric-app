package com.appic.matricapp.injection

import com.appic.matricapp.interactor.HighwayVignetteInteractor
import com.appic.matricapp.interactor.HighwayVignetteInteractorImpl
import com.appic.matricapp.network.api.HighwayVignetteAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorModule {

    @Provides
    @Singleton
    fun provideHighwayVignetteInteractor(api: HighwayVignetteAPI): HighwayVignetteInteractor {
        return HighwayVignetteInteractorImpl(api)
    }
}
