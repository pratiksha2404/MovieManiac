package com.example.moviemaniac.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.moviemaniac.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment()
{

    private lateinit var binding: FragmentLoginBinding
    private val TAG = "LoginFragment"
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            val action = LoginFragmentDirections.actionLoginFragmentToHomePage()
            findNavController().navigate(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding.loginButton.setOnClickListener {
            val email: String = binding.email.text.toString()
            val password: String = binding.password.text.toString()
            binding.progressbar.visibility = View.VISIBLE
            Log.d(TAG, "onViewCreated: email $email")
            Log.d(TAG, "onViewCreated: password $password")

            if (TextUtils.isEmpty(email))
            {
                Log.d(TAG, "onViewCreated: email is Empty")
                Toast.makeText(context, "Please Enter Email", Toast.LENGTH_LONG).show()
                binding.progressbar.visibility = View.GONE
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password) && password.length < 7)
            {
                Toast.makeText(context, "Invalid Password", Toast.LENGTH_LONG).show()
                binding.progressbar.visibility = View.GONE
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    binding.progressbar.visibility = View.GONE
                    if (task.isSuccessful)
                    {
                        Log.d(TAG, "signInWithEmail:success")
                        val action = LoginFragmentDirections.actionLoginFragmentToHomePage()
                        findNavController().navigate(action)
                    }else
                    {
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.registerTextview.setOnClickListener{
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }
}