package com.example.samplezooapp.di


import com.example.samplezooapp.config.EnvironmentManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideEnvironmentManager(): EnvironmentManager {
        return EnvironmentManager()
    }
}