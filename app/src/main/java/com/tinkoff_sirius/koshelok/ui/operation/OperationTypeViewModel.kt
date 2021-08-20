package com.tinkoff_sirius.koshelok.ui.operation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OperationTypeViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _name: MutableLiveData<String> = MutableLiveData()
    val name: LiveData<String> get() = _name
}
