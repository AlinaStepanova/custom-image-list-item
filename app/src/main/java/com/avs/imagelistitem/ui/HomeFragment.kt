package com.avs.imagelistitem.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.avs.imagelistitem.*
import com.avs.imagelistitem.databinding.FragmentHomeBinding
import com.avs.imagelistitem.recycler_view.HomeAdapter
import com.avs.imagelistitem.recycler_view.ItemListener
import com.avs.imagelistitem.recycler_view.ItemsAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        val root: View = binding.root
        val adapter = this.context?.let {
            HomeAdapter(
                ItemListener {
                    findNavController().navigate(R.id.action_homeFragment_to_flowersFragment)
                }, context = it
            )
        }
        binding.rvRecyclerView.adapter = adapter
        val list = setUpUIData()
        adapter?.submitList(list)
        return root
    }

    private fun setUpUIData(): ArrayList<UIData> {
        val list: ArrayList<UIData> = ArrayList()
        list.add(UIData(1, url, "Title 1"))
        list.add(UIData(2, url2, "Title 2"))
        list.add(UIData(3, url9, "Title 3"))
        list.add(UIData(4, url10, "Title 4"))
        return list
    }

    override fun onStop() {
        super.onStop()
        binding.rvRecyclerView.adapter = null
    }
}