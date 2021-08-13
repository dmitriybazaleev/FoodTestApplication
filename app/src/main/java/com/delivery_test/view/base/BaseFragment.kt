package com.delivery_test.view.base

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.delivery_test.base.Constants
import com.delivery_test.view.MainActivity

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    companion object {
        const val BASE_FRAGMENT_TAG = "baseFragmentTag"
    }

    // Поле переданного binding обращение к view
    var binding: B? = null

    // Если необходимо получить доступ к Activity
    var mainActivity: MainActivity? = null

    var progressBar: ProgressBar? = null

    // Нужно передать и вернуть наш сгенерированный binding
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> B

    override fun onAttach(context: Context) {
        mainActivity = activity as MainActivity
        super.onAttach(context)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBind()
    }

    abstract fun getProgress(): ProgressBar?

    private fun onBind() {
        getProgress()?.let { resultProgress ->
            progressBar = resultProgress
        } ?: run {
            Log.d(BASE_FRAGMENT_TAG, "Progress bar не указан")
        }
    }

    fun showProgress() {
        progressBar?.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressBar?.visibility = View.GONE
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
}