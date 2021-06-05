package com.delivery_test.database.databaseentity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delivery_test.network.NetworkConstants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "mealsType")
@Parcelize
data class FoodTypeUiEntity(
    @PrimaryKey
    @SerializedName(NetworkConstants.ID_CATEGORY)
    var idCategory: Int?,

    @ColumnInfo(name = "strCategory")
    @SerializedName(NetworkConstants.STR_CATEGORY)
    var strCategory: String?,

    @ColumnInfo(name = "strCategoryThumb")
    @SerializedName(NetworkConstants.STR_CATEGORY_THUMB)
    var strCategoryThumb: String?,

    @ColumnInfo(name = "strCategoryDescription")
    @SerializedName(NetworkConstants.STR_CATEGORY_DESCRIPTION)
    var strCategoryDescription: String?,

    var isChecked: Boolean
) : Parcelable
