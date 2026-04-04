package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val todos:MutableList<String> = mutableListOf()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, todos)
        binding.listView.adapter = adapter

        binding.btnAdd.setOnClickListener {

            var text: String = binding.etItem.text.toString().trim()

            if (text != "")
                adapter.insert(text, 0)
        }

        binding.listView.setOnItemClickListener {adapterView, view, i, l ->
            val text = binding.listView.getItemAtPosition(i).toString()
            adapter.remove(text)
            Toast.makeText(this, "Удалено $text", Toast.LENGTH_SHORT).show();

        }

    }
}