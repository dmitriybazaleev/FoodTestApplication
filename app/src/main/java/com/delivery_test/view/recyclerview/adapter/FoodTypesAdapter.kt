package com.delivery_test.view.recyclerview.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.delivery_test.R
import com.delivery_test.view.recyclerview.diffutil.FoodTypeDiffUtil
import com.delivery_test.database.databaseentity.FoodTypeUiEntity
import com.delivery_test.view.recyclerview.event.IFoodTypeEvent
import com.delivery_test.view.recyclerview.holders.FoodTypeHolder
import java.lang.Exception

/**
 * Адаптер для категории питания
 */
class FoodTypesAdapter(
    private val typeEvent: IFoodTypeEvent
) : RecyclerView.Adapter<FoodTypeHolder>() {

    private var foodsTypeArray: MutableList<FoodTypeUiEntity> = mutableListOf()

    fun addFoodsType(foodsTypes: MutableList<FoodTypeUiEntity>) {
        if (foodsTypes.isNotEmpty()) {
            val diffUtilCallBack = FoodTypeDiffUtil(foodsTypeArray, foodsTypes)
            val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallBack)
            foodsTypeArray = foodsTypes
            diffUtilResult.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodTypeHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food_type, parent, false)

        return FoodTypeHolder(view, typeEvent)
    }

    override fun onBindViewHolder(holder: FoodTypeHolder, position: Int) {
        holder.bind(foodsTypeArray[position])
    }

    override fun getItemCount(): Int {
        return foodsTypeArray.size
    }

    fun updateItems(clickedItemPos: Int, currentSelectedItemPos: Int) {
        try {
            foodsTypeArray[currentSelectedItemPos].isChecked = false
            foodsTypeArray[clickedItemPos].isChecked = true
            notifyDataSetChanged()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}