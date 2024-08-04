package com.bitflyer.github.client.domain.di

import com.bitflyer.github.client.BuildConfig
import com.bitflyer.github.client.data.api.GithubApi
import com.bitflyer.github.client.data.service.RepoRemoteDataSource
import com.bitflyer.github.client.data.service.RepoRemoteDataSourceImpl
import com.bitflyer.github.client.data.service.UserRemoteDataSource
import com.bitflyer.github.client.data.service.UserRemoteDataSourceImpl
import com.bitflyer.github.client.data.repository.RepoRepository
import com.bitflyer.github.client.data.repository.RepoRepositoryImpl
import com.bitflyer.github.client.data.repository.UserRepository
import com.bitflyer.github.client.data.repository.UserRepositoryImpl
import com.bitflyer.github.client.domain.interceptor.HttpHeaderInterceptor
import com.bitflyer.github.client.domain.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Named("http-header-interceptor")
    fun provideHttpHeaderInterceptor(): Interceptor {
        return HttpHeaderInterceptor()
    }

    @Provides
    @Named("http-logging-interceptor")
    fun provideHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    fun provideOkHttpClient(
        @Named("http-header-interceptor") httpHeaderInterceptor: Interceptor,
        @Named("http-logging-interceptor") httpLoggingInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpHeaderInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideGithubService(okHttpClient: OkHttpClient): GithubApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.HTTP_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApi::class.java)
    }

    @Provides
    fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository {
        return userRepositoryImpl
    }

    @Provides
    fun provideRepoRepository(repoRepositoryImpl: RepoRepositoryImpl): RepoRepository {
        return repoRepositoryImpl
    }

    @Provides
    fun provideUserRemoteDataSource(userRemoteDataSourceImpl: UserRemoteDataSourceImpl): UserRemoteDataSource {
        return userRemoteDataSourceImpl
    }

    @Provides
    fun provideRepoRemoteDataSource(repoRemoteDataSourceImpl: RepoRemoteDataSourceImpl): RepoRemoteDataSource {
        return repoRemoteDataSourceImpl
    }
}
