package com.example.moviemaniac.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviemaniac.R

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState==null)
        {
            val homePage = HomePage()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, homePage,).commit()
        }
    }

}
