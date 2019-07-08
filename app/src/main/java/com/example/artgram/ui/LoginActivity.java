package com.example.artgram.ui;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.artgram.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.btn_login)
    Button login;
    @BindView(R.id.registerTextView)
    TextView mRegisterTextView;
//    @BindView(R.id.login_progressbar)
//    ProgressBar logProgressBar;

    private FirebaseAuth mAuth;

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

        login.setOnClickListener(this);
        mRegisterTextView.setOnClickListener(this);
        Intent intent = getIntent();


//        showProgressBar(true);
        mAuth = FirebaseAuth.getInstance();
//        confirmInput(View v);
    }

    private boolean validateEmail() {
        String emailInput = email.getText().toString().trim();

        if (emailInput.isEmpty()) {
            email.setError("Field can't be empty.");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address.");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password too weak");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validatePassword()) {
            return;
        }

        String input = "Email: " + email.getText().toString();
        input += "\n";
        input += "Password: " + password.getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if (view == login) {
            if (isValid()) {
                login.setEnabled(false);
                mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("email", email.getText().toString());
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
//                                    finish();
                                } else {
                                    login.setEnabled(true);
                                    Toast.makeText(LoginActivity.this, task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }


        }
        if (view == mRegisterTextView) {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean isValid() {
        boolean valid = false;


        if (password.getText().toString().trim().isEmpty()) {
            password.setError("Please enter password");
        } else if (email.getText().toString().trim().isEmpty()) {
            email.setError("Please enter your email");
        } else if (!(Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches())) {
            email.setError("Email address not valid");
        } else {
            valid = true;
        }


        return valid;
    }

//    private void showProgressBar(boolean isShow) {
//        if (isShow) {
//            logProgressBar.setVisibility(View.VISIBLE);
//            login.setVisibility(View.GONE);
//        } else {
//            logProgressBar.setVisibility(View.GONE);
//            login.setVisibility(View.VISIBLE);
//        }
//    }


}
