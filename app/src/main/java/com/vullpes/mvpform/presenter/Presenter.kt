package com.vullpes.mvpform.presenter

import androidx.core.text.isDigitsOnly
import com.vullpes.mvpform.contract.MainViewContract
import com.vullpes.mvpform.model.User
import com.vullpes.mvpform.model.repository.data.AddressApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Presenter @Inject constructor(private val view: MainViewContract.IFormView,
                                    private val  addressApi: AddressApi,
                                    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
                                    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,

    ): MainViewContract.IPresenter {

    private var user: User = User()

    val scope = CoroutineScope(ioDispatcher)


    private fun checkEnableButton(): Boolean{
        if(user.nome.isNullOrEmpty()){
            return false
        }

        if(user.cargo.isNullOrEmpty()){
            return false
        }

        if(user.idade.isNullOrEmpty()){
            return false
        }

        if(user.cep.isNullOrEmpty()){
            return false
        }

        if(user.numero.isNullOrEmpty()){
            return false
        }

        if(user.endereco.isNullOrEmpty()){
            return false
        }

        if(user.cidade.isNullOrEmpty()){
            return false
        }

        if(user.estado.isNullOrEmpty()){
            return false
        }

        return true
    }

    override fun onNomeValidation(nome: String): Boolean {
        user = user.copy(nome = nome)
        view.enableSaveButton(checkEnableButton())
        return nome.isNotEmpty()


    }

    override fun onCargoValidation(cargo: String): Boolean {
        user = user.copy(cargo = cargo)
        view.enableSaveButton(checkEnableButton())
        return cargo.isNotEmpty()
    }

    override fun onIdadeValidation(idade: String): Boolean {
        user = user.copy(idade = idade)
        view.enableSaveButton(checkEnableButton())
        return idade.isNotEmpty() && idade.isDigitsOnly()
    }

    override fun onCepValidation(cep: String): Boolean {
        user = user.copy(cep = cep)
        view.enableSaveButton(checkEnableButton())
        getAdressData(cep)
        return cep.isNotEmpty() && cep.length == 9
    }

    override fun onNumeroValidation(numero: String): Boolean {
        user = user.copy(numero = numero)
        view.enableSaveButton(checkEnableButton())
        return numero.isNotEmpty() && numero.isDigitsOnly()
    }

    override fun onEnderecoValidation(endereco: String): Boolean {
        user = user.copy(endereco = endereco)
        view.enableSaveButton(checkEnableButton())
        return endereco.isNotEmpty()
    }

    override fun onCidadeValidation(cidade: String): Boolean {
        user = user.copy(cidade = cidade)
        view.enableSaveButton(checkEnableButton())
        return cidade.isNotEmpty()
    }

    override fun onEstadoValidation(estado: String): Boolean {
        user = user.copy(estado = estado)
        view.enableSaveButton(checkEnableButton())
        return estado.isNotEmpty()
    }

    override fun getAdressData(cep: String) {
        scope.launch {
            withContext(mainDispatcher){
                view.onShowProgress()
            }
            val result = addressApi.getCep(cep)

            withContext(mainDispatcher){
                view.onHideProgress()
                view.getDataAddress(endereco = result?.logradouro, cidade = result?.localidade, estado = result?.uf)
            }
        }
    }

    override fun saveForm() {
        view.onResultSaved()
    }

    override fun onDestroy() {
        scope.cancel()
    }
}