package com.delivery_test.view.event

sealed class HomeViewEvent {
    class FoodRequestSuccess(
        val currentSelectedItemPos: Int,
        val clickedItemPos: Int
    ) : HomeViewEvent()
}