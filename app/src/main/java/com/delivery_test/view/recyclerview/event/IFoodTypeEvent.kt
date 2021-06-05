package com.delivery_test.view.recyclerview.event

import com.delivery_test.database.databaseentity.FoodTypeUiEntity
import com.delivery_test.database.databaseentity.FoodUiEntity

interface IFoodTypeEvent {
    fun onFoodTypeClicked(entity: FoodTypeUiEntity, myPosition: Int)
}