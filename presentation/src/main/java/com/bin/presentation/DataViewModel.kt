package com.bin.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.bin.presentation.model.Message


class DataViewModel : ViewModel() {

    private val _messages = mutableStateListOf<Message>()

    val messages: List<Message>
        get() = _messages

    init {
        _messages.addAll(SampleData.conversationSample)
    }
}