package com.vullpes.mvpform.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import com.vullpes.mvpform.contract.MainViewContract
import com.vullpes.mvpform.databinding.ActivityMainBinding
import com.vullpes.mvpform.util.MaskWatcher
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainViewContract.IFormView {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var presenter: MainViewContract.IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, com.vullpes.mvpform.R.layout.activity_main)



        binding.txtNome.doAfterTextChanged { text->
            presenter.onNomeValidation(text.toString())
        }

        binding.txtCargo.doAfterTextChanged { text->
            presenter.onCargoValidation(text.toString())
        }
        binding.txtIdade.doAfterTextChanged { text->
            presenter.onIdadeValidation(text.toString())
        }
        binding.txtCep.addTextChangedListener(MaskWatcher("#####-###"))
        binding.txtCep.doAfterTextChanged { text->
            presenter.onCepValidation(text.toString())
        }
        binding.txtNumero.doAfterTextChanged { text->
            presenter.onNumeroValidation(text.toString())
        }
        binding.endereco.doAfterTextChanged { text->
            presenter.onEnderecoValidation(text.toString())
        }
        binding.txtCidade.doAfterTextChanged { text->
            presenter.onCidadeValidation(text.toString())
        }
        binding.txtEstado.doAfterTextChanged { text->
            presenter.onEstadoValidation(text.toString())
        }

        binding.btnSalvar.setOnClickListener {
            presenter.saveForm()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onClear() {
        binding.txtNome.setText("")
        binding.txtCargo.setText("")
        binding.txtIdade.setText("")
        binding.txtCep.setText("")
        binding.txtNumero.setText("")
        binding.endereco.setText("")
        binding.txtCidade.setText("")
        binding.txtEstado.setText("")

    }

    override fun onShowProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    override fun onHideProgress() {
        binding.progress.visibility = View.GONE
    }

    override fun onResultSaved() {
        Toast.makeText(this,"Formulario Salvo", Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }



    override fun enableSaveButton(enable: Boolean) {
        binding.btnSalvar.isEnabled = enable
    }

    override fun getDataAddress(endereco: String?, cidade: String?, estado: String?) {
        endereco?.let {
            binding.endereco.setText(it)
        }

        cidade?.let {
            binding.txtCidade.setText(it)
        }

        estado?.let {
            binding.txtEstado.setText(it)
        }


    }
}