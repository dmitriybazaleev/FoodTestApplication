package com.delivery_test.di

import android.content.Context
import androidx.room.Room
import com.delivery_test.base.Constants
import com.delivery_test.base.DeliveryApp
import com.delivery_test.database.DataBaseCreator
import com.delivery_test.database.FoodsListDao
import com.delivery_test.database.FoodsTypeDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    fun provideDaoFoodTypes(dataBase: DataBaseCreator): FoodsTypeDao {
        return dataBase.getFoodTypesDataBase()
    }

    @Provides
    fun provideDaoAllFoods(dataBase: DataBaseCreator): FoodsListDao {
        return dataBase.getFoodListDataBase()
    }

    @Provides
    @Singleton
    fun provideDBCreator(app: DeliveryApp): DataBaseCreator {
        return Room.databaseBuilder(app.applicationContext, DataBaseCreator::class.java, Constants.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}