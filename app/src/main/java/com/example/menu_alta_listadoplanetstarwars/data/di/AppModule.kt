package com.example.menu_alta_listadoplanetstarwars.data.di

import android.content.Context
import android.content.res.Resources
import com.example.menu_alta_listadoplanetstarwars.data.StarWarsDatabase
import com.example.menu_alta_listadoplanetstarwars.data.dao.FilmDAO
import com.example.menu_alta_listadoplanetstarwars.data.dao.PersonDao
import com.example.menu_alta_listadoplanetstarwars.data.dao.PlanetDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerResources(@ApplicationContext context: Context): Resources {
        return context.resources
    }

    @Provides
    @Singleton
    fun providesStarWarsDatabase(@ApplicationContext context: Context): StarWarsDatabase {
        return StarWarsDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun providerPlanetDao(database: StarWarsDatabase): PlanetDAO {
        return database.planetDao()
    }

    @Provides
    @Singleton
    fun providerPersonDao(database: StarWarsDatabase): PersonDao {
        return database.personDao()
    }

    @Provides
    @Singleton
    fun providerFilmDao(database: StarWarsDatabase): FilmDAO {
        return database.filmDao()
    }
}