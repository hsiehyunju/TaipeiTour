package com.example.taipeitour

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.taipeitour.databinding.FragmentListBinding
import com.example.taipeitour.recycler.TourListAdapter
import com.example.taipeitour.viewModel.ListViewModel

/**
 * 旅遊景點列表的 Fragment
 */
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this)[ListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        val adapter = TourListAdapter()
        this.binding.apply {
            tourRecyclerView.adapter = adapter
        }

        // 觀察資料變化，更新 RecyclerView
        viewModel.tourList.observe(viewLifecycleOwner) { list ->
            val resultData = list.sortedBy { it.id }
            adapter.submitList(resultData)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}