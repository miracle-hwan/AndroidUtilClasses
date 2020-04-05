package com.miraclehwan.androidutils.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miraclehwan.androidutils.databinding.ItemUtilBinding
import com.miraclehwan.androidutils.view.main.model.ClassInfo

class UtilAdapter : RecyclerView.Adapter<UtilAdapter.UtilViewHolder>() {

    private val items = mutableListOf<ClassInfo>()
    var itemClickCallback: ((className: Class<*>) -> Unit)? = null

    fun addItems(items: List<ClassInfo>) {
        val startPosition = if (this.items.size == 0) 0 else this.items.size - 1
        this.items.addAll(items)
        notifyItemRangeInserted(startPosition, items.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UtilViewHolder =
        UtilViewHolder(ItemUtilBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UtilViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    inner class UtilViewHolder(private val binding: ItemUtilBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { itemClickCallback?.invoke(items[adapterPosition].utilClass) }
        }

        fun onBind(item: ClassInfo) {
            binding.tvTitle.text = item.utilName
        }
    }
}