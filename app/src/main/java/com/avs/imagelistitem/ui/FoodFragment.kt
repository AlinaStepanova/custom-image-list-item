package com.avs.imagelistitem.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.avs.imagelistitem.*
import com.avs.imagelistitem.databinding.FragmentFoodBinding
import com.avs.imagelistitem.recycler_view.shared.ItemListener
import com.avs.imagelistitem.recycler_view.FoodAdapter

class FoodFragment : Fragment() {

    private lateinit var binding: FragmentFoodBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_food, container, false
        )
        val root: View = binding.root
        val adapter = this.context?.let {
            FoodAdapter(
                ItemListener { }, context = it
            )
        }
        binding.rvRecyclerView.adapter = adapter
        val list: ArrayList<UIData> = setUpUIData()
        adapter?.submitList(list)
        return root
    }

    private fun setUpUIData(): ArrayList<UIData> {
        val list: ArrayList<UIData> = ArrayList()
        list.add(UIData(33, url33, "Pizza"))
        list.add(UIData(34, url34, "Peperoni"))
        list.add(UIData(35, url35, "Philadelphia roll"))
        list.add(UIData(36, url36, "Sushi"))
        list.add(UIData(37, url37, "Burger"))
        list.add(UIData(38, url38, "French fries"))
        list.add(UIData(39, url39, "Cake"))
        list.add(UIData(40, url40, "Macaroons"))
        return list
    }

    override fun onStop() {
        super.onStop()
        binding.rvRecyclerView.adapter = null
    }
}