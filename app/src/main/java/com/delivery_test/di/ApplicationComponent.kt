package com.delivery_test.di

import com.delivery_test.base.DeliveryApp
import com.delivery_test.database.DataBaseCreator
import com.delivery_test.network.RetrofitCreator
import com.delivery_test.viewmodel.HomeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RetrofitCreator::class,
        ApplicationModule::class,
        UseCasesModule::class,
        DataBaseModule::class
    ]
)
interface ApplicationComponent {

    fun inject(app: DeliveryApp)
    fun inject(app: HomeViewModel)

}