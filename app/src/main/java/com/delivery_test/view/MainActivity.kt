package com.delivery_test.view

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.delivery_test.R
import com.delivery_test.databinding.ActivityMainBinding

/**
 * Приложение будет работать по принципу Single Activity
 * Главным экраном будет HomeFragment
 */
class MainActivity: AppCompatActivity() {

    // Поле с binding activity
    var activityBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()
        setLightStatusBar()
    }

    /**
     * Этот метод инициализирует наш binding
     */
    private fun setView() {
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityBinding?.root)
    }

    /**
     * Сэтим светлый статус бар
     */
    private fun setLightStatusBar() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
                window.statusBarColor =
                    ContextCompat.getColor(applicationContext, R.color.status_bar_semi_transparent)
            } else {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        } else {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            window.statusBarColor = Color.WHITE
        }
    }
}