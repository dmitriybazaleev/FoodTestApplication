package com.delivery_test.view.base

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.delivery_test.base.Constants
import com.delivery_test.view.MainActivity

/**
 * Базовый Фрагмент. В generic должен принимать в себя binding с layout
 * И еще принимает в себя ViewModel, которая будет работать с фрагментом
 */
abstract class BaseFragment<B: ViewBinding, VM: ViewModel>: Fragment() {

    // Поле для обращения к View Model
    var viewModel: VM? = null

    // Поле переданного binding обращение к view
    var binding: B? = null

    // Если необходимо получить доступ к Activity
    var mainActivity: MainActivity? = null

    // Этот метод нужен для того, чтоб вернуть нужную View Model
    abstract fun onCreateViewModel(savedInstanceState: Bundle?): VM

    // Нужно передать и вернуть наш сгенерированный binding
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> B

    override fun onAttach(context: Context) {
        mainActivity = activity as MainActivity
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBind(savedInstanceState)
    }

    override fun onDetach() {
        mainActivity = null
        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingInflater.invoke(layoutInflater, container, false)
        return requireNotNull(binding?.root)
    }

    // Метод показывает Progress Bar
    fun showProgress() {
        mainActivity?.activityBinding?.fmlytMainProgress?.visibility = View.VISIBLE
    }

    // Метод скрывает Progress Bar
    fun hideProgress() {
        mainActivity?.activityBinding?.fmlytMainProgress?.visibility = View.GONE
    }

    fun showConnectionErrorDialog() {
        AlertDialog.Builder(context)
            .setTitle(Constants.ERROR)
            .setMessage(Constants.CHECK_YOUR_INTERNET_CONNECTION)
            .setNegativeButton(Constants.CANCEL, null)
            .show()
    }

    fun showNothingToShowDialog() {
        AlertDialog.Builder(context)
            .setTitle(Constants.ERROR)
            .setMessage(Constants.NOTHING_TO_SHOW)
            .setNegativeButton(Constants.CANCEL, null)
            .show()
    }

    fun showToast(message: String) {
        if (message.isNotEmpty()) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    // Инициализация View Model
    private fun onBind(savedInstanceState: Bundle?) {
        viewModel = onCreateViewModel(savedInstanceState)
    }
}