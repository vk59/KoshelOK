package com.tinkoffsirius.koshelok.utils

sealed class Event {
    class Success : Event()
    class Error(val throwable: Throwable) : Event()
    class Loading : Event()
}
