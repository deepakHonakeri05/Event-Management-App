package com.your.company.name.eventmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    Button signInButton;
    TextView createNewAccountTextView;

    //Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        signInButton = findViewById(R.id.signButton);
        createNewAccountTextView = findViewById(R.id.createNewAccountText);

        mAuth = FirebaseAuth.getInstance();
        createNewAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    public void logUser(View v)
    {
        String email, password;

        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();

        //check whether the user has put a valid email address or not
        // check '@' symbol
        if(email.contains("@") == false){
            Toast.makeText(getApplicationContext(),"Enter valid email address",Toast.LENGTH_LONG).show();
        }

        //password length at least 8 characters
        if(password.length() < 8 ){
            Toast.makeText(getApplicationContext(),"Enter password at atleast 8 characters",Toast.LENGTH_LONG).show();
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(),"Sign In successful",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, dashboard.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(),"Check your account and internet connection",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}