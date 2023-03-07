package com.example.moviemaniac.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.moviemaniac.R
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

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navHostController = navHostFragment.navController
        binding.bottomNavigationBar.setupWithNavController(navHostController)
        navHostController.addOnDestinationChangedListener{_, destination, _ ->
            if( destination.id == R.id.loginFragment ||
                    destination.id == R.id.registerFragment ||
                    destination.id == R.id.moviesDetailsFragment )
            {
                binding.bottomNavigationBar.visibility = View.GONE
            }
            else
            {
                binding.bottomNavigationBar.visibility = View.VISIBLE
            }
        }
    }

}
