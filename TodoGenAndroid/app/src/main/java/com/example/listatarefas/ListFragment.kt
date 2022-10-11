package com.example.listatarefas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listatarefas.adapter.TarefaAdapter
import com.example.listatarefas.adapter.TaskClickListener
import com.example.listatarefas.databinding.FragmentListBinding
import com.example.listatarefas.model.Tarefa


class ListFragment : Fragment(), TaskClickListener{


    private lateinit var binding: FragmentListBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentListBinding.inflate(layoutInflater, container, false)

        mainViewModel.listTarefa()

        //val view = inflater.inflate(R.layout.fragment_list, container, false)

        //CONFIGURAÇÃO DO RECYCLEVIEW
        val adapter = TarefaAdapter(this, mainViewModel)
        //UM CARD ACIMA DO OUTRO
        binding.recyclerTarefa.layoutManager = LinearLayoutManager(context)
        binding.recyclerTarefa.adapter = adapter
        binding.recyclerTarefa.setHasFixedSize(true)

        binding.floatingAdd.setOnClickListener{
            mainViewModel.tarefaSelecionada = null
            findNavController().navigate(R.id.action_listFragment_to_formFragment)

        }

        mainViewModel.myTarefaResponse.observe(viewLifecycleOwner){
            response -> if(response.body()!= null){
                adapter.setList(response.body()!!)
            }
        }


        return binding.root
    }

    override fun onTaskClickListener(tarefa: Tarefa) {
        mainViewModel.tarefaSelecionada = tarefa
        findNavController().navigate(R.id.action_listFragment_to_formFragment)
    }
}