package com.mellenwood.rickandmortycharacters.domain

import com.mellenwood.rickandmortycharacters.remote.model.CharactersResponseModel

interface CharactersDataSource {

    suspend fun getCharacters(): CharactersResponseModel?

}