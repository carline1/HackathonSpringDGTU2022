package com.example.hackathonspring2022.ui.features.statistic_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathonspring2022.databinding.ChartColumnViewHolderBinding
import com.example.hackathonspring2022.di.Config
import com.example.hackathonspring2022.domain.IntervalMapper
import com.example.hackathonspring2022.ui.features.query_table_fragment.QueryTableAdapter
import com.example.hackathonspring2022.ui.models.ChartModel
import dagger.multibindings.IntoMap
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.utils.io.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.InetSocketAddress

class ChartAdapter(
    val maxColumnHeight: Int
) : RecyclerView.Adapter<ChartAdapter.ChartViewHolder>() {

    val items: MutableList<ChartModel> = mutableListOf()
    private var maxAmountOfQueries = 0

    fun updateData(newItems: List<ChartModel>) {
        items.clear()
        items.addAll(newItems)
        maxAmountOfQueries = items.maxOf { it.amountOfQueries }
        notifyDataSetChanged()
    }

    fun addNewColumn(newItem: ChartModel) {
        items.add(newItem)
        val newItems2 = mutableListOf<ChartModel>()
        items.forEach {
            newItems2.add(it.copy())
        }
        items.clear()
        items.addAll(newItems2)
        maxAmountOfQueries = items.maxOf { it.amountOfQueries }
//        notifyItemInserted(itemCount - 1)
        notifyDataSetChanged()
    }

    fun incLastColumnValue() {
        val amountOfQueries = items[itemCount - 1].amountOfQueries + 1
        items[itemCount - 1] = items[itemCount - 1].copy(amountOfQueries = amountOfQueries)
        val newItems2 = mutableListOf<ChartModel>()
        items.forEach {
            newItems2.add(it.copy())
        }
        items.clear()
        items.addAll(newItems2)
        maxAmountOfQueries = items.maxOf { it.amountOfQueries }

//        notifyItemChanged(itemCount - 1)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartViewHolder {
        return ChartViewHolder(ChartColumnViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) {
        return holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ChartViewHolder(private val binding: ChartColumnViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ChartModel) {
            binding.chartColumn.update(item.amountOfQueries, maxColumnHeight = maxColumnHeight, maxAmountOfQueries)


        }
    }

}