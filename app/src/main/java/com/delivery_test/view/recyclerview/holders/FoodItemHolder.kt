package com.delivery_test.view.recyclerview.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.delivery_test.R
import com.delivery_test.base.Constants
import com.delivery_test.database.databaseentity.FoodUiEntity
import com.delivery_test.view.loadImageByUrl
import com.delivery_test.view.setTextOrHide

class FoodItemHolder constructor(
    itemView: View
): RecyclerView.ViewHolder(itemView) {

    private var foodImage = itemView.findViewById<ImageView>(R.id.imv_meal)
    private var foodName = itemView.findViewById<TextView>(R.id.txv_meal_name)
    private var foodDescription = itemView.findViewById<TextView>(R.id.txv_meal_descr)
    private var foodPrice = itemView.findViewById<TextView>(R.id.txv_meal_price)


    fun bind(itemFood: FoodUiEntity) {
        foodName.setTextOrHide(itemFood.strCategory)
        foodDescription.setTextOrHide(itemFood.strInstruction)
        foodPrice.text = Constants.MAX_PRICE_FOOD
        foodImage.loadImageByUrl(itemFood.strMealThumb)
    }
}