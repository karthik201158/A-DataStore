package com.karthik.a.preference

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.lifecycleScope
import com.karthik.a.preference.utils.PreferencesKeys
import com.karthik.a.preference.utils.getString
import com.karthik.a.preference.utils.setString
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dataStore: DataStore<Preferences>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            saveName("Hi Karthik, Great")
        }

        // Load saved name and display it
        lifecycleScope.launch {
            val name = getName()
            println("karthikdatastore $name")
        }
    }

    private suspend fun saveName(name: String) {
        dataStore.setString(PreferencesKeys.NAME_KEY, name)
    }

    private suspend fun getName(): String {
        return dataStore.getString(PreferencesKeys.NAME_KEY).first()
    }
}