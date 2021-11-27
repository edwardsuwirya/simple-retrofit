package com.enigmacamp.myretro.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.myretro.R
import com.enigmacamp.myretro.data.models.Post
import com.enigmacamp.myretro.data.repository.JsonPlaceHolderRepo
import com.enigmacamp.myretro.data.repository.PostingRepo
import com.enigmacamp.myretro.utils.AppStatus

class MainActivity : AppCompatActivity() {
    private lateinit var repo: PostingRepo
    private lateinit var mainViewModel: MainViewModel
    private lateinit var outputTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        outputTextView = findViewById(R.id.output_textView)
        repo = JsonPlaceHolderRepo()
        mainViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(repo) as T
            }

        }).get(MainViewModel::class.java)
        subscribe()
    }


    private fun subscribe() {
        mainViewModel.getPost().observe(this, {
            it?.let {
                when (it.appStatus) {
                    AppStatus.LOADING -> outputTextView.text = "Loading..."
                    AppStatus.SUCCESS -> {
                        val post = it.data as Post
                        outputTextView.text = post.title
                    }
                    AppStatus.ERROR -> outputTextView.text = it.message

                }
            }
        })
    }
}