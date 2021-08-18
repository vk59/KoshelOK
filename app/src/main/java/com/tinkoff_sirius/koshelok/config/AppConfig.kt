package com.tinkoff_sirius.koshelok.config

import com.tinkoff_sirius.koshelok.model.MainItem

object AppConfig {
    const val WAIT_FOR_PUSH_AGAIN = 3000L
    val headerExample = listOf<MainItem>(
    MainItem.Header("Кошелек 1", "60 000", "170 000", "61 400", "230 000")
    )
}
