package com.bin.ui

import androidx.compose.runtime.Composable
import com.bin.presentation.model.EnergyNoteView

@Composable
fun ScreenNotes(
    messages: List<EnergyNoteView>,
    onRecordClicked: () -> Unit,
    onAboutClicked: () -> Unit
) {
    CustomScaffold(
        title = R.string.screen_notes,
        onNotesClicked = {},
        onRecordClicked = onRecordClicked,
        onAboutClicked = onAboutClicked
    ) {
        EnergyNotes(messages)
    }
}