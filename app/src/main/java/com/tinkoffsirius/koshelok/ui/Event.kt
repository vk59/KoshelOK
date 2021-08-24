package com.tinkoffsirius.koshelok.ui

sealed class Event {
    class Success(val data: List<Any>) : Event()
    class Error(val throwable: Throwable) : Event()
    class Loading() : Event()
}
