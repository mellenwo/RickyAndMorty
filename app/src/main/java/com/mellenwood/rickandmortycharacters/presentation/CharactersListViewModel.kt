package com.mellenwood.rickandmortycharacters.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mellenwood.rickandmortycharacters.remote.model.CharacterModel
import com.mellenwood.rickandmortycharacters.remote.service.CharactersApiImpl
import kotlinx.coroutines.*

class CharactersListViewModel : ViewModel() {

    private val apiService = CharactersApiImpl().makeGithubTrendingService(true)
    var characters = MutableLiveData<List<CharacterModel>>()

    fun loadCharacters() {

        viewModelScope.launch {
            val networkResponse = withContext(Dispatchers.IO) {
                apiService.getCharacters()
            }

            val charactersList = networkResponse.results

            characters.value = charactersList

            print(networkResponse)
        }
    }

}