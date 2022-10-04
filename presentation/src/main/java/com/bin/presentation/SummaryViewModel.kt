package com.bin.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.bin.presentation.model.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SummaryViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator
) : ViewModel(), RouteNavigator by routeNavigator {
    private val _messages = mutableStateListOf<Message>()

    val messages: List<Message>
        get() = _messages

    init {
        _messages.addAll(SampleData.conversationSample)
    }
}