package com.delivery_test.base

import android.app.Application
import com.delivery_test.di.ApplicationComponent
import com.delivery_test.di.ApplicationModule
import com.delivery_test.di.DaggerApplicationComponent
import com.delivery_test.network.RetrofitCreator
import com.delivery_test.network.api.FoodApi

class DeliveryApp: Application() {

    lateinit var deliveryApi: FoodApi

    companion object {
        lateinit var component: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initApi()
        initDagger()
    }

    private fun initDagger() {
        component = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }


    private fun initApi() {
        deliveryApi = RetrofitCreator().onCreateApi()
    }
}