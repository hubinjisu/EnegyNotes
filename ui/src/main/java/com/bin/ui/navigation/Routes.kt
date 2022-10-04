package com.bin.ui.navigation

import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bin.presentation.AboutViewModel
import com.bin.presentation.RecordViewModel
import com.bin.presentation.SummaryViewModel
import com.bin.ui.Conversation
import com.bin.ui.RecordList

object SummaryRoute : NavRoute<SummaryViewModel> {
    override val route = "summary/"

    @Composable
    override fun viewModel(): SummaryViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: SummaryViewModel) {
//        Conversation(viewModel)
        RecordList()
    }
}

object RecordRoute : NavRoute<RecordViewModel> {
    override val route = "record/"

    @Composable
    override fun viewModel(): RecordViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: RecordViewModel) {
        RecordList()
    }
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