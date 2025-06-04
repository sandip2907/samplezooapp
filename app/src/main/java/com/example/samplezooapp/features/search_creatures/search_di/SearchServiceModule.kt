package com.example.samplezooapp.features.search_creatures.search_di

import com.example.composesampleapp.di.IoDispatcher
import com.example.samplezooapp.features.search_creatures.data.source.DefaultZooRepository
import com.example.samplezooapp.features.search_creatures.data.source.remote.ZooService
import com.example.samplezooapp.features.search_creatures.domain.repository.ZooRepository
import com.example.samplezooapp.features.search_creatures.domain.usecase.GetAnimalsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchServiceModule{

    @Provides
    @Singleton
    fun provideZooRepository(remoteZooDataSource: ZooService, @IoDispatcher dispatcher: CoroutineDispatcher): ZooRepository {
        return DefaultZooRepository(remoteZooDataSource,dispatcher)
    }

    @Provides
    @Singleton
    fun providesGetAnimalsUseCase(repository: ZooRepository): GetAnimalsUseCase {
        return GetAnimalsUseCase(repository)
    }
}