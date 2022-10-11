package com.example.listatarefas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listatarefas.MainViewModel
import com.example.listatarefas.databinding.CardLayoutBinding
import com.example.listatarefas.model.Categoria
import com.example.listatarefas.model.Tarefa

class TarefaAdapter(val taskClickListener: TaskClickListener, val mainViewModel: MainViewModel) :  RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>(){

    private var listTarefa = emptyList<Tarefa>()

    class  TarefaViewHolder(val binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        return  TarefaViewHolder(CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    //PROCESSAR DADOS E ORGANIZAR DADOS DENTRO DO CARD
    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = listTarefa[position]

        holder.binding.textNome.text = tarefa.nome
        holder.binding.textDesc.text = tarefa.descricao
        holder.binding.textResponsavel.text = tarefa.responsavel
        holder.binding.textData.text = tarefa.data
        holder.binding.switchProgresso.isChecked = tarefa.status
        holder.binding.textCategoria.text = tarefa.categoria.descricao

        //SABER O QUE FAZER COM O ITEM SELECIONADO.
        holder.itemView.setOnClickListener{
            taskClickListener.onTaskClickListener(tarefa)
        }
        holder.binding.switchProgresso.setOnCheckedChangeListener { compoundButton, ativo ->
            tarefa.status = ativo
            mainViewModel.updateTarefa(tarefa)
        }
    }

    //SABER QUANTAS VEZES SERÁ CRIAR COMPONENTES
    override fun getItemCount(): Int {
        return  listTarefa.size
    }

    fun setList(list: List<Tarefa>){
        // recebe  a lista externa
        listTarefa = list.sortedByDescending { it.id }
        // avisa o adapter que ocorreu uma alteração
        notifyDataSetChanged()
    }

}