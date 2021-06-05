package com.delivery_test.view.recyclerview.holders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.delivery_test.R
import com.delivery_test.database.databaseentity.FoodTypeUiEntity
import com.delivery_test.view.loadImageByUrl

class BannerHolder constructor(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private var bannerImage = itemView.findViewById<ImageView>(R.id.imv_banner)

    fun bind(typesModel: FoodTypeUiEntity) {
        bannerImage.loadImageByUrl(typesModel.strCategoryThumb)
    }
}