package com.delivery_test.view.recyclerview.diffutil

import androidx.recyclerview.widget.DiffUtil

/**
 * Базовый класс для Diff Util
 */
abstract class BaseDiffUtil<T> constructor(
    private val oldList: MutableList<T>?,
    private val newList: MutableList<T>?
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList?.size ?: 0
    }

    override fun getNewListSize(): Int {
        return newList?.size ?: 0
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldList != null && newList != null) {
            return areItemsTheSame(oldList[oldItemPosition], newList[newItemPosition])
        }
        return false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldList != null && newList != null) {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
        return false
    }

    abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean
}