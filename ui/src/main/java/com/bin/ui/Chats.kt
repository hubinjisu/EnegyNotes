package com.bin.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.bin.ui.ui.theme.EnergyNotesTheme
import com.jaikeerthick.composable_graphs.color.Gradient2
import com.jaikeerthick.composable_graphs.color.Gradient3
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
fun BarChart() {
    val style = BarGraphStyle(
        visibility = BarGraphVisibility(
            isYAxisLabelVisible = true
        )
    )
    BarGraph(
        dataList = listOf(20, 30, 10, 60, 35),
        style = style
    )
}

@Composable
fun LineChart() {
    val style = LineGraphStyle(
        visibility = LinearGraphVisibility(
            isHeaderVisible = true,
            isYAxisLabelVisible = false,
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
        xAxisData = listOf("Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat").map {
            GraphData.String(it)
        }, // xAxisData : List<GraphData>, and GraphData accepts both Number and String types
        yAxisData = listOf(200, 40, 60, 450, 700, 30, 50),
        style = style
    )
}

@Preview
@Composable
fun PreviewBarChart() {
    EnergyNotesTheme {
        BarChart()
    }
}

@Preview
@Composable
fun PreviewLineChart() {
    EnergyNotesTheme {
        LineChart()
    }
}