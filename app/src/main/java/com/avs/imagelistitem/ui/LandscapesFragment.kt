package com.avs.imagelistitem.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.avs.imagelistitem.*
import com.avs.imagelistitem.databinding.FragmentCitiesBinding
import com.avs.imagelistitem.recycler_view.shared.WidePicturesAdapter
import com.avs.imagelistitem.recycler_view.shared.ItemListener

class LandscapesFragment : Fragment() {

    private lateinit var binding: FragmentCitiesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_cities, container, false
        )
        val root: View = binding.root
        val adapter = this.context?.let {
            WidePicturesAdapter(
                ItemListener { item -> Log.d("items", item.toString()) }, context = it, isLandscape = true
            )
        }
        binding.rvRecyclerView.adapter = adapter
        val list: ArrayList<UIData> = setUpUIData()
        adapter?.submitList(list)
        return root
    }

    private fun setUpUIData(): ArrayList<UIData> {
        val list: ArrayList<UIData> = ArrayList()
        list.add(UIData(28, url28, "Mountains"))
        list.add(UIData(31, url31, "Grand Canyon"))
        list.add(UIData(29, url29, "River"))
        list.add(UIData(32, url32, "Toscana"))
        list.add(UIData(30, url30, "Mountains"))
        return list
    }

    override fun onStop() {
        super.onStop()
        binding.rvRecyclerView.adapter = null
    }
}