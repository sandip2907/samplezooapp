package com.example.samplezooapp.di


import android.app.Application
import com.example.samplezooapp.BuildConfig
import com.example.samplezooapp.config.EnvironmentManager
import com.example.samplezooapp.features.search_creatures.data.source.remote.ServiceConfig
import com.example.samplezooapp.features.search_creatures.data.source.remote.ServiceConfig.ZOO_SERVICE_RETROFIT_INSTANCE
import com.example.samplezooapp.features.search_creatures.data.source.remote.ZooService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ZooServiceModule {


    @Singleton
    @Provides
    @Named(ZOO_SERVICE_RETROFIT_INSTANCE)
    fun provideRetrofitInstance(application: Application, envManager: EnvironmentManager, moshi: Moshi, interceptor: Interceptor): Retrofit {
        val headers = mapOf(envManager.API_KEY to BuildConfig.API_KEY)
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .cache(
                Cache(
                    File(application.cacheDir, envManager.CACHE_CONTROL),
                    10L * 1024L * 1024L
                )
            )
            .addNetworkInterceptor(interceptor)
            .build()

        httpClient.addInterceptor(logging)
        httpClient.addInterceptor(getHeadersInterceptor(headers))
        return Retrofit.Builder()
            .baseUrl(ServiceConfig.getBaseUrl(envManager.getEnvironmentType()))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient.build())
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response: Response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(10, TimeUnit.MINUTES)
                .build()
            response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
    }


    @Provides
    @Singleton
    fun provideMoshiKotlinJsonAdapterFactory(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideMyCustomService(@Named(ZOO_SERVICE_RETROFIT_INSTANCE) retrofit: Retrofit): ZooService{
        return retrofit.create(ZooService::class.java)
    }

    private fun getHeadersInterceptor(headers: Map<String, String>): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()

            val request = original.newBuilder()
            headers.forEach { header ->
                request.removeHeader(header.key)
                request.addHeader(header.key, header.value)
            }
            request.method(original.method, original.body)
            chain.proceed(request.build())
        }
    }
}