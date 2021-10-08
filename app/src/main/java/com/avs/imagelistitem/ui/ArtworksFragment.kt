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
        list.add(UIData(1, url, "Flowers 1"))
        list.add(UIData(2, url11, "Cities 1"))
        list.add(UIData(3, url12, "Animals 1"))
        list.add(UIData(4, url13, "Landscapes 1"))
        list.add(UIData(5, url14, "Food 1"))
        list.add(UIData(6, url15, "Artworks 1"))
        return list
    }

    override fun onStop() {
        super.onStop()
        binding.rvRecyclerView.adapter = null
    }
}