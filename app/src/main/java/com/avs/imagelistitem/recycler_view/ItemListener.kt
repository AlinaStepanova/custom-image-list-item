package com.avs.imagelistitem.recycler_view

import com.avs.imagelistitem.UIData

class ItemListener(
    val clickListener: (uiData: UIData) -> Unit,
) {
    fun onClick(uiData: UIData) = clickListener(uiData)
}