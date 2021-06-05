package com.delivery_test.view.recyclerview.holders

import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.delivery_test.R
import com.delivery_test.database.databaseentity.FoodTypeUiEntity
import com.delivery_test.view.recyclerview.event.IFoodTypeEvent
import com.delivery_test.view.setTextOrHide

class FoodTypeHolder(
    itemView: View,
    private val typeCallBack: IFoodTypeEvent,
) : RecyclerView.ViewHolder(itemView) {

    private var mealTypeText = itemView.findViewById<TextView>(R.id.txv_food_name)
    private var mealTypeFl = itemView.findViewById<FrameLayout>(R.id.fmlyt_food_type)


    fun bind(foodTypeItem: FoodTypeUiEntity) {
        mealTypeText.setTextOrHide(foodTypeItem.strCategory)

        mealTypeText.setOnClickListener {
            typeCallBack.onFoodTypeClicked(foodTypeItem, adapterPosition)
        }

        checkPosition(foodTypeItem.isChecked)
    }

    private fun checkPosition(isChecked: Boolean) {
        if (isChecked) {
            mealTypeFl.setBackgroundResource(R.drawable.red_rectangle_selected)
            mealTypeText.setTextColor(ContextCompat.getColor(itemView.context, R.color.red))
        } else {
            mealTypeFl.setBackgroundResource(R.drawable.white_rectangle_non_selected)
            mealTypeText.setTextColor(ContextCompat.getColor(itemView.context, R.color.dark_grey))
        }

    }
}
