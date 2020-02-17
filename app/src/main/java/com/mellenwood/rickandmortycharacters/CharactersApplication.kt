package com.mellenwood.rickandmortycharacters

import android.app.Application
import android.content.Context

class CharactersApplication : Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(applicationContext)
    }



}