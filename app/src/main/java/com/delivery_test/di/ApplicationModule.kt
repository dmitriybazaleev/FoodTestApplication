package com.delivery_test.di

import com.delivery_test.base.DeliveryApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule constructor(
    private val app: DeliveryApp
) {
    @Provides
    @Singleton
    fun provideApp(): DeliveryApp = app
}