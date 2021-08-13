package com.delivery_test.usecase

import com.delivery_test.database.FoodsListDao
import com.delivery_test.database.FoodsTypeDao
import com.delivery_test.network.apimodel.FoodUiModel
import com.delivery_test.network.apimodel.FoodTypeUiModel
import com.delivery_test.database.databaseentity.FoodTypeUiEntity
import com.delivery_test.database.databaseentity.FoodUiEntity
import com.delivery_test.network.api.FoodApi
import io.reactivex.Single
import javax.inject.Inject

/**
 * UseCase служит для запросов базы данных
 * И служит для запросов данных еды
 * Будет использоваться в HomeViewModel
 */
class HomeUseCase @Inject  constructor(
    private val api: FoodApi,
    private val foodsListDao: FoodsListDao,
    private val foodsTypeDao: FoodsTypeDao
) {

    fun getAllFoodTypes(): Single<FoodTypeUiModel> {
        return api.getAllMealTypes()
    }

    fun getAllMeals(mealName: String): Single<FoodUiModel> {
        return api.getCurrentMeal(mealName)
    }

    fun getAllLastFoodTypesFromDb(): Single<MutableList<FoodTypeUiEntity>> {
        return foodsTypeDao.getAllTypesOfFood()
    }

    fun getAllLastFoodsFromDb(): Single<MutableList<FoodUiEntity>> {
        return foodsListDao.getLastMealsList()
    }

    fun onSearchFoodFromDb(search: String): Single<MutableList<FoodUiEntity>> {
        return foodsListDao.onSearchFood(search)
    }

    fun insertFoodsListToDb(
        foodsList: MutableList<FoodUiEntity>
    ): Single<MutableList<FoodUiEntity>> {
        return Single.create {  emitter ->
            foodsListDao.insertLastFoodList(foodsList)
            emitter.onSuccess(foodsList)
        }
    }

    fun insertTypeOfFoodListToDb(
        typeOfFoodList: MutableList<FoodTypeUiEntity>
    ): Single<MutableList<FoodTypeUiEntity>> {
        return Single.create { emitter ->
            foodsTypeDao.insertLastTypeOfFood(typeOfFoodList)
            emitter.onSuccess(typeOfFoodList)
        }
    }
}