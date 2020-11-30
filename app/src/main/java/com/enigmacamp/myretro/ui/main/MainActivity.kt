package com.enigmacamp.myretro.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.myretro.R
import com.enigmacamp.myretro.data.repository.JsonPlaceHolderRepo

class MainActivity : AppCompatActivity() {
    private lateinit var repo: JsonPlaceHolderRepo
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repo = JsonPlaceHolderRepo()
        mainViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(repo) as T
            }

        }).get(MainViewModel::class.java)
        mainViewModel.getPost()
        mainViewModel.response.observe(this, {
            if (it.isSuccessful) {
                Log.d("MainActivity", it.body().toString())
            } else {
                Log.e("MainActivity", it.errorBody().toString())
            }

        })
    }
}