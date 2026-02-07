package com.example.menu_alta_listadoplanetstarwars.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.menu_alta_listadoplanetstarwars.data.dao.PersonDao
import com.example.menu_alta_listadoplanetstarwars.data.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val personDao: PersonDao
) : ViewModel() {

    val people = personDao.getAll()

    fun borrarPersona(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            personDao.delete(person)
        }
    }

    suspend fun getDetalle(id: Int) = personDao.getPersonWithPlanet(id)
}