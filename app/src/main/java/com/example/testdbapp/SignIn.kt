package com.example.testdbapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser

class SignIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()


        val home: ImageButton = findViewById(R.id.btn_return_home_login)
        home.setOnClickListener {
            finish()
        }

        // Find views
        val btnLogin: Button = findViewById(R.id.btn_submit_login)
        val editTextEmail: EditText = findViewById(R.id.editTextTextEmailAddress)
        val editTextPassword: EditText = findViewById(R.id.editTextTextPassword)

        // Set OnClickListener for login button
        btnLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            // Perform Firebase authentication
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }
                }
        }

        // Navigate to sign up activity
        val navBackToSignUp: Button = findViewById(R.id.btn_direct_sign_up)
        navBackToSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        // Find the "Forgot Password" button
        val btnForgotPassword: Button = findViewById(R.id.btn_forgot_password)

// Set OnClickListener for the "Forgot Password" button
        btnForgotPassword.setOnClickListener {
            val email = editTextEmail.text.toString().trim()

            if (email.isEmpty()) {
                editTextEmail.error = "Enter your email"
                editTextEmail.requestFocus()
            } else {
                // Call method to handle forgot password
                forgotPassword(email)
            }
        }


    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            // Redirect to homepage activity upon successful login
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
            finish() // Finish the current activity to prevent returning to it on back press
        }
    }

    private fun forgotPassword(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext,
                        "Password reset email sent to $email",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        baseContext,
                        "Failed to send password reset email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}
