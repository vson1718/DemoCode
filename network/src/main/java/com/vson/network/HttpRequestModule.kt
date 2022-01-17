package com.vson.network

import com.vson.network.annotation.BindOkHttp
import com.vson.network.request.OkHttpRequest
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class HttpRequestModule {
    @BindOkHttp
    @Binds
    @Singleton
    abstract fun bindOkHttp(okHttpRequest: OkHttpRequest): IHttpRequest

}