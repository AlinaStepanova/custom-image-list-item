package com.avs.imagelistitem.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.avs.imagelistitem.*
import com.avs.imagelistitem.databinding.FragmentFlowersBinding
import com.avs.imagelistitem.recycler_view.shared.ItemListener
import com.avs.imagelistitem.recycler_view.FlowersAdapter

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
            FlowersAdapter(
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
        list.add(UIData(2, url2, "Lily", "Lilium candidum"))
        list.add(UIData(3, url3, "Gerbera", "Gerbera jamesonii in Orange"))
        list.add(UIData(4, url4, "Hydrangea", "Hydrangea macrophylla"))
        list.add(UIData(5, url5, "Hyacinth", "Hyacinthus orientalis"))
        list.add(UIData(6, url6, "Syringa", "Syringa vulgaris"))
        list.add(UIData(7, url7, "Rose", "Rosa pendulina"))
        list.add(UIData(8, url8, "Peony", "Paeonia suffruticosa"))
        list.add(UIData(9, url9, "Narcissus", "Narcissus poeticus"))
        list.add(UIData(10, url10, "Tulips", "Tulipa × gesneriana"))
        return list
    }

    override fun onStop() {
        super.onStop()
        binding.rvRecyclerView.adapter = null
    }
}