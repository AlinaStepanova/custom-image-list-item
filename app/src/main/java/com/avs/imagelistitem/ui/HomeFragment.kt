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
                ItemListener { data ->
                    if (data.id == 3) {
                        findNavController().navigate(R.id.action_homeFragment_to_animalsFragment)
                    } else {
                        findNavController().navigate(R.id.action_homeFragment_to_flowersFragment)
                    }
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
        list.add(UIData(1, url, "Flowers"))
        list.add(UIData(2, url11, "Cities"))
        list.add(UIData(3, url12, "Animals"))
        list.add(UIData(4, url13, "Landscapes"))
        list.add(UIData(5, url14, "Food"))
        list.add(UIData(6, url15, "Artworks"))
        return list
    }

    override fun onStop() {
        super.onStop()
        binding.rvRecyclerView.adapter = null
    }
}