package com.delivery_test.view.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.delivery_test.R
import com.delivery_test.view.recyclerview.diffutil.FoodListDiffUtil
import com.delivery_test.database.databaseentity.FoodUiEntity
import com.delivery_test.view.recyclerview.holders.FoodItemHolder

class FoodListAdapter : RecyclerView.Adapter<FoodItemHolder>() {

    var mainFoodList: MutableList<FoodUiEntity> = mutableListOf()

    fun addFoods(list: MutableList<FoodUiEntity>) {
        val diffUtilCallBack = FoodListDiffUtil(mainFoodList, newList = list)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallBack)
        mainFoodList = list
        diffUtilResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal_type, parent, false)

        return FoodItemHolder(itemView = layoutInflater)
    }

    override fun onBindViewHolder(holder: FoodItemHolder, position: Int) {
        holder.bind(mainFoodList[position])
    }

    override fun getItemCount(): Int {
        return mainFoodList.size
    }
}