package com.delivery_test.view.recyclerview.diffutil

import com.delivery_test.database.databaseentity.FoodTypeUiEntity

class FoodTypeDiffUtil constructor(
    oldArray: MutableList<FoodTypeUiEntity>,
    newList: MutableList<FoodTypeUiEntity>
): BaseDiffUtil<FoodTypeUiEntity>(oldArray, newList) {

    override fun areItemsTheSame(oldItem: FoodTypeUiEntity, newItem: FoodTypeUiEntity): Boolean {
        return oldItem.idCategory == newItem.idCategory
    }
}