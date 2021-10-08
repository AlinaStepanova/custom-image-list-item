 package com.avs.imagelistitem.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.avs.imagelistitem.*
import com.avs.imagelistitem.databinding.FragmentHomeBinding
import com.avs.imagelistitem.recycler_view.shared.HomeAdapter
import com.avs.imagelistitem.recycler_view.shared.ItemListener

 class ArtworksFragment : Fragment() {

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
            HomeAdapter(ItemListener { }, context = it, isArtworks = true)
        }
        binding.rvRecyclerView.adapter = adapter
        val list = setUpUIData()
        adapter?.submitList(list)
        return root
    }

    private fun setUpUIData(): ArrayList<UIData> {
        val list: ArrayList<UIData> = ArrayList()
        list.add(UIData(41, url41, "Woman with a Parasol"))
        list.add(UIData(42, url42, "Sunflowers "))
        list.add(UIData(43, url43, "La Scapigliata"))
        list.add(UIData(44, url44, "Girl with a Pearl Earring"))
        list.add(UIData(45, url45, "Empty nest"))
        list.add(UIData(46, url46, "The Dream"))
        return list
    }

    override fun onStop() {
        super.onStop()
        binding.rvRecyclerView.adapter = null
    }
}