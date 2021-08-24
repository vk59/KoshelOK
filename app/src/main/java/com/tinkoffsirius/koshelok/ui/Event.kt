package com.tinkoffsirius.koshelok.ui

sealed class Event {
    class Success : Event()
    class Error(val throwable: Throwable) : Event()
    class Loading : Event()
}
