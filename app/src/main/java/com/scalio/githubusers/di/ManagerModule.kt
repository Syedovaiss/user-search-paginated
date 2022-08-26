package com.scalio.githubusers.di

import com.scalio.githubusers.manager.DefaultStringResourceManager
import com.scalio.githubusers.manager.DefaultToastManager
import com.scalio.githubusers.manager.StringResourceManager
import com.scalio.githubusers.manager.ToastManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface ManagerModule {

    @Binds
    fun bindStringResourceManager(defaultStringResourceManager: DefaultStringResourceManager): StringResourceManager


    @Binds
    fun bindToastManager(defaultToastManager: DefaultToastManager): ToastManager
}