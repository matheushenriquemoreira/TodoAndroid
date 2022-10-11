package com.example.doesaude

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.doesaude.databinding.FragmentFormBinding
import com.example.doesaude.databinding.FragmentListBinding
import com.example.doesaude.model.Categoria
import com.example.doesaude.model.Postagem

class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private var categoriaSelecionada = 0L
    private var postagemSelecionada: Postagem? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormBinding.inflate(layoutInflater, container, false)

        carregarDados()

        mainViewModel.listCategoria()

        mainViewModel.myCategoriaResponse.observe(viewLifecycleOwner){
                response -> Log.d("Requisicao", response.body().toString())
            spinnerCategoria(response.body())
        }

        binding.buttonSalvar.setOnClickListener {
            inserirNoBanco()
        }
        return binding.root
    }

    private fun spinnerCategoria(listCategoria: List<Categoria>?){
        if (listCategoria != null){
            binding.spinnerCategoria.adapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listCategoria)

        }

        binding.spinnerCategoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selected = binding.spinnerCategoria.selectedItem as Categoria

                categoriaSelecionada = selected.id
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
    private fun inserirNoBanco(){
        val imagem = binding.editImagem.text.toString()
        val titulo = binding.editTitulo.text.toString()
        val descricao = binding.editDescricao.text.toString()
        val categoria = Categoria(categoriaSelecionada, null, null, null)
        Log.d("categoria", categoria.id.toString())

        if(validarCampos(imagem, titulo, descricao)){
            val salvar: String
            if(postagemSelecionada != null){
                salvar = "Postagem atualizada!"
                val postagem = Postagem(postagemSelecionada?.id!!, titulo, imagem, descricao, categoria)
                mainViewModel.addPostagem(postagem)
            }else{
                salvar = "Postagem criada!"
                val postagem = Postagem(0, titulo, imagem, descricao, categoria)
                mainViewModel.addPostagem(postagem)
            }
            Toast.makeText(context, salvar, Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_formFragment_to_listFragment)

        }else{
            Toast.makeText(context, "Verifique os campos!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun validarCampos(imagem: String, titulo: String, descricao: String): Boolean{
        return !(
                (imagem == "" || titulo == "" || titulo.length < 3 || titulo.length > 30)
                        || (descricao.length < 5 || descricao.length > 200 || descricao == "")
                )
    }

    private fun carregarDados(){
        postagemSelecionada = mainViewModel.postagemSelecionada
        if(postagemSelecionada != null){
            binding.editTitulo.setText(postagemSelecionada?.titulo)
            binding.editImagem.setText(postagemSelecionada?.imagem)
            binding.editDescricao.setText(postagemSelecionada?.descricao)
        }
    }

}