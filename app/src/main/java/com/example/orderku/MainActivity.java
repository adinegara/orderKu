package com.example.orderku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewRegister;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        buttonLogin = (Button) findViewById(R.id.button_signin);
        editTextEmail = (EditText) findViewById(R.id.editText_email);
        editTextPassword = (EditText) findViewById(R.id.editText_password);
        textViewRegister = (TextView) findViewById(R.id.textviewRegister);

        buttonLogin.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);
    }

private void loginUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

    if(TextUtils.isEmpty(email)){
        Toast.makeText(this, "Masukkan email", Toast.LENGTH_SHORT).show();
        return;
    }
    if(TextUtils.isEmpty(password)){
        Toast.makeText(this, "Masukkan password", Toast.LENGTH_SHORT).show();
        return;
    }
    progressDialog.setMessage("Mendaftarkan...");
    progressDialog.show();

    firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                 progressDialog.dismiss();
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Berhasil mendaftar", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "gagal, coba lagi", Toast.LENGTH_SHORT).show();
                    }
                }
            });
}
    @Override
    public void onClick(View view) {
        if(view==buttonLogin){
        loginUser();
}
        if(view==textViewRegister){
            startActivity(new Intent(this, Register.class));
            finish();
        }
    }
}
