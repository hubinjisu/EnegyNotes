package com.bin.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.bin.presentation.model.EnergyNoteView

@Composable
fun EnergyNotes(noteViews: List<EnergyNoteView>) {
    LazyColumn {
        items(noteViews) { noteView ->
            NoteCard(noteView = noteView)
        }
    }
}