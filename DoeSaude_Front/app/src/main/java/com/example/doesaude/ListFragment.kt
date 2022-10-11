package com.example.doesaude

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doesaude.adapter.PostagemAdapter
import com.example.doesaude.adapter.TaskClickListener
import com.example.doesaude.databinding.FragmentListBinding
import com.example.doesaude.model.Postagem


class ListFragment : Fragment(), TaskClickListener {

    private lateinit var binding: FragmentListBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentListBinding.inflate(layoutInflater, container, false)


        mainViewModel.listPostagem()

        //CONFIGURAÇÃO DO RECYCLEVIEW
        val adapter = PostagemAdapter(this, mainViewModel)
        binding.recyclerTarefa.layoutManager = LinearLayoutManager(context)
        binding.recyclerTarefa.adapter = adapter
        binding.recyclerTarefa.setHasFixedSize(true)



        binding.floatingAdd.setOnClickListener {
            mainViewModel.postagemSelecionada = null
            findNavController().navigate(R.id.action_listFragment_to_formFragment)
        }
        mainViewModel.myPostagemResponse.observe(viewLifecycleOwner){
                response -> if(response.body() != null){
            adapter.setList(response.body()!!)
        }
        }
        return binding.root
    }

    override fun onTaskClickListener(postagem: Postagem) {
        mainViewModel.postagemSelecionada = postagem
        findNavController().navigate(R.id.action_listFragment_to_formFragment)
    }

}
