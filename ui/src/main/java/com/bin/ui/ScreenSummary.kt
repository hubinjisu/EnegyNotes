package com.bin.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bin.presentation.model.EnergyNoteView
import com.bin.presentation.model.EnergyTypeView
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import java.math.BigDecimal

@OptIn(ExperimentalPagerApi::class)
@ExperimentalPagerApi
@Composable
fun ScreenSummary(
    noteViews: List<EnergyNoteView>,
    onRecordClicked: () -> Unit,
    onNotesClicked: () -> Unit
) {
    val pagerState = rememberPagerState()
    CustomScaffold(
        title = R.string.screen_summary,
        onNotesClicked = onNotesClicked,
        onRecordClicked = onRecordClicked,
        onAboutClicked = {}
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            EnergyTabs(pagerState = pagerState)
            if (noteViews.isEmpty()) {
                Text(text = "Data is empty!")
            } else {
                SummaryTabsContent(noteViews = noteViews, pagerState = pagerState)
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@ExperimentalPagerApi
@Composable
fun SummaryTabsContent(noteViews: List<EnergyNoteView>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = 3) { page ->
        val notesData = when (page) {
            0 -> noteViews.filter { it.energyType == EnergyTypeView.WATER }.extractMonthlyData()
            1 -> noteViews.filter { it.energyType == EnergyTypeView.ELECTRICITY }.extractMonthlyData()
            2 -> noteViews.filter { it.energyType == EnergyTypeView.GAS }.extractMonthlyData()
            else -> emptyList()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val size = notesData.size
            BarChart(notesData.subList(size - 12, size))
            LineChart(notesData)
        }
    }
}

private fun List<EnergyNoteView>.extractMonthlyData() = this.zipWithNext { a, b ->
    val monthlyUsage = b.reading - a.reading
    val monthlyCost = BigDecimal.valueOf(b.cost.toDouble()) - BigDecimal.valueOf(a.cost.toDouble())
    b.copy(reading = monthlyUsage, cost = "$monthlyCost €")
}