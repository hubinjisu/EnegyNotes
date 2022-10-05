package com.bin.ui.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bin.presentation.AboutViewModel
import com.bin.presentation.NotesViewModel
import com.bin.presentation.RecordViewModel
import com.bin.ui.Conversation
import com.bin.ui.ScreenRecord

object NotesRoute : NavRoute<NotesViewModel> {
    override val route = "notes/"

    @Composable
    override fun viewModel(): NotesViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: NotesViewModel) {
        Conversation(viewModel)
    }
}

object RecordRoute : NavRoute<RecordViewModel> {
    override val route = "record/"

    @Composable
    override fun viewModel(): RecordViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: RecordViewModel) = ScreenRecord(
        onNotesClicked = {},
        onAboutClicked = {}
    )
}

object AboutRoute : NavRoute<AboutViewModel> {
    override val route = "about/"

    @Composable
    override fun viewModel(): AboutViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: AboutViewModel) {
        Text(text = "About Energy Notes")
    }
}