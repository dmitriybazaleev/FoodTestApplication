package com.delivery_test.view.recyclerview.diffutil

import com.delivery_test.database.databaseentity.FoodUiEntity

class FoodListDiffUtil constructor(
    oldList: MutableList<FoodUiEntity>,
    newList: MutableList<FoodUiEntity>
) : BaseDiffUtil<FoodUiEntity>(oldList, newList) {

    override fun areItemsTheSame(oldItem: FoodUiEntity, newItem: FoodUiEntity): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }


}