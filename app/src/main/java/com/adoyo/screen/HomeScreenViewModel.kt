package com.adoyo.screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adoyo.data.MongoRepository
import com.adoyo.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repository: MongoRepository) :
    ViewModel() {
    var name = mutableStateOf("")
    var objectId = mutableStateOf("")
    var filtered = mutableStateOf(false)
    var data = mutableStateOf(emptyList<Person>())


    fun updateName(name: String) {
        this.name.value = name
    }

    fun updateObjectId(id: String) {
        this.objectId.value = id
    }

    fun insertPerson() {
        viewModelScope.launch(Dispatchers.IO) {
            if (name.value.isNotEmpty()) {
                repository.insertPerson(person = Person().apply {
                    name = this@HomeScreenViewModel.name.value
                })
            }
        }
    }

    fun deletePerson() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                repository.deletePerson(id = org.mongodb.kbson.ObjectId(hexString = objectId.value))
            }
        }
    }

    fun filteredData() {
        viewModelScope.launch(Dispatchers.IO) {
            if (filtered.value) {
                repository.getData().collect{
                    filtered.value = false
                    name.value = ""
                    data.value = it
                }
             } else {
                 repository.filterData(name = name.value).collect{
                     filtered.value = true
                     data.value = it
                 }
            }
        }
    }

}