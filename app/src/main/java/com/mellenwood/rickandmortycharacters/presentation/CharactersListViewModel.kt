package com.mellenwood.rickandmortycharacters.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mellenwood.rickandmortycharacters.domain.CharactersRepository
import com.mellenwood.rickandmortycharacters.remote.model.CharacterModel
import com.mellenwood.rickandmortycharacters.remote.service.CharactersApiImpl
import kotlinx.coroutines.*

class CharactersListViewModel(private val repository: CharactersRepository) : ViewModel() {

    private val apiService = CharactersApiImpl().makeGithubTrendingService(true)
    var characters = MutableLiveData<List<CharacterModel>>()

    fun loadCharacters() {


        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                repository.getCharacters()
            }
            val charactersList = response.results

            if (charactersList.isNotEmpty()) {
                repository.saveCharacters(response)
            }

            characters.value = charactersList

            print(response)
        }
    }

}