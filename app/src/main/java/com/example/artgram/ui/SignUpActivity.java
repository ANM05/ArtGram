package com.example.artgram.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.artgram.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.loading)
    AVLoadingIndicatorView loadingIndicatorView;
    @BindView(R.id.name)
    EditText names;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.confirm_password)
    EditText confirm_password;
    @BindView(R.id.sign_up)
    Button signUp;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public static final String TAG = SignUpActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        loadingIndicatorView.hide();
        mAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == signUp) {
            createNewUser();
        }

    }

    private void createNewUser() {
        final String name = names.getText().toString().trim();
        final String emailInput = email.getText().toString().trim();
        String passWord = password.getText().toString().trim();
        String confirmPassword = confirm_password.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            confirm_password.setError("Please enter Name");
            return;
        }
        if (TextUtils.isEmpty(emailInput)) {
            confirm_password.setError("Password enter email address");
            return;
        }
        if (TextUtils.isEmpty(passWord)) {
            confirm_password.setError("Please enter password");
            return;
        }
        if (!(confirmPassword.equals(passWord))) {
            confirm_password.setError("Password doesn't match");
            return;
        }

        mAuth.createUserWithEmailAndPassword(emailInput, passWord)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loadingIndicatorView.hide();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            //avoids using the back btn to go back to the signup activity after successful login
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            setDisplayName(task.getResult().getUser());
                            startActivity(intent);
                        } else {
                            loadingIndicatorView.hide();
                            Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            signUp.setEnabled(true);
                        }
                    }
                });
    }

        public void setDisplayName ( final FirebaseUser firebaseUser)
        {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(names.getText().toString().trim())
                    .build();
            firebaseUser.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "User profile updated.");
                            }
                        }
                    });

        }
    }


