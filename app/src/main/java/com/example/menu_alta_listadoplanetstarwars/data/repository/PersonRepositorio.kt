package com.example.menu_alta_listadoplanetstarwars.data.repository

import com.example.menu_alta_listadoplanetstarwars.data.dao.PersonDao
import com.example.menu_alta_listadoplanetstarwars.data.model.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepositorio @Inject constructor(
    private val personDao: PersonDao
) {

    var personaSeleccionada: Person? = null

    fun seleccionarPersona(person: Person) {
        personaSeleccionada = person
    }

    fun getPeople(): Flow<List<Person>> = personDao.getAll()

    fun searchPeople(search: String): Flow<List<Person>> = personDao.searchPeople(search)

    suspend fun add(person: Person): Boolean {
        return withContext(Dispatchers.IO) {
            val exists = personDao.exists(person.name)

            if (exists) {
                false
            } else {
                personDao.insert(person)
                true
            }
        }
    }

    suspend fun update(person: Person): Boolean {
        return withContext(Dispatchers.IO) {
            personDao.update(person) > 0
        }
    }

    suspend fun delete(person: Person) {
        withContext(Dispatchers.IO) {
            personDao.delete(person)
        }
    }
}