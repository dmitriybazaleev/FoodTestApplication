package com.delivery_test.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.delivery_test.base.Constants.HOME_FRAGMENT_TAG
import com.delivery_test.base.DeliveryApp
import com.delivery_test.database.databaseentity.FoodTypeUiEntity
import com.delivery_test.database.databaseentity.FoodUiEntity
import com.delivery_test.network.ServerResponse
import com.delivery_test.usecase.HomeUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException
import javax.inject.Inject

class HomeViewModel : ViewModel() {

    private var compositeDisposable = CompositeDisposable()

    private var selectedPosition: Int = 0

    private val foodTypeLiveData = MutableLiveData<MutableList<FoodTypeUiEntity>>()
    private val foodListLiveData = MutableLiveData<MutableList<FoodUiEntity>>()
    val responseServerLiveData = MutableLiveData<ServerResponse>()
    val viewEvent = MutableLiveData<HomeViewEvent>()

    @Inject
    lateinit var homeUseCase: HomeUseCase

    init {
        DeliveryApp.component.inject(this)
    }

    // Получаем планку с категориями
    fun getAllFoodsType() {
        val disposable = homeUseCase.getAllFoodTypes()
            .filter {
                it.categories?.size != 0 && it.categories != null
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { responseServerLiveData.value = ServerResponse.SHOW_PROGRESS }
            .doFinally { responseServerLiveData.value = ServerResponse.HIDE_PROGRESS }
            .subscribe({
                Log.d(HOME_FRAGMENT_TAG, "data size: ${it.categories?.size}")
                it.categories?.let { data ->
                    insertFoodTypeToDataBase(data)
                    data[0].strCategory?.let { strCategory ->
                        Log.d(HOME_FRAGMENT_TAG, "request name: $strCategory")
                        getFoodListByName(strCategory, 0)
                    }
                }
                foodTypeLiveData.value = it.categories
            }, {
                Log.d(HOME_FRAGMENT_TAG, "getAllFoods error: ${it.message}")
                when (it) {
                    is UnknownHostException -> {
                        responseServerLiveData.value = ServerResponse.CONNECTION_LOST
                    }
                }
                // если произошла ошибка, то получаем последние данные с Room
                getFoodTypesFromDataBase()
            })

        compositeDisposable.add(disposable)
    }
    fun getFoodList(entity: FoodTypeUiEntity, clickPosition: Int) {
        val name = entity.strCategory ?: return
        getFoodListByName(name, clickPosition)
    }
    // получаем список меню по имени
    private fun getFoodListByName(name: String, clickPosition: Int) {
        viewEvent.postValue(
            HomeViewEvent.FoodRequestSuccess(
                selectedPosition, clickPosition
            )
        )
        selectedPosition = clickPosition
        val disposable = homeUseCase.getAllMeals(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { responseServerLiveData.value = ServerResponse.SHOW_PROGRESS }
            .doFinally { responseServerLiveData.value = ServerResponse.HIDE_PROGRESS }
            .subscribe({
                it.searchFoodResultList?.let { listResult ->
                    insertMainFoodListToDataBase(listResult)

                    foodListLiveData.value = it.searchFoodResultList
                } ?: run {
                    responseServerLiveData.value = ServerResponse.NOTHING_TO_SHOW
                }
                Log.d(HOME_FRAGMENT_TAG, "food by name size: ${it.searchFoodResultList?.size}")

            }, {
                when (it) {
                    is UnknownHostException -> {
                        responseServerLiveData.value = ServerResponse.CONNECTION_LOST
                    }
                }
                onSearchFoodFromDataBase(name)
                Log.d(HOME_FRAGMENT_TAG, "getFoodByName error: ${it.message}")
            })

        compositeDisposable.add(disposable)
    }

    private fun insertFoodTypeToDataBase(
        data: MutableList<FoodTypeUiEntity>
    ) {
        val disposable = homeUseCase.insertTypeOfFoodListToDb(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, {
                Log.d(HOME_FRAGMENT_TAG, "error insert food type to db: ${it.message}")
            })

        compositeDisposable.add(disposable)
    }

    private fun insertMainFoodListToDataBase(
        data: MutableList<FoodUiEntity>
    ) {
        val disposable = homeUseCase.insertFoodsListToDb(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(HOME_FRAGMENT_TAG, "")
            }, {
                Log.d(HOME_FRAGMENT_TAG, "error insert food to db: ${it.message}")
            })

        compositeDisposable.add(disposable)
    }

    private fun getFoodTypesFromDataBase() {
        val disposable = homeUseCase.getAllLastFoodTypesFromDb()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { responseServerLiveData.value = ServerResponse.SHOW_PROGRESS }
            .doFinally { responseServerLiveData.value = ServerResponse.HIDE_PROGRESS }
            .subscribe({
                Log.d(HOME_FRAGMENT_TAG, "last food types size: ${it.size}")
                foodTypeLiveData.value = it
                if (!it.isNullOrEmpty()) {
                    it[0].strCategory?.let { strCategory ->
                        onSearchFoodFromDataBase(strCategory)
                    }
                }
            }, {
                Log.d(HOME_FRAGMENT_TAG, "getLastFoodTypes error: ${it.message}")
            })

        compositeDisposable.add(disposable)

    }

    /**
     * Ищем по базе, что есть ли такое меню
     * Метод вызывается, когда сервер отдал ошибку например:
     * Нет интернета
     * В таком случае выполняется поиск по базе всех блюд
     */
    private fun onSearchFoodFromDataBase(search: String) {
        val disposable = homeUseCase.onSearchFoodFromDb(search)
            .filter {
                it.size != 0
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(HOME_FRAGMENT_TAG, "search result size: ${it.size}")
                foodListLiveData.value = it
            }, {
                Log.d(HOME_FRAGMENT_TAG, "search food db error: ${it.message}")
            })

        compositeDisposable.add(disposable)
    }

    fun getFoodListLiveData(): LiveData<MutableList<FoodUiEntity>> {
        return foodListLiveData
    }

    fun getFoodTypeLiveData(): LiveData<MutableList<FoodTypeUiEntity>> {
        return foodTypeLiveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}