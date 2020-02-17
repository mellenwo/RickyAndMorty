package com.mellenwood.rickandmortycharacters

import android.content.Context
import com.mellenwood.rickandmortycharacters.data.CharactersLocalDataSource
import com.mellenwood.rickandmortycharacters.data.CharactersRemoteDataSource
import com.mellenwood.rickandmortycharacters.data.CharactersRepositoryImpl
import com.mellenwood.rickandmortycharacters.presentation.CharactersListViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppContainer(context: Context) {

    companion object {
        const val CHARACTERS_PREFS = "characters_prefs"
    }

    private val sharedPrefs = context.getSharedPreferences(CHARACTERS_PREFS, 0)

    private val localDataSource = CharactersLocalDataSource(sharedPrefs)
    private val remoteDataSource = CharactersRemoteDataSource()

    private val repository = CharactersRepositoryImpl(localDataSource, remoteDataSource, Dispatchers.IO)

    val viewModel = CharactersListViewModel(repository)

}