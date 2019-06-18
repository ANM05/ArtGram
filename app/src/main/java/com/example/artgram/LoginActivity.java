package com.example.artgram;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.email)
    EditText mLoginEmail;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    private static final Pattern PASSWORD_PATTERN = Pattern
            .compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mBtnLogin.setOnClickListener(this);
        Intent intent=getIntent();
    }

    private boolean validateEmail() {
        String emailInput = mLoginEmail.getText().toString().trim();

        if (emailInput.isEmpty()) {
            mLoginEmail.setError("Field can't be empty.");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            mLoginEmail.setError("Please enter a valid email address.");
            return false;
        } else {
            mLoginEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String passwordInput=mPassword.getText().toString().trim();

        if(passwordInput.isEmpty()){
            mPassword.setError("Field can't be empty");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            mPassword.setError("Password too weak");
            return false;
        }
        else{
            mPassword.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validatePassword()) {
            return;
        }

        String input = "Email: " + mLoginEmail.getText().toString();
        input += "\n";
        input += "Password: " + mPassword.getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view){
        if(view==mBtnLogin){
            String email=mLoginEmail.getText().toString();
            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("email", email);
            startActivity(intent);
        }
    }

}
