package com.vullpes.mvpform.model.repository.data

import com.google.common.base.CharMatcher.any
import com.google.common.truth.Truth
import com.vullpes.mvpform.model.repository.remote.CepApi
import com.vullpes.mvpform.model.repository.remote.CepDto
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
class GetCepTest{

    @Mock
    lateinit var cepApi: CepApi

    lateinit var getCep: GetCep


    @Before
    fun setup(){
        getCep = GetCep(cepApi)
    }



    @Test
    fun `testar retorno null quando digitar o cep incorreto`() = runTest {
        val cep = "08588"
        `when`(cepApi.getNews(cep)).thenReturn(
            Response.success(null)
        )
        val result = getCep.getCep(cep)
        Truth.assertThat(result).isNull()
    }

    @Test
    fun `testar retorno do CEP dto quando digitar o cep correto`() = runTest {
        val cep = "08588-608"
        val cepDto =  CepDto(
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

        `when`(cepApi.getNews(cep)).thenReturn(
            Response.success(cepDto)
        )

        val result = getCep.getCep(cep)

        Truth.assertThat(result).isEqualTo(cepDto)

    }


}