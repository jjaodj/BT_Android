package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.afinal.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {


    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.afinal.databinding.ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.editTextTextEmailAddress2.getText().toString();
                String password = binding.editTextTextPassword2.getText().toString();

                if (email.isEmpty() || password.isEmpty())
                    Toast.makeText(LoginActivity.this,"All fields are required!", Toast.LENGTH_SHORT).show();
                else {
                    boolean checkCredentials = databaseHelper.checkEmailPassword(email,password);

                    if (checkCredentials){
                        Toast.makeText(LoginActivity.this,"Sign in successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this,"Invalid user!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.doesnothave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });


    }

}