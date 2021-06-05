package com.delivery_test.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.delivery_test.database.databaseentity.FoodTypeUiEntity
import com.delivery_test.view.recyclerview.adapter.BannerAdapter
import com.delivery_test.view.recyclerview.adapter.FoodListAdapter
import com.delivery_test.view.recyclerview.adapter.FoodTypesAdapter
import com.delivery_test.databinding.FragmentHomeBinding
import com.delivery_test.network.ServerResponse
import com.delivery_test.view.base.BaseFragment
import com.delivery_test.view.recyclerview.event.IFoodTypeEvent
import com.delivery_test.viewmodel.HomeViewEvent
import com.delivery_test.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), IFoodTypeEvent {

    private val foodTypeAdapter = FoodTypesAdapter(this)
    private val foodListAdapter = FoodListAdapter()
    private val bannerAdapter = BannerAdapter()

    override fun onCreateViewModel(savedInstanceState: Bundle?): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel?.getAllFoodsType()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewAdapter()
        setRecyclerLayoutManager()
        setSnapHelper()
        getObserverLiveData()
    }

    /**
     * Добавляем snap helper
     */
    private fun setSnapHelper() {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding?.collapsingLayout?.rclvBanners)
    }

    /**
     * Сетим Layout Manager recycler view
     */
    private fun setRecyclerLayoutManager() {
        binding?.collapsingLayout?.rclvBanners?.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding?.collapsingLayout?.rclvAllFoods?.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding?.collapsingLayout?.rclvFoodType?.layoutManager = LinearLayoutManager(
            context,
             LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun getObserverLiveData() {
        viewModel?.getFoodListLiveData()?.observe(viewLifecycleOwner) { allFoodsList ->
            foodListAdapter.addFoods(allFoodsList)
        }
        viewModel?.getFoodTypeLiveData()?.observe(viewLifecycleOwner) { allFoodsListType ->
            foodTypeAdapter.addFoodsType(allFoodsListType)
            bannerAdapter.addBanners(allFoodsListType)
        }
        viewModel?.responseServerLiveData?.observe(viewLifecycleOwner) { response ->
            when (response) {
                ServerResponse.CONNECTION_LOST -> {
                    showConnectionErrorDialog()
                }
                ServerResponse.HIDE_PROGRESS -> {
                    hideProgress()
                }
                ServerResponse.SHOW_PROGRESS -> {
                    showProgress()
                }
                ServerResponse.NOTHING_TO_SHOW -> {
                    showNothingToShowDialog()
                }
            }
        }

        viewModel?.viewEvent?.observe(viewLifecycleOwner) {
            when(it) {
                is HomeViewEvent.FoodRequestSuccess -> {
                    foodTypeAdapter.updateItems(it.clickedItemPos, it.currentSelectedItemPos)
                }
            }
        }
    }

    /**
     * Сетим adapter recycler view
     */
    private fun setRecyclerViewAdapter() {
        binding?.collapsingLayout?.rclvFoodType?.adapter = foodTypeAdapter
        binding?.collapsingLayout?.rclvAllFoods?.adapter = foodListAdapter
        binding?.collapsingLayout?.rclvBanners?.adapter = bannerAdapter
    }

    override fun onFoodTypeClicked(entity: FoodTypeUiEntity, myPosition: Int) {
        viewModel?.getFoodList(entity, myPosition)
    }
}