package com.example.moviemaniac.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviemaniac.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate( layoutInflater )
        setContentView(binding.root)

        if(savedInstanceState==null)
        {
            val homePage = HomePage()
            supportFragmentManager.beginTransaction().add(binding.fragmentContainer.id, homePage,).commit()
        }
    }

}
