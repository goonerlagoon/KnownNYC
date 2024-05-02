package com.example.knownnyc.util

import com.example.knownnyc.data.local.provider.AssetProvider
import com.example.knownnyc.data.local.provider.AssetsProviderImpl
import com.example.knownnyc.domain.repositories.BoroughsRepository
import com.example.knownnyc.domain.repositories.BoroughsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppProviderModule {

    //Local Assets
    @Binds
    @Singleton
    abstract fun localAssetsProvider(impl: AssetsProviderImpl) : AssetProvider

    @Binds
    @Singleton
    abstract fun boroughRepositoryProvider(impl: BoroughsRepositoryImpl) : BoroughsRepository
}