package com.delivery_test.viewmodel

sealed class HomeViewEvent {
    class FoodRequestSuccess(
        val currentSelectedItemPos: Int,
        val clickedItemPos: Int
    ) : HomeViewEvent()
}