package com.mellenwood.rickandmortycharacters.data

import com.mellenwood.rickandmortycharacters.domain.CharactersRepository
import com.mellenwood.rickandmortycharacters.remote.model.CharactersResponseModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersRepositoryImpl(private val localDataSource: CharactersLocalDataSource,
                               private val remoteDataSource: CharactersRemoteDataSource,
                               private val backgroundDispatcher: CoroutineDispatcher) : CharactersRepository {

    override suspend fun saveCharacters(characters: CharactersResponseModel) {
        withContext(backgroundDispatcher) {
            localDataSource.saveCharacters(characters)
        }
    }

    override suspend fun getCharacters(): CharactersResponseModel {
        return withContext(backgroundDispatcher) {
             localDataSource.getCharacters() ?: remoteDataSource.getCharacters()
        }
    }

}