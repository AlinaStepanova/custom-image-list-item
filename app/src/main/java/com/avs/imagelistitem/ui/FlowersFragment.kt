package com.avs.imagelistitem.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.avs.imagelistitem.*
import com.avs.imagelistitem.databinding.FragmentFlowersBinding
import com.avs.imagelistitem.recycler_view.ItemListener
import com.avs.imagelistitem.recycler_view.ItemsAdapter

class FlowersFragment : Fragment() {

    private lateinit var binding: FragmentFlowersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_flowers, container, false
        )
        val root: View = binding.root
        val adapter = this.context?.let {
            ItemsAdapter(
                ItemListener { item -> Log.d("items", item.toString()) }, context = it
            )
        }
        binding.rvRecyclerView.adapter = adapter
        val list: ArrayList<UIData> = setUpUIData()
        adapter?.submitList(list)
        return root
    }

    private fun setUpUIData(): ArrayList<UIData> {
        val list: ArrayList<UIData> = ArrayList()
        list.add(UIData(2, url2, "Title 2", "Some description 2"))
        list.add(UIData(3, url3, "Title 3", "Some description 3"))
        list.add(UIData(4, url4, "Title 4", "Some description 4"))
        list.add(UIData(5, url5, "Title 5", "Some description 5"))
        list.add(UIData(6, url6, "Title 6", "Some description 6"))
        list.add(UIData(7, url7, "Title 7", "Some description 7"))
        list.add(UIData(8, url8, "Title 8", "Some description 8"))
        list.add(UIData(9, url9, "Title 9", "Some description 9"))
        list.add(UIData(10, url10, "Title 10", "Some description 10"))
        return list
    }

    override fun onStop() {
        super.onStop()
        binding.rvRecyclerView.adapter = null
    }
}