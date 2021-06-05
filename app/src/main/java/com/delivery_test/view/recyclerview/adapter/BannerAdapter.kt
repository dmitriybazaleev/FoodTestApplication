package com.delivery_test.view.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.delivery_test.R
import com.delivery_test.view.recyclerview.diffutil.FoodTypeDiffUtil
import com.delivery_test.database.databaseentity.FoodTypeUiEntity
import com.delivery_test.view.recyclerview.holders.BannerHolder

class BannerAdapter : RecyclerView.Adapter<BannerHolder>() {

    private var bannersList: MutableList<FoodTypeUiEntity> = mutableListOf()

    fun addBanners(list: MutableList<FoodTypeUiEntity>) {
        val diffUtilCallBack = FoodTypeDiffUtil(bannersList, list)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallBack)
        bannersList = list
        diffUtilResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_banner, parent, false)

        return BannerHolder(itemView = view)
    }

    override fun onBindViewHolder(holder: BannerHolder, position: Int) {
        holder.bind(bannersList[position])
    }

    override fun getItemCount(): Int {
        return bannersList.size
    }
}