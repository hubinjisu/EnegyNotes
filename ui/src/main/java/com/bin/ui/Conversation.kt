package com.bin.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.bin.presentation.DataViewModel

@Composable
fun Conversation(viewModel: DataViewModel) {
    LazyColumn {
        items(viewModel.messages) { message ->
            MessageCard(msg = message)
        }
    }
}