package com.diego.poketinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diego.poketinder.data.network.FirebaseRemoteConfigRepository

class InfoViewModel : ViewModel() {
    private var firebaseRemoteConfigRepository = FirebaseRemoteConfigRepository()
    init {
        firebaseRemoteConfigRepository.init()
    }
    fun getUrlPokemon():MutableLiveData<String>{
        return firebaseRemoteConfigRepository.getUrlPokemonLiveData
    }
}