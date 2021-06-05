package com.delivery_test.network

object NetworkConstants {

    const val TIME_CONNECTION: Long = 20

    const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/" // базовая ссылка

    // Имена полей Json, чтоб было понятнее
    const val CATEGORIES = "categories"
    const val MEALS = "meals"
    // Блок для MealTypesModel
    const val ID_CATEGORY = "idCategory"
    const val STR_CATEGORY = "strCategory"
    const val STR_CATEGORY_THUMB = "strCategoryThumb"
    const val STR_CATEGORY_DESCRIPTION = "strCategoryDescription"

    // Блок для MealsListModel
    const val ID_MEAL = "idMeal"
    const val STR_MEAL = "strMeal"
    const val STR_MEAL_THUMB = "strMealThumb"
    const val STR_INSTRUCTION = "strInstructions"


    // методы
    const val METHOD_GET_ALL_TYPES = "categories.php" // возвращает все категории питания
    const val METHOD_GET_CURRENT_MEAL = "search.php?" // возвращает определенный список еды, которую мы хотим получить
}