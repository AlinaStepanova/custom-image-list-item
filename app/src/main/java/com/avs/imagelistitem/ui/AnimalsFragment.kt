package com.avs.imagelistitem.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.avs.imagelistitem.*
import com.avs.imagelistitem.databinding.FragmentAnimalsBinding
import com.avs.imagelistitem.recycler_view.AnimalsAdapter
import com.avs.imagelistitem.recycler_view.ItemListener

class AnimalsFragment : Fragment() {

    private lateinit var binding: FragmentAnimalsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_animals, container, false
        )
        val root: View = binding.root
        val adapter = this.context?.let {
            AnimalsAdapter(
                ItemListener { item -> Log.d("items", item.toString()) }
            )
        }
        binding.rvRecyclerView.adapter = adapter
        val list: ArrayList<UIData> = setUpUIData()
        adapter?.submitList(list)
        return root
    }

    private fun setUpUIData(): ArrayList<UIData> {
        val list: ArrayList<UIData> = ArrayList()
        list.add(UIData(2, url2))
        list.add(UIData(3, url3))
        list.add(UIData(4, url4))
        list.add(UIData(5, url5))
        list.add(UIData(6, url6))
        list.add(UIData(7, url7))
        return list
    }

    override fun onStop() {
        super.onStop()
        binding.rvRecyclerView.adapter = null
    }
}