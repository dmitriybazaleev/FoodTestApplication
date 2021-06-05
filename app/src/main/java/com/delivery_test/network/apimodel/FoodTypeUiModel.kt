package com.delivery_test.network.apimodel

import android.os.Parcelable
import com.delivery_test.database.databaseentity.FoodTypeUiEntity
import com.delivery_test.network.NetworkConstants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodTypeUiModel(
    @SerializedName(NetworkConstants.CATEGORIES)
    var categories: MutableList<FoodTypeUiEntity>?
): Parcelable