package com.bin.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bin.presentation.model.EnergyNoteView
import com.bin.ui.DateTimeUtil.formatLocalDate
import com.jaikeerthick.composable_graphs.color.Gradient2
import com.jaikeerthick.composable_graphs.color.Gradient3
import com.jaikeerthick.composable_graphs.color.GraphAccent
import com.jaikeerthick.composable_graphs.color.GraphAccent2
import com.jaikeerthick.composable_graphs.color.LinearGraphColors
import com.jaikeerthick.composable_graphs.color.PointHighlight2
import com.jaikeerthick.composable_graphs.composables.BarGraph
import com.jaikeerthick.composable_graphs.composables.LineGraph
import com.jaikeerthick.composable_graphs.data.GraphData
import com.jaikeerthick.composable_graphs.style.BarGraphStyle
import com.jaikeerthick.composable_graphs.style.BarGraphVisibility
import com.jaikeerthick.composable_graphs.style.LineGraphStyle
import com.jaikeerthick.composable_graphs.style.LinearGraphVisibility


@Composable
fun BarChart(nodesData: List<EnergyNoteView>) {
    val numberList = nodesData.map { it.reading }.zipWithNext { a, b -> b - a }
    val style = BarGraphStyle(
        visibility = BarGraphVisibility(
            isYAxisLabelVisible = true
        )
    )
    BarGraph(
        dataList = numberList,
        style = style,
        header = {
            Column {
                Text(
                    text = "The monthly energy usage",
                    color = GraphAccent,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    )
}

@Composable
fun LineChart(nodesData: List<EnergyNoteView>) {
    val recordTimeRowData = nodesData.map { formatLocalDate(it.recordDate) }
    val xAxisData = recordTimeRowData.subList(1, recordTimeRowData.size)
    val yAxisData = nodesData.map { it.reading }.zipWithNext { a, b -> b - a }
    Box(modifier = Modifier.fillMaxWidth()) {
        val clickedValue: MutableState<Pair<Any, Any>?> = remember { mutableStateOf(null) }
        val style = LineGraphStyle(
            visibility = LinearGraphVisibility(
                isHeaderVisible = true,
                isYAxisLabelVisible = true,
                isXAxisLabelVisible = false,
                isCrossHairVisible = true
            ),
            colors = LinearGraphColors(
                lineColor = GraphAccent2,
                pointColor = GraphAccent2,
                clickHighlightColor = PointHighlight2,
                fillGradient = Brush.verticalGradient(
                    listOf(Gradient3, Gradient2)
                )
            )
        )
        LineGraph(
            xAxisData = xAxisData.map {
                GraphData.String(it)
            },
            yAxisData = yAxisData.toList(),
            style = style,
            onPointClicked = {
                clickedValue.value = it
            },
            header = {
                Column {
                    Text(
                        text = "The monthly ${nodesData[0].energyType.name.lowercase()} usage",
                        color = GraphAccent,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        )
        clickedValue.value?.let {
            Row(
                modifier = Modifier
                    .padding(all = 5.dp)
            ) {
                Text(
                    text = "${it.first}: ${it.second}",
                    color = GraphAccent2,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}