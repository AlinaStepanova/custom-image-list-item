package com.avs.imagelistitem

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.avs.imagelistitem.databinding.ActivityMainBinding
import com.avs.imagelistitem.recycler_view.ItemListener
import com.avs.imagelistitem.recycler_view.ItemsAdapter

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val list: ArrayList<UIData> = ArrayList(8)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        list.add(UIData(url, "Title 1", "Some description 1"))
        list.add(UIData(url2, "Title 2", "Some description 2"))
        list.add(UIData(url3, "Title 3", "Some description 3"))
        list.add(UIData(url4, "Title 4", "Some description 4"))
        list.add(UIData(url5, "Title 5", "Some description 5"))
        list.add(UIData(url6, "Title 6", "Some description 6"))
        list.add(UIData(url7, "Title 7", "Some description 7"))
        list.add(UIData(url8, "Title 8", "Some description 8"))
        list.add(UIData(url9, "Title 9", "Some description 9"))
        list.add(UIData(url10, "Title 10", "Some description 10"))
        val adapter = ItemsAdapter(
            ItemListener { item -> Log.d("items", item.toString()) }
        , context = this
        )
        binding.rvRecyclerView.adapter = adapter
        adapter.submitList(list)
    }
}