package com.example.hackathonspring2022.domain

import com.example.hackathonspring2022.R
import com.example.hackathonspring2022.ui.models.ChartModel

object IntervalMapper {

//    val items: MutableList<ChartModel> = mutableListOf()

    fun updateChart(
        items: MutableList<ChartModel>,
        response: String?,
        interval: Int,
        callback1: (min: String) -> Unit,
        callback2: () -> Unit,
        callback3: (min: String) -> Unit,
    ) {
        if (response == null) return
        val textArray = response.split("<||>")
        val time = textArray[0]
        val ip = textArray[1]
        val flags = textArray[2]

        val minLast = time.split(":")[2].split(".")[0].toInt()


        if (items.isEmpty()) {
//            items.add(ChartModel(min))
            callback1.invoke(time)
        } else {
            val min = if (items[items.size - 1].startMin != "")
                items[items.size - 1].startMin.split(":")[2].split(".")[0].toInt()
            else
                minLast
            if (minLast - min < interval) {
//                items[-1].amountOfQueries += 1
                callback2.invoke()
            } else {
//                items.add(ChartModel(min))
                callback3.invoke(time)
            }
        }
    }

    fun getColumns(interval: Int): List<ChartModel> {
        return listOf()
    }

}