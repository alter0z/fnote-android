package com.fiberlance.fnote.notes.di

import com.fiberlance.fnote.BuildConfig
import com.fiberlance.fnote.notes.data.api.NoteApi
import com.fiberlance.fnote.notes.data.repository.NoteRepositoryImpl
import com.fiberlance.fnote.notes.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor =
            if(BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) else HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.NONE)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteApi(retrofit: Retrofit): NoteApi = retrofit.create(NoteApi::class.java)

    @Provides
    @Singleton
    fun provideNoteRepository(api: NoteApi): NoteRepository = NoteRepositoryImpl(api)
}