package com.miraclehwan.androidutils.utils.recyclerview

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miraclehwan.androidutils.view.main.model.ClassInfo
import com.miraclehwan.androidutils.view.main.UtilAdapter

object BA {
    @JvmStatic
    @BindingAdapter("setItems", "onItemClick", requireAll = false)
    fun RecyclerView.setItems(
        items: List<ClassInfo>?,
        onItemClickCallback: ((className: Class<*>) -> Unit)
    ) {
        if (items == null) {
            return
        }
        val adapter = this.adapter as? UtilAdapter ?: UtilAdapter().apply {
            itemClickCallback = onItemClickCallback
            setHasFixedSize(true)
            adapter = this
        }
        adapter.addItems(items)
    }
}