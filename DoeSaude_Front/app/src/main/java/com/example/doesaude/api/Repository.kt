package com.example.doesaude.api

import com.example.doesaude.model.Categoria
import com.example.doesaude.model.Postagem
import retrofit2.Response

class Repository {

    suspend fun listCategoria(): Response<List<Categoria>>{
        return RetrofitInstance.api.listCategoria()
    }

    suspend fun addPostagem(postagem: Postagem): Response<Postagem>{
        return RetrofitInstance.api.addPostagem(postagem)
    }

    suspend fun listPostagem(): Response<List<Postagem>>{
        return  RetrofitInstance.api.listPostagem()
    }

    suspend fun updatePostagem(postagem: Postagem): Response<Postagem>{
        return RetrofitInstance.api.updateTarefa(postagem)
    }
}