package com.delivery_test.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.delivery_test.database.databaseentity.FoodTypeUiEntity
import com.delivery_test.database.databaseentity.FoodUiEntity

@Database(
    entities = [FoodTypeUiEntity::class, FoodUiEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DataBaseCreator : RoomDatabase() {

    abstract fun getFoodTypesDataBase(): FoodsTypeDao

    abstract fun getFoodListDataBase(): FoodsListDao
}