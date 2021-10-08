package com.avs.imagelistitem.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.avs.imagelistitem.*
import com.avs.imagelistitem.databinding.FragmentAnimalsBinding
import com.avs.imagelistitem.recycler_view.AnimalsAdapter
import com.avs.imagelistitem.recycler_view.shared.ItemListener

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
            context?.let { context ->
                AnimalsAdapter(
                    ItemListener { }, context
                )
            }
        }
        binding.rvRecyclerView.adapter = adapter
        val list: ArrayList<UIData> = setUpUIData()
        adapter?.submitList(list)
        return root
    }

    private fun setUpUIData(): ArrayList<UIData> {
        val list: ArrayList<UIData> = ArrayList()
        list.add(UIData(2, url16, "Parrot"))
        list.add(UIData(3, url17, "Leopard"))
        list.add(UIData(4, url20, "Giraffe"))
        list.add(UIData(5, url19, "Dolphin"))
        list.add(UIData(6, url21, "Lovely dog"))
        list.add(UIData(7, url18, "Panda"))
        return list
    }

    override fun onStop() {
        super.onStop()
        binding.rvRecyclerView.adapter = null
    }
}