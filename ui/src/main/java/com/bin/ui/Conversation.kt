package com.bin.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.bin.presentation.DataViewModel
import com.bin.presentation.SummaryViewModel

@Composable
fun Conversation(viewModel: SummaryViewModel) {
    LazyColumn {
        items(viewModel.messages) { message ->
            MessageCard(msg = message)
        }
    }
}