package com.example.doesaude.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doesaude.MainViewModel
import com.example.doesaude.databinding.CardLayoutBinding
import com.example.doesaude.model.Postagem

class PostagemAdapter(
    val taskClickListener: TaskClickListener,
    val mainViewModel: MainViewModel
) : RecyclerView.Adapter<PostagemAdapter.PostagemViewHolder>(){


    private var listPostagem = emptyList<Postagem>()

    class PostagemViewHolder(val binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root){}

    //CRIARÁ COM BASE NO CARD
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostagemViewHolder {
        return PostagemViewHolder(CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    //COMO IRÁ PROCESSAR DENTRO DO CARD
    override fun onBindViewHolder(holder: PostagemViewHolder, position: Int) {
        val postagem = listPostagem[position]

        holder.binding.textImagem.text = postagem.imagem
        holder.binding.textTitulo.text = postagem.titulo
        holder.binding.textDesc.text = postagem.descricao
        holder.binding.textCategoria.text = postagem.categoria.tipo

        holder.itemView.setOnClickListener {
            taskClickListener.onTaskClickListener(postagem)
        }
    }

    override fun getItemCount(): Int {
        return listPostagem.size
    }

    //SETTAR LISTA
    fun setList(list: List<Postagem>){
        listPostagem = list.sortedByDescending { it.id }
        notifyDataSetChanged()
    }
}