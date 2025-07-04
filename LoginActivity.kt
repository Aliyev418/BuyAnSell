package com.buyansell

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username == "admin" && password == "admin123") {
                startActivity(Intent(this, AdminActivity::class.java))
                finish()
            } else if (username == "user1" && password == "user123") {
                startActivity(Intent(this, UserActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "İstifadəçi adı və ya şifrə yalnışdır", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
