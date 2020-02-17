package com.mellenwood.rickandmortycharacters.data

import com.mellenwood.rickandmortycharacters.domain.CharactersDataSource
import com.mellenwood.rickandmortycharacters.remote.model.CharactersResponseModel
import com.mellenwood.rickandmortycharacters.remote.service.CharactersApiImpl

class CharactersRemoteDataSource: CharactersDataSource {

    private val apiService = CharactersApiImpl().makeGithubTrendingService(true)

    override suspend fun getCharacters(): CharactersResponseModel {
        return apiService.getCharacters()
    }

}