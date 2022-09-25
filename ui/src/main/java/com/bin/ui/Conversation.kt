package com.bin.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.bin.presentation.DataViewModel
import com.bin.ui.ui.theme.EnergyNotesTheme

@Composable
fun Conversation(viewModel: DataViewModel) {
    LazyColumn {
        items(viewModel.messages) { message ->
            MessageCard(msg = message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    val viewModel by remember { mutableStateOf(DataViewModel()) }
    EnergyNotesTheme {
        Conversation(viewModel)
    }
}