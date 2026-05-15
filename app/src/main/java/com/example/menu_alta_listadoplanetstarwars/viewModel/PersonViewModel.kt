package com.example.menu_alta_listadoplanetstarwars.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.menu_alta_listadoplanetstarwars.data.dao.PersonDao
import com.example.menu_alta_listadoplanetstarwars.data.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val personDao: PersonDao
) : ViewModel() {

    val people: StateFlow<List<Person>> = personDao.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    var search by mutableStateOf("")
        private set

    var personaSeleccionada by mutableStateOf<Person?>(null)
        private set

    var name by mutableStateOf("")
    var height by mutableStateOf("")
    var mass by mutableStateOf("")
    var hairColor by mutableStateOf("")
    var skinColor by mutableStateOf("")
    var eyeColor by mutableStateOf("")
    var birthYear by mutableStateOf("")
    var gender by mutableStateOf("")
    var imgStarWars by mutableIntStateOf(0)
    var planetId by mutableStateOf("")

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onSearchChange(value: String) {
        search = value
    }

    fun seleccionarPersona(person: Person) {
        personaSeleccionada = person
        cargarCampos(person)
    }

    fun cargarPersonaPorId(id: Int) {
        viewModelScope.launch {
            val person = withContext(Dispatchers.IO) {
                personDao.getById(id)
            }
            if (person != null) {
                personaSeleccionada = person
                cargarCampos(person)
            }
        }
    }

    fun añadirPersona(onSuccess: (String) -> Unit) {
        viewModelScope.launch {
            if (!validarCampos()) return@launch

            val nombreGuardado = name.trim()

            val exists = withContext(Dispatchers.IO) {
                personDao.exists(nombreGuardado)
            }

            if (exists) {
                errorMessage = "Ya existe un personaje con ese nombre"
                return@launch
            }

            val person = Person(
                name = nombreGuardado,
                height = height.trim(),
                mass = mass.trim(),
                hairColor = hairColor.trim(),
                skinColor = skinColor.trim(),
                eyeColor = eyeColor.trim(),
                birthYear = birthYear.trim(),
                gender = gender.trim(),
                imgStarWars = imgStarWars,
                planetId = planetId.toIntOrNull() ?: 0
            )

            withContext(Dispatchers.IO) {
                personDao.insert(person)
            }

            onSuccess(nombreGuardado)
        }
    }

    fun actualizarPersona(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val actual = personaSeleccionada ?: return@launch

            if (!validarCampos()) return@launch

            val personActualizada = actual.copy(
                name = name.trim(),
                height = height.trim(),
                mass = mass.trim(),
                hairColor = hairColor.trim(),
                skinColor = skinColor.trim(),
                eyeColor = eyeColor.trim(),
                birthYear = birthYear.trim(),
                gender = gender.trim(),
                imgStarWars = imgStarWars,
                planetId = planetId.toIntOrNull() ?: 0
            )

            withContext(Dispatchers.IO) {
                personDao.update(personActualizada)
            }

            limpiarCampos()
            onSuccess()
        }
    }

    fun borrarPersona(person: Person) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                personDao.delete(person)
            }
        }
    }

    private fun cargarCampos(person: Person) {
        name = person.name
        height = person.height
        mass = person.mass
        hairColor = person.hairColor
        skinColor = person.skinColor
        eyeColor = person.eyeColor
        birthYear = person.birthYear
        gender = person.gender
        imgStarWars = person.imgStarWars
        planetId = person.planetId.toString()
        errorMessage = null
    }

    private fun validarCampos(): Boolean {
        if (name.isBlank()) {
            errorMessage = "El nombre no puede estar vacío"
            return false
        }

        if (height.isBlank()) {
            errorMessage = "La altura no puede estar vacía"
            return false
        }

        if (gender.isBlank()) {
            errorMessage = "El género no puede estar vacío"
            return false
        }

        if (planetId.isBlank() || planetId.toIntOrNull() == null) {
            errorMessage = "El ID del planeta debe ser un número"
            return false
        }

        errorMessage = null
        return true
    }

    fun limpiarCampos() {
        name = ""
        height = ""
        mass = ""
        hairColor = ""
        skinColor = ""
        eyeColor = ""
        birthYear = ""
        gender = ""
        imgStarWars = 0
        planetId = ""
        personaSeleccionada = null
        errorMessage = null
    }
}