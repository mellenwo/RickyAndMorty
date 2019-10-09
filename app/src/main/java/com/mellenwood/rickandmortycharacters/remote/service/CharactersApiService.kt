package com.mellenwood.rickandmortycharacters.remote.service

import com.mellenwood.rickandmortycharacters.remote.model.CharactersResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CharactersApiService {

    @GET("/api/character")
    suspend fun getCharacters() : CharactersResponseModel

}