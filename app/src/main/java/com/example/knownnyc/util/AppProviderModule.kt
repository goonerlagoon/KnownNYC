package com.example.knownnyc.util

import com.example.knownnyc.data.local.provider.AssetsProvider
import com.example.knownnyc.data.local.provider.AssetsProviderImpl
import com.example.knownnyc.data.remote.repositories.NYCParksRepository
import com.example.knownnyc.data.remote.repositories.NYCParksRepositoryImpl
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
    abstract fun localAssetsProvider(impl: AssetsProviderImpl) : AssetsProvider

    @Binds
    @Singleton
    abstract fun boroughRepositoryProvider(impl: BoroughsRepositoryImpl) : BoroughsRepository

    @Binds
    @Singleton
    abstract fun nycParksRepositoryProvider(impl: NYCParksRepositoryImpl) : NYCParksRepository
}