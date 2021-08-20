package com.tinkoff_sirius.koshelok.ui.main

import com.tinkoff_sirius.koshelok.ui.main.adapters.model.MainItem

interface OptionsCallback {
    fun deleteItem(element: MainItem.Transaction)

    fun editItem(element: MainItem.Transaction)
}
