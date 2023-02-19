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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adoyo.model.Person

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


                }
            }
        }
    }

}

}