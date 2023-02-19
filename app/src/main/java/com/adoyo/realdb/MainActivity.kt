package com.adoyo.realdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.adoyo.realdb.ui.theme.RealDbTheme
import com.adoyo.screen.HomeScreen
import com.adoyo.screen.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RealDbTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: HomeScreenViewModel = hiltViewModel()
                    val data by viewModel.data

                    HomeScreen(
                        data = data,
                        filtered = viewModel.filtered.value,
                        name = viewModel.name.value,
                        objectId = viewModel.objectId.value,
                        onNameChanged = { viewModel.updateName(name = it) },
                        onObjectIdChanged = { viewModel.updateObjectId(id = it) },
                        onInsertClicked = { viewModel.insertPerson() },
                        onUpdateClicked = { viewModel.updatePerson() },
                        onDeleteClicked = { viewModel.deletePerson() },
                        onFilteredClicked = { viewModel.filteredData() })

                }
            }
        }
    }
}
