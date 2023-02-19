package com.example.hackathonspring2022.ui.features.statistic_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathonspring2022.R
import com.example.hackathonspring2022.databinding.ChartViewHolderBinding
import com.example.hackathonspring2022.di.Config
import com.example.hackathonspring2022.domain.IntervalMapper
import com.example.hackathonspring2022.ui.models.ChartsModel
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.utils.io.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.InetSocketAddress


class ChartsAdapter : RecyclerView.Adapter<ChartsAdapter.ChartsViewHolder>() {

    private val items: MutableList<ChartsModel> = mutableListOf()

    fun updateData(newItems: MutableList<ChartsModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartsViewHolder {
        return ChartsViewHolder(
            ChartViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChartsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ChartsViewHolder(private val binding: ChartViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ChartsModel) {
            val adapter =
                ChartAdapter(binding.root.resources.getDimensionPixelSize(com.example.hackathonspring2022.R.dimen.chart_column_max_height)).apply {
                    updateData(item.data)
                }

            binding.name.text = item.title
            binding.chart.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.chart.adapter = adapter
            binding.chart.smoothScrollToPosition(adapter.itemCount - 1)



//            val spinnerAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
//                binding.root.context, R.array.time_intervals,
//                R.layout.spinner_layout
//            )
//            spinnerAdapter.setDropDownViewResource(R.layout.spinnser_item_layout)
//
//            binding.timeIntervals.adapter = spinnerAdapter

//            CoroutineScope(Dispatchers.IO).launch {
//                val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(
//                    InetSocketAddress("192.168.157.10", 9000)
//                )
//                val input = socket.openReadChannel()
//                while (true) {
//                    var response = input.readUTF8Line()?.removePrefix("b'")
//                    response = response?.removeSuffix("'")
//                    println("Server said: '$response'")
//                    launch(Dispatchers.Main) {
////                        IntervalMapper.updateChart(response, )
//                    }
//                }
//
////            val listener = object : AdapterView.OnItemSelectedListener {
////                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
////                    TODO("Not yet implemented")
////                }
////
////                override fun onNothingSelected(p0: AdapterView<*>?) {
////                    TODO("Not yet implemented")
////                }
////            }
////            binding.timeIntervals.onItemSelectedListener = listener
        }
    }

}
