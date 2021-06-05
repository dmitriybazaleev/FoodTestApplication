package com.delivery_test.network.apimodel

import android.os.Parcelable
import com.delivery_test.database.databaseentity.FoodUiEntity
import com.delivery_test.network.NetworkConstants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodUiModel(
    @SerializedName(NetworkConstants.MEALS)
    var searchFoodResultList: MutableList<FoodUiEntity>?
): Parcelable
