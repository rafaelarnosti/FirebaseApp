package br.com.rafaelarnosti.firebaseapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import android.support.v4.app.FragmentActivity
import android.util.Log
import br.com.rafaelarnosti.firebaseapp.extensions.getText
import android.R.attr.password




class LoginActivity : AppCompatActivity() {


    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        btCriar.setOnClickListener {
            mAuth.createUserWithEmailAndPassword(inputEmail.getText(), inputSenha.getText())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(FragmentActivity.TAG, "createUserWithEmail:success")
                            val user = mAuth.currentUser
                            //updateUI(user)
                            Toast.makeText(this, "Authentication sucess.",
                                    Toast.LENGTH_SHORT).show()
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(FragmentActivity.TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                            //updateUI(null)
                        }

                        // ...
                    }
        }

        btLogin.setOnClickListener {
            mAuth.signInWithEmailAndPassword(inputEmail.getText(), inputSenha.getText())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(FragmentActivity.TAG, "signInWithEmail:success")
                            val user = mAuth.currentUser
                            //updateUI(user)
                            Toast.makeText(this, "Authentication sucess.",
                                    Toast.LENGTH_SHORT).show()
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(FragmentActivity.TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                            //updateUI(null)
                        }

                        // ...
                    }

        }
        btLogOut.setOnClickListener {
            mAuth.signOut()
        }
        btEnviar.setOnClickListener {
            val user = mAuth.currentUser

            user?.sendEmailVerification()
                    ?.addOnCompleteListener(this, OnCompleteListener {
                        task -> if(task.isSuccessful){
                        Toast.makeText(this, "Email enviado.",
                                Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Falha no envio de e-mail.",
                                Toast.LENGTH_SHORT).show()
                    }
                    })
        }

    }
}
