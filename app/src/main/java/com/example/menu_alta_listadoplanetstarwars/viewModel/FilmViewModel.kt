package com.example.menu_alta_listadoplanetstarwars.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.menu_alta_listadoplanetstarwars.data.dao.FilmDAO
import com.example.menu_alta_listadoplanetstarwars.data.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(
    private val filmDao: FilmDAO
) : ViewModel() {

    val films: StateFlow<List<Film>> = filmDao.getAll()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    var search by mutableStateOf("")
        private set

    var peliculaSeleccionada by mutableStateOf<Film?>(null)
        private set

    var filmId by mutableStateOf("")
    var title by mutableStateOf("")
    var episode by mutableStateOf("")
    var director by mutableStateOf("")
    var releaseDate by mutableStateOf("")
    var era by mutableStateOf("")
    var rating by mutableStateOf("")
    var openingText by mutableStateOf("")
    var isOriginalTrilogy by mutableStateOf(false)

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onSearchChange(value: String) {
        search = value
    }

    fun seleccionarPelicula(film: Film) {
        peliculaSeleccionada = film
        cargarCampos(film)
    }

    fun añadirPelicula(onSuccess: () -> Unit) {
        viewModelScope.launch {
            if (!validarCampos()) return@launch

            val id = filmId.toIntOrNull() ?: return@launch

            val existsId = withContext(Dispatchers.IO) {
                filmDao.exists(id)
            }

            if (existsId) {
                errorMessage = "Ya existe una película con ese ID"
                return@launch
            }

            val existsTitleDate = withContext(Dispatchers.IO) {
                filmDao.existsByTitleAndDate(
                    title.trim(),
                    releaseDate.trim()
                )
            }

            if (existsTitleDate) {
                errorMessage = "Esa película ya existe"
                return@launch
            }

            val film = Film(
                filmId = id,
                title = title.trim(),
                episode = episode.trim(),
                director = director.trim(),
                releaseDate = releaseDate.trim(),
                era = era.trim(),
                rating = rating.trim(),
                openingText = openingText.trim(),
                isOriginalTrilogy = isOriginalTrilogy
            )

            withContext(Dispatchers.IO) {
                filmDao.insert(film)
            }

            limpiarCampos()
            onSuccess()
        }
    }

    fun actualizarPelicula(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val actual = peliculaSeleccionada ?: return@launch

            if (!validarCampos()) return@launch

            val actualizada = actual.copy(
                filmId = actual.filmId,
                title = title.trim(),
                episode = episode.trim(),
                director = director.trim(),
                releaseDate = releaseDate.trim(),
                era = era.trim(),
                rating = rating.trim(),
                openingText = openingText.trim(),
                isOriginalTrilogy = isOriginalTrilogy
            )

            withContext(Dispatchers.IO) {
                filmDao.update(actualizada)
            }

            limpiarCampos()
            onSuccess()
        }
    }

    fun borrarPelicula(film: Film) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                filmDao.delete(film)
            }
        }
    }

    private fun cargarCampos(film: Film) {
        filmId = film.filmId.toString()
        title = film.title
        episode = film.episode
        director = film.director
        releaseDate = film.releaseDate
        era = film.era
        rating = film.rating
        openingText = film.openingText
        isOriginalTrilogy = film.isOriginalTrilogy
        errorMessage = null
    }

    private fun validarCampos(): Boolean {
        if (filmId.isBlank() || filmId.toIntOrNull() == null) {
            errorMessage = "El ID debe ser numérico"
            return false
        }

        if (title.isBlank()) {
            errorMessage = "El título no puede estar vacío"
            return false
        }

        if (director.isBlank()) {
            errorMessage = "El director no puede estar vacío"
            return false
        }

        if (releaseDate.isBlank()) {
            errorMessage = "La fecha de estreno no puede estar vacía"
            return false
        }

        errorMessage = null
        return true
    }
    fun cargarPeliculaPorId(id: Int) {
        viewModelScope.launch {
            val film = withContext(Dispatchers.IO) {
                filmDao.getFilmById(id)
            }

            if (film != null) {
                seleccionarPelicula(film)
            } else {
                errorMessage = "No se ha encontrado la película"
            }
        }
    }

    fun limpiarCampos() {
        filmId = ""
        title = ""
        episode = ""
        director = ""
        releaseDate = ""
        era = ""
        rating = ""
        openingText = ""
        isOriginalTrilogy = false
        peliculaSeleccionada = null
        errorMessage = null
    }
}