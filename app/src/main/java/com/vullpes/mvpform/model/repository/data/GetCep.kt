package com.vullpes.mvpform.model.repository.data

import com.vullpes.mvpform.model.repository.remote.CepApi
import com.vullpes.mvpform.model.repository.remote.CepDto
import javax.inject.Inject

class GetCep @Inject constructor(private val cepApi: CepApi):AddressApi {

    override suspend fun getCep(cep: String): CepDto? {

        val result = cepApi.getNews(cep)

       return if(result.isSuccessful && result.body() != null){
            result.body()
        }else{
            null
        }

    }
}