package com.vullpes.mvpform.model.repository.data

import com.vullpes.mvpform.model.repository.remote.CepDto

interface AddressApi {

    suspend fun getCep(cep:String): CepDto?
}