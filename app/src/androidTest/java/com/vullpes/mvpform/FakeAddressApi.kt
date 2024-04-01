package com.vullpes.mvpform

import com.vullpes.mvpform.model.repository.data.AddressApi
import com.vullpes.mvpform.model.repository.remote.CepDto

class FakeAddressApi (): AddressApi {
    override suspend fun getCep(cep: String): CepDto? {
        return CepDto(
            bairro = "Jd Alpes de Itaqua",
            cep = "08588-608",
            complemento = "",
            ddd= "11",
            gia = "",
            ibge = "",
            localidade = "Itaquaquecetuba",
            logradouro = "Rua quipapa",
            siafi = "",
            uf= "SP"

        )
    }
}