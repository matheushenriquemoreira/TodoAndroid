package com.example.doesaude.model

data class Postagem (
    var id: Long,
    var titulo: String,
    var imagem: String,
    var descricao: String,
    var categoria: Categoria
    ){

}
