package com.example.listatarefas

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listatarefas.api.Repository
import com.example.listatarefas.model.Categoria
import com.example.listatarefas.model.Tarefa
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDate
import javax.inject.Inject


// ELE SABE QUE ESSA CLASSE É UM HILT VIEW MODEL
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
        ) : ViewModel(){


    var tarefaSelecionada: Tarefa? = null

    private val _myCategoriaResponse = MutableLiveData<Response<List<Categoria>>>()

    val myCategoriaResponse: LiveData<Response<List<Categoria>>> =
        _myCategoriaResponse

    private val _myTarefaResponse = MutableLiveData<Response<List<Tarefa>>>()
    val myTarefaResponse: LiveData<Response<List<Tarefa>>> = _myTarefaResponse

    val dataSelecionada = MutableLiveData<LocalDate>()

    init {

    }


    //MÉTODO RESPONÁVEL POR TRAZER DADOS DO REPOSITÓRIO

    fun listCategoria(){
        //  CORROTINA
        viewModelScope.launch {
            //EVITAR QUE SEJA COLOCADO INFORMAÇÕES INDESEJADAS E EVITANDO QUE NOSSO PROGRAMA QUEBRE
            try {
                val response = repository.lisCategoria()
                _myCategoriaResponse.value = response
            }catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }


    fun addTarefa(tarefa: Tarefa){
        viewModelScope.launch {
            try {
                repository.addTarefa(tarefa)
            }catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }


    fun listTarefa(){
        viewModelScope.launch {
            try {
                val response = repository.listTarefa()
                _myTarefaResponse.value = response
            }catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }


    fun updateTarefa(tarefa: Tarefa){
        viewModelScope.launch {
            try {
                repository.updateTarefa(tarefa)
                listTarefa()
            }catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

}