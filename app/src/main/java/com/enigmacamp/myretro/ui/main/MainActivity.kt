package com.enigmacamp.myretro.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.myretro.R
import com.enigmacamp.myretro.data.models.Post
import com.enigmacamp.myretro.data.repository.JsonPlaceHolderRepo
import com.enigmacamp.myretro.data.repository.PostingRepo
import com.enigmacamp.myretro.utils.AppResource

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
        mainViewModel.getPost()
        subscribe()
    }


    private fun subscribe() {
        mainViewModel.postingLiveData.observe(this, {
            it?.let {
                when (it) {
                    is AppResource.Loading -> outputTextView.text = "Loading..."
                    is AppResource.Success -> {
                        it.data?.apply {
                            outputTextView.text = body
                        }
                    }
                    is AppResource.Error -> outputTextView.text = it.message
                }
            }
        })
    }
}