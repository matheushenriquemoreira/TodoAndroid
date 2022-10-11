package com.example.doesaude

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.doesaude.databinding.FragmentCadastroEnderecoBinding
import com.example.doesaude.databinding.FragmentCadastroUsuarioBinding

class cadastro_endereco : Fragment() {

    private lateinit var binding: FragmentCadastroEnderecoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCadastroEnderecoBinding.inflate(layoutInflater, container, false)

        binding.buttonEndFinalizar.setOnClickListener{
            findNavController().navigate(R.id.action_cadastro_endereco_to_listFragment)
        }

        return binding.root
    }

}