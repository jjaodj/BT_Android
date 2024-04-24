package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.example.afinal.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        databaseHelper = new DatabaseHelper(this);

        binding.signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.editTextTextEmailAddress.getText().toString();
                String password = binding.editTextTextPassword.getText().toString();
                String confirmPassword = binding.editTextTextPassword3.getText().toString();

                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
                    Toast.makeText(SignupActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                else {
                    if (password.equals(confirmPassword)) {

                        if (databaseHelper.checkEmail(email)) {

                            Toast.makeText(SignupActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                            Boolean insert = databaseHelper.insertData(email, password);

                            if (insert) {
                                Toast.makeText(SignupActivity.this, "Sign up successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                               Toast.makeText(SignupActivity.this, "Sign up failed!", Toast.LENGTH_SHORT).show();
                            }


                    } else {
                        Toast.makeText(SignupActivity.this, "Invalid password!", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });

        binding.redictInsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}