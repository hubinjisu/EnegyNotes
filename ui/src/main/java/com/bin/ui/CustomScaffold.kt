package com.bin.ui

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bin.ui.ui.theme.Blue_100
import com.bin.ui.ui.theme.Blue_400
import com.bin.ui.ui.theme.Cool_Grey_100
import com.bin.ui.ui.theme.EnergyNotesTheme
import com.bin.ui.ui.theme.HALF_UNIT
import com.bin.ui.ui.theme.ONE_UNIT
import com.bin.ui.ui.theme.QUARTER_UNIT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CustomScaffold(
    @StringRes title: Int,
    onNotesClicked: () -> Unit,
    onRecordClicked: () -> Unit,
    onAboutClicked: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { NotesTopBar(false, title, {}, scaffoldState) },
        content = { content() },
        drawerContent = {
            DrawerContent(
                currentTitle = title,
                onNotesClicked = onNotesClicked,
                onRecordClicked = onRecordClicked,
                onAboutClicked = onAboutClicked,
                scaffoldState = scaffoldState,
                scope = scope,
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CustomScaffold(
    @StringRes title: Int,
    onBackClicked: () -> Unit,
    content: @Composable () -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { NotesTopBar(true, title, onBackClicked, scaffoldState) },
        content = { content() }
    )
}

@Composable
fun DrawerContent(
    @StringRes currentTitle: Int,
    onNotesClicked: () -> Unit,
    onRecordClicked: () -> Unit,
    onAboutClicked: () -> Unit,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Cool_Grey_100)
    ) {
        Image(
            painter = painterResource(id = R.drawable.navi_header),
            modifier = Modifier
                .fillMaxWidth()
                .size(167.dp),
            contentDescription = "Banner",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.padding(QUARTER_UNIT))
        DrawerRow(
            text = R.string.screen_notes,
            icon = R.drawable.ic_notes,
            currentTitle = currentTitle,
            onClicked = onNotesClicked,
            scaffoldState = scaffoldState,
            scope = scope
        )
        DrawerRow(
            text = R.string.screen_summary,
            icon = R.drawable.ic_about,
            currentTitle = currentTitle,
            onClicked = onAboutClicked,
            scaffoldState = scaffoldState,
            scope = scope
        )
        DrawerRow(
            text = R.string.screen_record,
            icon = R.drawable.ic_record,
            currentTitle = currentTitle,
            onClicked = onRecordClicked,
            scaffoldState = scaffoldState,
            scope = scope
        )
    }
}

@Composable
fun DrawerRow(
    @StringRes text: Int,
    @DrawableRes icon: Int,
    @StringRes currentTitle: Int,
    onClicked: () -> Unit,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {
    val color = if (text == currentTitle) {
        Blue_100
    } else {
        Cool_Grey_100
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = HALF_UNIT)
            .background(color)
            .clickable {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
                onClicked()
            }
    ) {
        Icon(
            modifier = Modifier.padding(ONE_UNIT),
            painter = painterResource(id = icon),
            contentDescription = "drawer"
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = ONE_UNIT)
                .align(alignment = Alignment.CenterVertically),
            text = stringResource(id = text),
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.padding(QUARTER_UNIT))
    }
}

@Composable
private fun NotesTopBar(
    hasBackNavgation: Boolean,
    @StringRes title: Int,
    onBackClicked: () -> Unit,
    scaffoldState: ScaffoldState
) {
    TopAppBar(
        title = { Text(stringResource(title)) },
        navigationIcon = {
            if (hasBackNavgation) {
                BackButton(onBackClicked)
            } else {
                DrawerButton(scaffoldState)
            }
        },
        backgroundColor = Color.White
    )
}

@Composable
private fun DrawerButton(scaffoldState: ScaffoldState) {
    val scope = rememberCoroutineScope()
    IconButton(
        onClick = {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        }
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "drawer",
            tint = Blue_400
        )
    }
}

@Composable
private fun BackButton(onBackClicked: () -> Unit) {
    IconButton(onClick = { onBackClicked() }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "back",
            tint = Blue_400
        )
    }
}

@Preview("ScaffoldTopBar", device = Devices.NEXUS_5X, showBackground = true)
@Composable
private fun ScaffoldPreview() {
    EnergyNotesTheme {
        CustomScaffold(title = R.string.app_name, onBackClicked = {}, content = {})
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Preview("DrawerPreview", device = Devices.NEXUS_5X, showBackground = true)
@Composable
private fun DrawerPreview() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    scope.launch {
        scaffoldState.drawerState.open()
    }

    EnergyNotesTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            DrawerContent(
                currentTitle = R.string.screen_summary,
                onNotesClicked = {},
                onRecordClicked = {},
                onAboutClicked = {},
                scaffoldState = scaffoldState,
                scope = scope
            )
        }
    }
}
