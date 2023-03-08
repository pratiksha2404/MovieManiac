package com.example.moviemaniac.ui

import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.moviemaniac.R
import com.google.firebase.auth.FirebaseAuth

class SettingsFragment : PreferenceFragmentCompat()
{
    private val TAG = "SettingsFragment"
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?)
    {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean
    {
        var key = preference.key
        Log.d(TAG, "onPreferenceTreeClick: Key = $key")
        if(key.equals("logout"))
        {
            FirebaseAuth.getInstance().signOut()
            Log.d(TAG , "onPreferenceTreeClick: logout....!!!!")
            val action = SettingsFragmentDirections.actionSettingsFragmentToLoginFragment()
            findNavController().navigate(action)
        }
        return super.onPreferenceTreeClick(preference)
    }
}