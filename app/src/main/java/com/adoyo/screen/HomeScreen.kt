@file:OptIn(ExperimentalMaterial3Api::class)

package com.adoyo.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.adoyo.model.Person
import io.realm.kotlin.types.RealmInstant
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    data: List<Person>,
    filtered: Boolean,
    name: String,
    objectId: String,
    onNameChanged: (String) -> Unit,
    onObjectIdChanged: (String) -> String,
    onInsertClicked: () -> Unit,
    onUpdateClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    onFilteredClicked: () -> Unit
) {
    Scaffold(
        content = {}
    )
}

@Composable
fun HomeContent(
    data: List<Person>,
    filtered: Boolean,
    name: String,
    objectId: String,
    onNameChanged: (String) -> Unit,
    onObjectIdChanged: (String) -> Unit,
    onInsertClicked: () -> Unit,
    onUpdateClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    onFilteredClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Row {
                TextField(
                    modifier = Modifier.weight(1f),
                    value = objectId,
                    onValueChange = onObjectIdChanged,
                    placeholder = { Text(text = "Object Id") }
                )
                Spacer(modifier = Modifier.width(12.dp))
                TextField(
                    modifier = Modifier.weight(1f),
                    value = name,
                    onValueChange = onNameChanged,
                    placeholder = { Text(text = "Object Id") }
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(state = rememberScrollState()),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = onInsertClicked) {
                        Text(text = "Add")
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Button(onClick = onUpdateClicked) {
                        Text(text = "Update")
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Button(onClick = onDeleteClicked) {
                        Text(text = "Delete")
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Button(onClick = onFilteredClicked) {
                        Text(text = if (filtered) "Clear" else "Filter")
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(items = data, key = { it._id.toHexString() }) {

                        }

                    }
                }
            }
        }

    }
}

@Composable
fun PersonView(id: String, name: String, timeStamp: RealmInstant) {
    Row(modifier = Modifier.padding(bottom = 24.dp)) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )
            )
            SelectionContainer {
                Text(
                    text = id,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = SimpleDateFormat(
                        "hh:mm: a",
                        Locale.getDefault()
                    ).format(Date.from(timeStamp.toInstant())).uppercase(Locale.ROOT),
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Normal
            )
        )

    }
}

fun RealmInstant.toInstant(): Instant {
    val sec: Long = this.epochSeconds
    val nano: Int = this.nanosecondsOfSecond
    return if (sec >= 0) {
        Instant.ofEpochSecond(sec, nano.toLong())
    } else {
        Instant.ofEpochSecond(sec - 1, 1_000_000 + nano.toLong())
    }
}