package com.delivery_test.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delivery_test.database.databaseentity.FoodUiEntity
import io.reactivex.Single

/**
 * Класс для запросов всех последних спиков меню
 */
@Dao
abstract class FoodsListDao {

    @Query("SELECT * FROM mealsList")
    abstract fun getLastMealsList(): Single<MutableList<FoodUiEntity>>

    @Query("SELECT * FROM mealsList WHERE strCategory LIKE :search")
    abstract fun onSearchFood(search: String): Single<MutableList<FoodUiEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertLastFoodList(data: MutableList<FoodUiEntity>)
}