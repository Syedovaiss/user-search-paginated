package com.scalio.githubusers.features.search.di

import com.scalio.githubusers.features.search.core.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
class HttpModule {

    @Provides
    fun providesSearchAPIService(retrofit: Retrofit) = retrofit.create(SearchAPIService::class.java)
}

@InstallIn(ViewModelComponent::class)
@Module
interface SearchBinderModule {

    @Binds
    fun bindsSearchUserRepository(defaultSearchUserRepository: DefaultSearchUserRepository): SearchUserRepository

    @Binds
    fun bindSearchUserUseCase(defaultSearchUserUseCase: DefaultSearchUserUseCase): SearchUserUseCase


}
