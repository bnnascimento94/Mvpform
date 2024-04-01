package com.vullpes.mvpform.presenter

import com.google.common.base.CharMatcher.any
import com.google.common.truth.Truth
import com.vullpes.mvpform.MainCoroutineRule
import com.vullpes.mvpform.contract.MainViewContract
import com.vullpes.mvpform.model.repository.data.AddressApi
import com.vullpes.mvpform.model.repository.remote.CepDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.isActive
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyBoolean
import org.mockito.Mockito.anyString
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class PresenterTest{

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Mock
    lateinit var view: MainViewContract.IFormView

    @Mock
    lateinit var addressApi: AddressApi

    lateinit var presenter: Presenter


    @Before
    fun setup(){
        presenter = Presenter(view,addressApi, mainDispatcher = Dispatchers.Main, ioDispatcher = Dispatchers.Main )

    }


    @Test
    fun `testar validacao de nome quando inserido o nome`(){
        val nome = "carlos"

        val result = presenter.onNomeValidation(nome)

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun `testar validacao de nome quando não inserido o nome`(){
        val nome = ""

        val result = presenter.onNomeValidation(nome)

        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `checar se é chamada a funcao de habilitar o botão quando digitado o nome`(){
        val nome = "calor"

        presenter.onNomeValidation(nome)

        verify(view, times(1)).enableSaveButton(anyBoolean())
    }


    @Test
    fun `testar validacao de cargo quando inserido o cargo`(){
        val cargo = "gerente"

        val result = presenter.onCargoValidation(cargo)

        Truth.assertThat(result).isTrue()
    }

    @Test
    fun `testar validacao de cargo quando não inserido o cargo`(){
        val cargo = ""

        val result = presenter.onCargoValidation(cargo)

        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `checar se é chamada a funcao de habilitar o botão quando digitado o cargo`(){
        val cargo = "gerente"

        presenter.onCargoValidation(cargo)

        verify(view, times(1)).enableSaveButton(anyBoolean())
    }


    @Test
    fun `testar validacao de idade quando inserido a idade`(){
        val idade = "13"
        val result = presenter.onIdadeValidation(idade)
        Truth.assertThat(result).isTrue()
    }

    @Test
    fun `testar validacao de idade quando inserido a idade com texto ao invés de numeros`(){
        val idade = "hkasdasd"
        //to work is digit only i had to create a package on java>android>text> TextUtils.java and paste the function there
        val result = presenter.onIdadeValidation(idade)
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `testar validacao de idade quando não inserido a idade`(){
        val idade = ""
        val result = presenter.onIdadeValidation(idade)
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `checar se é chamada a funcao de habilitar o botão quando digitada a idade`(){
        val idade = "19"
        presenter.onIdadeValidation(idade)
        verify(view, times(1)).enableSaveButton(anyBoolean())
    }



    @Test
    fun `testar validacao de cep quando inserido o cep`(){
        val cep = "08566-709"
        val result = presenter.onCepValidation(cep)
        Truth.assertThat(result).isTrue()
    }



    @Test
    fun `testar validacao de cep quando não inserido nenhum cep`(){
        val cep = ""
        val result = presenter.onCepValidation(cep)
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `checar se e chamada a funcao de habilitar o botao quando digitada o cep`(){
        val cep = "08566-709"
        presenter.onCepValidation(cep)
        verify(view, times(1)).enableSaveButton(anyBoolean())
    }


    @Test
    fun `testar validacao de numero quando inserido a numero`(){
        val numero = "40"
        val result = presenter.onNumeroValidation(numero)
        Truth.assertThat(result).isTrue()
    }

    @Test
    fun `testar validacao de numero quando inserido o numero com texto ao inves de numeros`(){
        val numero = "hkasdasd"
        //to work is digit only i had to create a package on java>android>text> TextUtils.java and paste the function there
        val result = presenter.onNumeroValidation(numero)
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `testar validacao de numero quando nao inserido o numero`(){
        val numero = ""
        val result = presenter.onNumeroValidation(numero)
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `checar se e chamada a funcao de habilitar o botao quando digitada o numero`(){
        val numero = "19"
        presenter.onNumeroValidation(numero)
        verify(view, times(1)).enableSaveButton(anyBoolean())
    }



    @Test
    fun `testar validacao de endereco quando inserido o endereco`(){
        val endereco = "Rua 1234"
        val result = presenter.onEnderecoValidation(endereco)
        Truth.assertThat(result).isTrue()
    }



    @Test
    fun `testar validacao de endereco quando não inserido nenhum endereco`(){
        val endereco = ""
        val result = presenter.onEnderecoValidation(endereco)
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `checar se e chamada a funcao de habilitar o endereco quando digitada o endereco`(){
        val endereco = "Rua 1234"
        presenter.onEnderecoValidation(endereco)
        verify(view, times(1)).enableSaveButton(anyBoolean())
    }


    @Test
    fun `testar validacao de cidade quando inserido a cidade`(){
        val cidade = "Helsinki"
        val result = presenter.onCidadeValidation(cidade)
        Truth.assertThat(result).isTrue()
    }



    @Test
    fun `testar validacao de cidade quando não inserido nenhuma cidade`(){
        val cidade = ""
        val result = presenter.onCidadeValidation(cidade)
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `checar se e chamada a funcao de habilitar o botao salvar quando digitada a cidade`(){
        val cidade = "Helsinki"
        presenter.onCidadeValidation(cidade)
        verify(view, times(1)).enableSaveButton(anyBoolean())
    }




    @Test
    fun `testar validacao de estado quando inserido a estado`(){
        val estado = "SP"
        val result = presenter.onEstadoValidation(estado)
        Truth.assertThat(result).isTrue()
    }



    @Test
    fun `testar validacao de estado quando nao inserido nenhuma estado`(){
        val estado = ""
        val result = presenter.onEstadoValidation(estado)
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `checar se e chamada a funcao de habilitar o botao salvar quando digitado o estado`(){
        val estado = "Helsinki"
        presenter.onEstadoValidation(estado)
        verify(view, times(1)).enableSaveButton(anyBoolean())
    }


    @Test
    fun `checar se e chamada a api quando recebe o valor do cep`() = runTest{
        val cep = "08588-604"
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

        `when`(addressApi.getCep(cep)).thenReturn(cepDto)
        presenter.getAdressData(cep)
        //advanceUntilIdle()
        verify(view, times(1)).getDataAddress(anyString(),anyString(),anyString())

    }


    @Test
    fun `testar se a corrotina é cancelada quando a view é destruida`() = runTest {

        val scopeJob = presenter.scope
        presenter.onDestroy()
        val result = scopeJob.isActive
        Truth.assertThat(result).isFalse()


    }


}