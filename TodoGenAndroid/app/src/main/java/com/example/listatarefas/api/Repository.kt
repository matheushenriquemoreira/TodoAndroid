package com.example.listatarefas.api

import com.example.listatarefas.model.Categoria
import com.example.listatarefas.model.Tarefa
import retrofit2.Response

class Repository {
    suspend fun lisCategoria(): Response<List<Categoria>>{
        return RetroFitInstance.api.lisCategoria()
    }

    suspend fun addTarefa(tarefa: Tarefa): Response<Tarefa>{
        return RetroFitInstance.api.addTarefa(tarefa)
    }

    suspend fun listTarefa(): Response<List<Tarefa>>{
        return  RetroFitInstance.api.listTarefa()
    }

    suspend fun updateTarefa(tarefa: Tarefa): Response<Tarefa>{
        return RetroFitInstance.api.updateTarefa(tarefa)
    }
}