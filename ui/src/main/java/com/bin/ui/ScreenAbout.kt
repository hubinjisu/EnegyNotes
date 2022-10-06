package com.bin.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ScreenAbout(
    onRecordClicked: () -> Unit,
    onNotesClicked: () -> Unit
) {
    CustomScaffold(
        title = R.string.screen_about,
        onNotesClicked = onNotesClicked,
        onRecordClicked = onRecordClicked,
        onAboutClicked = {}
    ) {
        Text(text = "About")
    }
}