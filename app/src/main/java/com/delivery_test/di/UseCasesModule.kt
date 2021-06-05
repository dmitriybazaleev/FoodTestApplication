package com.delivery_test.di

import com.delivery_test.database.FoodsListDao
import com.delivery_test.database.FoodsTypeDao
import com.delivery_test.network.api.FoodApi
import com.delivery_test.usecase.HomeUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCasesModule {

    @Provides
    fun getHomeUseCase(
        api: FoodApi,
        foodsListDao: FoodsListDao,
        foodsTypeDao: FoodsTypeDao
    ): HomeUseCase {
        return HomeUseCase(api, foodsListDao, foodsTypeDao)
    }
}