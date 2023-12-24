package com.example.taipeitour

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.taipeitour.data.ViewPagerItem
import com.example.taipeitour.databinding.FragmentDetailBinding
import com.example.taipeitour.recycler.ViewPagerAdapter

/**
 * 旅遊景點資訊的 Fragment
 */
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 先判斷有沒有參數，再將參數內資料綁定到 UI 上
        arguments?.let {
            binding.detailIntroduction.text = it.getString("introduction")
            binding.detailAddress.text = it.getString("address")
            binding.detailTel.text = it.getString("tel")

            it.getString("url")?.let { url ->
                binding.button.setOnClickListener { view ->
                    var bundle = Bundle().apply {
                        putString("url", url)
                        putString("title", it.getString("title"))
                    }
                    Navigation.findNavController(binding.root).navigate(R.id.action_detailFragment_to_webViewFragment, bundle)
                }
            }

            // 如果有 images list 就轉換並設定給 adapter
            it.getStringArrayList("images")?.let { list ->
                val adapter = ViewPagerAdapter()
                binding.detailViewpager.adapter = adapter
                adapter.submitList(list.map { imageURL -> ViewPagerItem(imageURL) })
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}