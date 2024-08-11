package com.karthik.a.proto

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var userDetailDataStore: DataStore<UserDetail>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val text = findViewById<TextView>(R.id.tvText)
        val btnSaveData = findViewById<Button>(R.id.btnSaveData)
        val btnGetData = findViewById<Button>(R.id.btnGetData)

        btnSaveData.setOnClickListener {
            val firstName = "Karthik"
            val lastName = "Reddy"
            val phoneNumber = "1234567890"
            val email = "karthik@gmail.com"

            // Save the data directly using DataStore
            runBlocking {
                saveUserDetail(firstName, lastName, phoneNumber, email)
            }
        }

        btnGetData.setOnClickListener {
            runBlocking {
                val userDetail = getUserDetail()
                val result = userDetail.firstName + "  "+userDetail.lastName + "  "+userDetail.email+ "  "+userDetail.phoneNumber
                text.text = result
            }
        }
    }

    // Function to retrieve data from DataStore
    private suspend fun getUserDetail(): UserDetail {
        return userDetailDataStore.data.first()
    }

    // Function to save data to DataStore
    private suspend fun saveUserDetail(firstName: String, lastName: String, phoneNumber: String, email: String) {
        userDetailDataStore.updateData { userDetail ->
            userDetail.toBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .build()
        }
    }
}