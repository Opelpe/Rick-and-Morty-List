package com.pnow.rick_and_morty_list.app.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pnow.rick_and_morty_list.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}
