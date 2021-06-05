package com.delivery_test.database.databaseentity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.delivery_test.network.NetworkConstants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Класс служит одновременно для базы данных entity
 * И служит для запросов
 */
@Entity(tableName = "mealsList")
@Parcelize
data class FoodUiEntity(
    @PrimaryKey
    @SerializedName(NetworkConstants.ID_MEAL)
    var idMeal: Int?,

    @ColumnInfo(name = "strMeal")
    @SerializedName(NetworkConstants.STR_MEAL)
    var strMeal: String?,

    @ColumnInfo(name = "strCategory")
    @SerializedName(NetworkConstants.STR_CATEGORY)
    var strCategory: String?,

    @ColumnInfo(name = "strMealThumb")
    @SerializedName(NetworkConstants.STR_MEAL_THUMB)
    var strMealThumb: String?,

    @ColumnInfo(name = "strInstruction")
    @SerializedName(NetworkConstants.STR_INSTRUCTION)
    var strInstruction: String?,

    var currentPosition: Int
) : Parcelable
