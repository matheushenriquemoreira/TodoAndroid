package com.example.listatarefas.api

import com.example.listatarefas.model.Categoria
import com.example.listatarefas.model.Tarefa
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {

    //VERBOS HTTP
    @GET("categoria")
    suspend fun lisCategoria(): Response<List<Categoria>>

    @GET("tarefa")
    suspend fun listTarefa(): Response<List<Tarefa>>

    @POST("tarefa")
    suspend fun addTarefa(@Body tarefa: Tarefa): Response<Tarefa>

    @PUT("tarefa")
    suspend fun updateTarefa(@Body tarefa: Tarefa): Response<Tarefa>
}