package com.delivery_test.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delivery_test.database.databaseentity.FoodTypeUiEntity
import io.reactivex.Single

/**
 * Класс для запросов последного типа питания
 */
@Dao
abstract class FoodsTypeDao {

    @Query("SELECT * FROM mealsType")
    abstract fun getAllTypesOfFood(): Single<MutableList<FoodTypeUiEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertLastTypeOfFood(
        types: MutableList<FoodTypeUiEntity>
    )
}