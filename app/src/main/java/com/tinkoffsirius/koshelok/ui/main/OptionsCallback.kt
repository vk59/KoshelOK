package com.tinkoffsirius.koshelok.ui.main

import com.tinkoffsirius.koshelok.ui.main.adapters.model.MainItem

interface OptionsCallback {
    fun deleteItem(element: MainItem.Transaction)

    fun editItem(element: MainItem.Transaction)
}