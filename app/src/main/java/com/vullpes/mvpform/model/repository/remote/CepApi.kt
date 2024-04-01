package com.vullpes.mvpform.model.repository.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CepApi {

    //https://viacep.com.br/ws/08588-608/json/
    @GET("/ws/{cep}/json/")
    suspend fun getNews(@Path("cep") cep:String): Response<CepDto>



}


