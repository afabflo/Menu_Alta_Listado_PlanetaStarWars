
package com.example.menu_alta_listadoplanetstarwars.data

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// ... el resto de tu clase igual

class StringListConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }
    //converter fromlocaldate y  localdate
    //libreria de java no de jotlimn
    /*@TypeConverters(Converters::class){
        fun tolocalDate(dateString:String?):LocalDate?{

        }
    }*

     */
}