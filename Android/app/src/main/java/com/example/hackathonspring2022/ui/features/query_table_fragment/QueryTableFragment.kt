package com.example.hackathonspring2022.ui.features.query_table_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathonspring2022.databinding.QueryTableFragmentBinding
import com.example.hackathonspring2022.di.Config
import com.example.hackathonspring2022.ui.features.query_table_fragment.QueryTableAdapter.QueryTableData
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.utils.io.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import java.net.InetSocketAddress
import kotlin.coroutines.coroutineContext


class QueryTableFragment : Fragment() {

    private var _binding: QueryTableFragmentBinding? = null
    private val binding get() = _binding!!

    private var adapter = QueryTableAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this._binding = QueryTableFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.queryTableRecycler.adapter = adapter
        binding.queryTableRecycler.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        CoroutineScope(Dispatchers.IO).launch {
            launch(Dispatchers.IO) { binding.progressBar.isVisible = true }
            val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(
                InetSocketAddress(Config.ip, Config.port)
            )
            val input = socket.openReadChannel()
            while (true) {
                var response = input.readUTF8Line()?.removePrefix("b'")
                response = response?.removeSuffix("'")
                println("Server said: '$response'")
                launch(Dispatchers.Main) {
                    binding.progressBar.isVisible = false
                    adapter.addData(QueryTableData(response))
//                    if (binding.queryTableRecycler.layoutManager.isViewPartiallyVisible()) {
//                        binding.queryTableRecycler.smoothScrollToPosition(0)
//                    }
                }
            }
        }
    }

}