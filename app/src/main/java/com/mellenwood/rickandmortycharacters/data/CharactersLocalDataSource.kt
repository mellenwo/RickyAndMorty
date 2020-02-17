package com.mellenwood.rickandmortycharacters.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.mellenwood.rickandmortycharacters.domain.CharactersDataSource
import com.mellenwood.rickandmortycharacters.remote.model.CharactersResponseModel

class CharactersLocalDataSource(private val sharedPrefs: SharedPreferences): CharactersDataSource {

    companion object {
        const val CHARACTERS_LIST = "characters_list"
    }

    private val gson = Gson()


    override suspend fun getCharacters(): CharactersResponseModel? {
        val json = sharedPrefs.getString(CHARACTERS_LIST, "")
        return gson.fromJson(json, CharactersResponseModel::class.java)
    }

    fun saveCharacters(characters: CharactersResponseModel){
        val json = gson.toJson(characters)
        sharedPrefs.edit().putString(CHARACTERS_LIST, json).apply()
    }

}