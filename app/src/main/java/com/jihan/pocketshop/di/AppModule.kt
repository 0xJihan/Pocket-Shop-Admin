package com.jihan.pocketshop.di

import com.jihan.pocketshop.data.api.AuthApi
import com.jihan.pocketshop.domain.repository.AuthRepository
import com.jihan.pocketshop.domain.utils.Constants.BASE_URL
import com.jihan.pocketshop.domain.viewmodel.AuthViewmodel
import com.jihan.pocketshop.domain.viewmodel.LanguageViewmodel
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory


private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = true
    prettyPrint = true
    coerceInputValues = true
}

val appModule = module {


    single {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(json.asConverterFactory(
            "application/json; charset=UTF-8".toMediaType()
        )
        )
    }

    single {
        get<Retrofit.Builder>().build().create(AuthApi::class.java)
    }



    viewModelOf(::AuthViewmodel)
    viewModelOf(::LanguageViewmodel)
    singleOf(::AuthRepository)

}



