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
import com.example.moviemaniac.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment()
{

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth
    private val TAG = "RegisterFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding.registerButton.setOnClickListener {
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

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    binding.progressbar.visibility = View.GONE
                    if (task.isSuccessful)
                    {
                        Log.d(TAG, "createUserWithEmail:success")
                        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                        findNavController().navigate(action)
                    } else
                    {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            context, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

        binding.loginTextview.setOnClickListener{
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }
}