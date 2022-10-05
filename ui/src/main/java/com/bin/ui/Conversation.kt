package com.bin.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.bin.presentation.NotesViewModel

@Composable
fun Conversation(viewModel: NotesViewModel) {
    LazyColumn {
        items(viewModel.messages) { message ->
            MessageCard(msg = message)
        }
    }
}