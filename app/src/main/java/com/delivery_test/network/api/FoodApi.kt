package com.delivery_test.network.api

import com.delivery_test.network.apimodel.FoodUiModel
import com.delivery_test.network.apimodel.FoodTypeUiModel
import com.delivery_test.network.NetworkConstants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {

    // метод возвращает все категории питания
    @GET(NetworkConstants.METHOD_GET_ALL_TYPES)
    fun getAllMealTypes(): Single<FoodTypeUiModel>

    // метод будет делать запрос на какой-то определенный тип питания
    @GET(NetworkConstants.METHOD_GET_CURRENT_MEAL)
    fun getCurrentMeal(
        @Query("s")
        name: String
    ): Single<FoodUiModel>

    
}