package com.example.doesaude.model

data class Categoria(
    var id: Long,
    var tipo: String?,
    var descricao: String?,
    var postagens: List<Postagem>?
) {


    override fun toString(): String {
        return tipo!!
    }
}