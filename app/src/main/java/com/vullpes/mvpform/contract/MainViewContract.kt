package com.vullpes.mvpform.contract

interface MainViewContract {

    interface IFormView {
        fun onClear()
        fun onShowProgress()
        fun onHideProgress()
        fun onResultSaved()
        fun enableSaveButton(enable:Boolean)
        fun getDataAddress(endereco: String?,cidade: String?,estado: String?)

    }

    interface IPresenter {
        fun onNomeValidation(nome:String): Boolean
        fun onCargoValidation(cargo:String):Boolean
        fun onIdadeValidation(idade:String):Boolean
        fun onCepValidation(cep:String): Boolean
        fun onNumeroValidation(numero:String): Boolean
        fun onEnderecoValidation(endereco:String): Boolean
        fun onCidadeValidation(cidade:String): Boolean
        fun onEstadoValidation(estado:String): Boolean
        fun getAdressData(cep:String)
        fun saveForm()
        fun onDestroy()
    }
}