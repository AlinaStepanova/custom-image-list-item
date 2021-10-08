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
import com.avs.imagelistitem.recycler_view.ItemListener

class CitiesFragment : Fragment() {

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
        list.add(UIData(22, url22, "Rome"))
        list.add(UIData(24, url24, "Barcelona"))
        list.add(UIData(25, url25, "London"))
        list.add(UIData(23, url23, "Paris"))
        list.add(UIData(26, url26, "Santorini"))
        list.add(UIData(27, url27, "Phuket"))
        return list
    }

    override fun onStop() {
        super.onStop()
        binding.rvRecyclerView.adapter = null
    }
}