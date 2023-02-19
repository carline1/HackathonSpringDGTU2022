package com.example.hackathonspring2022.ui.features.statistic_fragment

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.hackathonspring2022.databinding.TableViewBinding

class TableView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): FrameLayout(context, attrs) {

    private val binding = TableViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun addData() {
        binding.tableLayout
    }

}