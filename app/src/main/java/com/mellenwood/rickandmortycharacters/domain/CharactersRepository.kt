package com.mellenwood.rickandmortycharacters.domain

import com.mellenwood.rickandmortycharacters.remote.model.CharactersResponseModel

interface CharactersRepository {

    suspend fun getCharacters(): CharactersResponseModel

    suspend fun saveCharacters(characters: CharactersResponseModel)

}