package com.example.doesaude

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.doesaude.databinding.FragmentCadastroUsuarioBinding

class cadastro_usuario : Fragment() {

    private lateinit var binding: FragmentCadastroUsuarioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding = FragmentCadastroUsuarioBinding.inflate(layoutInflater, container, false)

        binding.buttonUserAvancar.setOnClickListener{
            findNavController().navigate(R.id.action_cadastro_usuario_to_cadastro_endereco)
        }


        return binding.root
    }

}