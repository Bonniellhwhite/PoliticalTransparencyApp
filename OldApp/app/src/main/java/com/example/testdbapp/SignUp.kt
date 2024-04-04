package com.example.testdbapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
class SignUp : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        mAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Navigates back to "Home Page"
        val home: ImageButton = findViewById(R.id.btn_return_home_sign_up)
        home.setOnClickListener {
            finish()
        }

        // Navigates to login
        val navBackToLogin: Button = findViewById(R.id.btn_direct_login)
        navBackToLogin.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

        // When sign up button is pushed
        val signUpButton: Button = findViewById(R.id.btn_submit_sign_up)
        signUpButton.setOnClickListener {
            val emailEditText: EditText = findViewById(R.id.Email)
            val passwordEditText: EditText = findViewById(R.id.Password)
            val usernameEditText: EditText = findViewById(R.id.Username)

            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val username = usernameEditText.text.toString()

            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign up success, update UI with the signed-in user's information
                        val user: FirebaseUser? = mAuth.currentUser
                        val uid = user?.uid

                        // Save user information to Firestore
                        val userMap = hashMapOf(
                            "email" to email,
                            "username" to username
                        )
                        if (uid != null) {
                            firestore.collection("users").document(uid)
                                .set(userMap)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "Sign up successful!", Toast.LENGTH_SHORT).show()

                                    // Redirect to home page or any other activity upon successful signup
                                    val intent = Intent(this, HomePage::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(
                                        baseContext, "Error: ${e.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        }
                    } else {
                        // If sign up fails, display a message to the user.
                        if (task.exception is FirebaseAuthUserCollisionException) {
                            // Email is already in use
                            Toast.makeText(
                                baseContext, "This email is already associated with an existing account.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            // Other errors
                            Toast.makeText(
                                baseContext, "Sign up failed. Please try again.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        }
    }
}