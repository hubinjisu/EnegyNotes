package com.bin.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bin.presentation.model.EnergyNoteView
import com.bin.presentation.model.EnergyTypeView
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import java.math.BigDecimal
import java.time.LocalDate
import java.util.Locale
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@ExperimentalPagerApi
@Composable
fun ScreenNotes(
    noteViews: List<EnergyNoteView>,
    onRecordClicked: () -> Unit,
    onAboutClicked: () -> Unit
) {
    // on below line we are creating variable for pager state.
    val pagerState = rememberPagerState()
    CustomScaffold(
        title = R.string.screen_notes,
        onNotesClicked = {},
        onRecordClicked = onRecordClicked,
        onAboutClicked = onAboutClicked
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            EnergyTabs(pagerState = pagerState)
            TabsContent(noteViews = noteViews, pagerState = pagerState)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@ExperimentalPagerApi
@Composable
fun EnergyTabs(pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    val tabData = listOf(
        EnergyTypeView.WATER.name.capitalize(),
        EnergyTypeView.ELECTRICITY.name.capitalize(),
        EnergyTypeView.GAS.name.capitalize(),
    )
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {
        tabData.forEachIndexed { index, text ->
            Tab(
                selected = pagerState.currentPage == index,
                text = { Text(text = text) },
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                })
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@ExperimentalPagerApi
@Composable
fun TabsContent(noteViews: List<EnergyNoteView>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = 3) { page ->
        when (page) {
            0 -> EnergyNotes(noteViews.filter { it.energyType == EnergyTypeView.WATER })
            1 -> EnergyNotes(noteViews.filter { it.energyType == EnergyTypeView.ELECTRICITY })
            2 -> EnergyNotes(noteViews.filter { it.energyType == EnergyTypeView.GAS })
        }
    }
}

private fun String.capitalize() =
    this.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

@OptIn(ExperimentalPagerApi::class)
@ExperimentalPagerApi
@Preview(name = "Light Mode")
@Composable
fun PeviewTab() {
    EnergyTabs(rememberPagerState())
}

@Preview(name = "Light Mode")
@Composable
fun PeviewContent() {
    EnergyNotes(
        listOf(
            EnergyNoteView(
                BigDecimal.valueOf(10),
                LocalDate.now(),
                EnergyTypeView.WATER,
                "cost",
                "totalCost"
            )
        )
    )
}

@OptIn(ExperimentalPagerApi::class)
@ExperimentalPagerApi
@Preview(name = "Light Mode")
@Composable
fun PeviewTabContent() {
    val testDaten = listOf(
        EnergyNoteView(
            BigDecimal.valueOf(10),
            LocalDate.now(),
            EnergyTypeView.WATER,
            "cost",
            "totalCost"
        ),
        EnergyNoteView(
            BigDecimal.valueOf(10),
            LocalDate.now(),
            EnergyTypeView.ELECTRICITY,
            "cost",
            "totalCost"
        ),
        EnergyNoteView(
            BigDecimal.valueOf(10),
            LocalDate.now(),
            EnergyTypeView.GAS,
            "cost",
            "totalCost"
        )
    )
    TabsContent(noteViews = testDaten, pagerState = rememberPagerState())
}