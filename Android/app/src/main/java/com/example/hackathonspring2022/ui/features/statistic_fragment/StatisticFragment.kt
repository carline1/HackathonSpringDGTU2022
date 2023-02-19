package com.example.hackathonspring2022.ui.features.statistic_fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathonspring2022.api.ApiService
import com.example.hackathonspring2022.databinding.StatisticFragmentBinding
import com.example.hackathonspring2022.di.Config
import com.example.hackathonspring2022.di.appComponent
import com.example.hackathonspring2022.domain.IntervalMapper
import com.example.hackathonspring2022.ui.models.ChartModel
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.utils.io.*
import kotlinx.coroutines.*
import java.net.InetSocketAddress
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class StatisticFragment : Fragment() {

    private var _binding: StatisticFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var service: ApiService

    private var adapter: ChartAdapter? = null

    private var coroutineContext: CoroutineContext? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this._binding = StatisticFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        CoroutineScope(Dispatchers.IO).launch {
//            val zhopa = service.getZhopa()
//        }

//        val lineView = binding.lineView
//
//        lineView.setBottomTextList(arrayListOf("a", "aaa"))
//        lineView.setDataList(arrayListOf(1, 2), 100) //or lineView.setFloatDataList(floatDataLists)
        adapter = ChartAdapter(view.resources.getDimensionPixelSize(com.example.hackathonspring2022.R.dimen.chart_column_max_height))
        binding.chart.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
//        binding.chart.layoutManager =
//            LinearLayoutManager(context)
        binding.chart.adapter = adapter
        binding.name.text = "Количество поступивших запросов"

//        binding.chartList.addItemDecoration(
//            DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL)
//                .apply { setDrawable(myDrawable) }F
//        )

        CoroutineScope(Dispatchers.IO).launch {
            val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(
                InetSocketAddress(
                    Config.ip,
                    Config.port
                )
            )
            val input = socket.openReadChannel()
            while (true) {
                var response = input.readUTF8Line()?.removePrefix("b'")
                response = response?.removeSuffix("'")
                println("Server said: '$response'")
                launch(Dispatchers.Main) {
                    IntervalMapper.updateChart(adapter?.items!!, response, 5, {
                        adapter!!.addNewColumn(ChartModel(1, it))
//                        items.add(ChartModel(it))
//                        notifyItemInserted(items.size)
                    }, {
//                        items[items.size].amountOfQueries += 1
//                        notifyItemChanged(items.size)
                       adapter!!.incLastColumnValue()
                    }, {
//                        items.add(ChartModel(it))
//                        notifyItemInserted(items.size)
                        adapter!!.addNewColumn(ChartModel(1, it))

                    })
                }
            }
        }

//        adapter?.updateData(
//            mutableListOf(
//                ChartsModel(
//                    "Количество поступивших запросов",
//                    mutableListOf(
//                        ChartModel(50),
//                        ChartModel(200),
//                        ChartModel(400),
//                        ChartModel(342),
//                        ChartModel(300),
//                        ChartModel(342),
//                        ChartModel(200),
//                        ChartModel(500),
//                        ChartModel(20),
//                        ChartModel(342),
//                        ChartModel(100),
//                    )
//                ),
////                ChartsModel(
////                    "AAAAAA123231231123",
////                    mutableListOf(
////                        ChartModel(50),
////                        ChartModel(200),
////                        ChartModel(400),
////                        ChartModel(342)
////                    )
////                )
//            )
//        )


//        client.outputStream.write("Hello from the client!".toByteArray())
//        val text = BufferedReader(InputStreamReader(socket.inputStream)).readLine()
//        socket.close()

//        CoroutineScope(Dispatchers.IO).launch {
//            val socket = Socket("192.168.137.10", 9004)
//
//            while(true) {
//                val text = BufferedReader(InputStreamReader(socket.inputStream)).readLine()
//                Log.wtf("TAG", text)
//                withContext(Dispatchers.Main) {
//                    binding.textView.text = text
//                    Toast.makeText(context, text, Toast.LENGTH_SHORT)
//                    Log.wtf("TAG", text)
//
//                }
//                Log.wtf("TAG", text)
//
//            }
//            socket.close()
//
//        }

//        CoroutineScope(Dispatchers.IO).launch {
//        coroutineContext = CoroutineScope(Dispatchers.IO).launch {
//            val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(
//                InetSocketAddress("192.168.137.10", 9007)
//            )
//            val input = socket.openReadChannel()
//            while (true) {
//                val response = input.readUTF8Line()
//                println("Server said: '$response'")
//            }
//        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        coroutineContext?.job?.cancel()
//    }

}