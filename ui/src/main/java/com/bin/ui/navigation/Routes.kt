package com.bin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.bin.presentation.viewmodel.SummaryViewModel
import com.bin.presentation.viewmodel.NotesViewModel
import com.bin.presentation.viewmodel.RecordViewModel
import com.bin.ui.ScreenSummary
import com.bin.ui.ScreenNotes
import com.bin.ui.ScreenRecord
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
object NotesRoute : NavRoute<NotesViewModel> {
    override val route = "notes/"

    @Composable
    override fun viewModel(): NotesViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: NotesViewModel) = ScreenNotes(
        noteViews = requireNotNull(viewModel.energyNotes.collectAsState().value),
        onRecordClicked = { viewModel.navigateToRoute(RecordRoute.route) },
        onAboutClicked = { viewModel.navigateToRoute(SummaryRoute.route) }
    )
}

object RecordRoute : NavRoute<RecordViewModel> {
    override val route = "record/"

    @Composable
    override fun viewModel(): RecordViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: RecordViewModel) = ScreenRecord(
        onNotesClicked = { viewModel.navigateToRoute(NotesRoute.route) },
        onAboutClicked = { viewModel.navigateToRoute(SummaryRoute.route) },
        onRecordClicked = { waterReading, electricityReading, gasReading, recordDate ->
            viewModel.recordWaterNote(waterReading.toReadingData(), recordDate)
            viewModel.recordElectricityNote(electricityReading.toReadingData(), recordDate)
            viewModel.recordGasNote(gasReading.toReadingData(), recordDate)
            viewModel.navigateToRoute(NotesRoute.route)
        }
    )

    private fun String.toReadingData() = this.replace(",", ".").toBigDecimalOrNull()
}

@OptIn(ExperimentalPagerApi::class)
object SummaryRoute : NavRoute<SummaryViewModel> {
    override val route = "summary/"

    @Composable
    override fun viewModel(): SummaryViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: SummaryViewModel) = ScreenSummary(
        noteViews = requireNotNull(viewModel.energyNotes.collectAsState().value),
        onRecordClicked = { viewModel.navigateToRoute(RecordRoute.route) },
        onNotesClicked = { viewModel.navigateToRoute(NotesRoute.route) }
    )
}