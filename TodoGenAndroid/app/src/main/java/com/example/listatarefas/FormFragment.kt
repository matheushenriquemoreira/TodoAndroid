package com.example.listatarefas

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.listatarefas.databinding.FragmentFormBinding
import com.example.listatarefas.databinding.FragmentListBinding
import com.example.listatarefas.fragment.DatePickerFragment
import com.example.listatarefas.fragment.TimerPickerListener
import com.example.listatarefas.model.Categoria
import com.example.listatarefas.model.Tarefa
import java.time.LocalDate


class FormFragment : Fragment(), TimerPickerListener {

    private lateinit var binding: FragmentFormBinding
    //mainViewModel será presente em todas as telas -> Instancia compartilhada
    private val mainViewModel: MainViewModel by activityViewModels()
    private var categoriaSelecionada = 0L

    private var tarefaSelecionada : Tarefa? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState:  Bundle?
    ): View? {

        mainViewModel.listCategoria()

        //CRIAR E SETAR DATA COM O CAMPO ATUAL
        mainViewModel.dataSelecionada.value = LocalDate.now()

        // Inflate the layout for this fragment
        binding = FragmentFormBinding.inflate(layoutInflater, container, false)


        carregarDados()


        mainViewModel.myCategoriaResponse.observe(viewLifecycleOwner){
           response -> Log.d("Requisicao", response.body().toString())
            spinnerCategoria(response.body())
        }

        mainViewModel.dataSelecionada.observe(viewLifecycleOwner){
            selectDate -> binding.editData.setText(selectDate.toString())
        }

        binding.buttonSalvar.setOnClickListener{
            inserirNoBanco()
        }

        binding.editData.setOnClickListener{
            DatePickerFragment(this).show(parentFragmentManager, "DatePicker")
        }

        return binding.root
    }

    //ARRAYADAPTER
    fun spinnerCategoria(listCategoria: List<Categoria>?){
        if(listCategoria != null){
            binding.spinnerCategoria.adapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listCategoria)

            binding.spinnerCategoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val selected = binding.spinnerCategoria.selectedItem as Categoria

                    //ARMAZENAR VARIÁVEL DENTRO DA VARIÁVEL GLOBAL
                    categoriaSelecionada = selected.id
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

        }
    }


    private fun validarCampos(nome: String, desc: String, responsavel: String): Boolean{
        return !((nome == "" || nome.length < 3 || nome.length > 20) || (desc == "" || desc.length < 5 || desc.length > 200) || (responsavel == "" || responsavel.length < 3 || responsavel.length > 20))
    }

    private fun inserirNoBanco(){
        val nome = binding.editNome.text.toString()
        val desc = binding.editDescricao.text.toString()
        val resp = binding.editResponsavel.text.toString()
        val data = binding.editData.text.toString()
        val status = binding.switchAtivoCard.isChecked
        val categoria = Categoria(categoriaSelecionada, null, null)

        if(validarCampos(nome, desc, resp)){
            val salvar: String

            if(tarefaSelecionada != null){
                salvar = "Tarefa Atualizada"
                val tarefa = Tarefa(tarefaSelecionada?.id!!, nome, desc, resp, data, status, categoria)
                mainViewModel.addTarefa(tarefa)
            }else {
                salvar = "Tarefa Criada"
                val tarefa = Tarefa(0,nome, desc, resp, data, status, categoria)
                mainViewModel.addTarefa(tarefa)
            }
            Toast.makeText(context, salvar, Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        }else{
            Toast.makeText(context, "Verifique os Campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun carregarDados(){
        tarefaSelecionada = mainViewModel.tarefaSelecionada
        if(tarefaSelecionada != null){
            binding.editNome.setText(tarefaSelecionada?.nome)
            binding.editDescricao.setText(tarefaSelecionada?.descricao)
            binding.editResponsavel.setText(tarefaSelecionada?.responsavel)
            binding.editData.setText(tarefaSelecionada?.data)
            binding.switchAtivoCard.isChecked = tarefaSelecionada?.status!!
        }
    }

    override fun onDateSelected(date: LocalDate) {
        mainViewModel.dataSelecionada.value = date
    }


}