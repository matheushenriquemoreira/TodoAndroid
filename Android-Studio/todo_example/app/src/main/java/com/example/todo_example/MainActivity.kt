package com.example.todo_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todo_example.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}