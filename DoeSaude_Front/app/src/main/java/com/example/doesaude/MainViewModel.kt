package com.example.doesaude

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doesaude.api.Repository
import com.example.doesaude.model.Categoria
import com.example.doesaude.model.Postagem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    var postagemSelecionada: Postagem? = null

    private val _myCategoriaResponse = MutableLiveData<Response<List<Categoria>>>()
    val myCategoriaResponse: LiveData<Response<List<Categoria>>> = _myCategoriaResponse

    //PONTO DE ATENÇÃO
    private val _myPostagemResponse = MutableLiveData<Response<List<Postagem>>>()
    val myPostagemResponse: LiveData<Response<List<Postagem>>> = _myPostagemResponse

    init {
        // listCategoria()
    }

    fun listCategoria(){
        viewModelScope.launch {
            try {
                val response = repository.listCategoria()
                _myCategoriaResponse.value = response
            }catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun addPostagem(postagem: Postagem){
        viewModelScope.launch {
            try {
                repository.addPostagem(postagem)
            }catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun listPostagem(){
        viewModelScope.launch {
            try {
                val response = repository.listPostagem()
                _myPostagemResponse.value = response
            }catch (e : Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun updatePostagem(postagem: Postagem){
        viewModelScope.launch {
            try {
                repository.updatePostagem(postagem)
                listPostagem()

            }catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }
}
