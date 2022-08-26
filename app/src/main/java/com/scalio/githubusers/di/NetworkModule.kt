package com.scalio.githubusers.di

import com.google.gson.Gson
import com.scalio.githubusers.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

		@Provides
		@Singleton
		fun providesGson() = Gson()

		@Provides
		fun createRetrofitInstance(): Retrofit {
				return Retrofit.Builder()
								.addConverterFactory(GsonConverterFactory.create())
								.baseUrl(BuildConfig.BASE_URL)
								.build()
		}


}