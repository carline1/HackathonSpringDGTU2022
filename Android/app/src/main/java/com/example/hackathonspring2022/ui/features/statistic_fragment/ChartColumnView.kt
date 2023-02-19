package com.example.hackathonspring2022.ui.features.statistic_fragment

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.hackathonspring2022.databinding.ChartViewBinding

class ChartColumnView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding = ChartViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun update(amountOfQueries: Int, maxColumnHeight: Int, maxAmountOfQueries: Int) {
        binding.amount.text = amountOfQueries.toString()
        val maxAmountOfQueries1 = if (maxAmountOfQueries == 0) 1 else maxAmountOfQueries
        val heightColumn = maxColumnHeight / maxAmountOfQueries1 * amountOfQueries
        binding.column.layoutParams.height = heightColumn
    }


//    private val columnPaint = Paint().apply {
//        style = Paint.Style.FILL
//    }
//
//    private val amountOfQueriesPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
//        textSize = resources.getDimension(R.dimen.amountOfQueriesTextSize)
//    }
//
//    private val columnRowWidth = resources.getDimension(R.dimen.columnRowWidth)
//    private val betweenColumnWidth = resources.getDimension(R.dimen.betweenColumnWidth)
//
//    private val column = Rect()
//
//    private val states: List<State> = emptyList()
//
//    fun update() {
//
//        requestLayout()
//        invalidate()
//    }
//
//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//
//
//    }

//    fun update(state: State) {
//        binding.lineView.setDataList(state.data, state.data)
//        binding.lineView.setBottomTextList()
//    }
//
//    data class State(
//        val data: ArrayList<Int>,
//        val bottomTextList: ArrayList<String>
//    )

//    data class State(
//        val amountOfQueries: Int
//    )

}