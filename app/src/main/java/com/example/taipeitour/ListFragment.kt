package com.example.taipeitour

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.example.taipeitour.databinding.FragmentListBinding
import com.example.taipeitour.recycler.TourListAdapter
import com.example.taipeitour.tools.LogTool
import com.example.taipeitour.viewModel.ListViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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

        // 設定選單與對應功能
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                val dialogBuilder = MaterialAlertDialogBuilder(requireContext(), com.google.android.material.R.style.ThemeOverlay_Material3_MaterialAlertDialog_Centered)
                return when (menuItem.itemId) {
                    R.id.action_languages -> {
                        dialogBuilder
                            .setTitle("語言")
                            .setItems(R.array.tour_languages) { dialog, which ->
                                val language = resources.getStringArray(R.array.tour_languages)[which]
                                LogTool.i("language is $language")
                                viewModel.changeLanguage(language)
                            }
                            .show()
                        true
                    }

                    R.id.action_about -> {
                        dialogBuilder
                            .setTitle("關於")
                            .setMessage("本 App 為練習使用，串接台北市政府 Open Data，資料來源為 https://data.taipei/ 。")
                            .setPositiveButton("確定") { _, _ -> }
                            .show()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}