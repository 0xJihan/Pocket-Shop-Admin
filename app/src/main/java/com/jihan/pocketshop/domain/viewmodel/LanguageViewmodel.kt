package com.jihan.pocketshop.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jihan.pocketshop.domain.utils.Datastore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LanguageViewmodel : ViewModel() {

    private val _language = MutableStateFlow(0)
    val language = _language.asStateFlow()

    init {
        _language.value = Datastore.language.value
    }

    fun setLanguage(language: Int) {
        _language.value = language
        viewModelScope.launch {
        Datastore.language.update(language)
        }
    }

    fun updateToken(token: String) {
        viewModelScope.launch {
            Datastore.token.update(token)
        }
    }
}