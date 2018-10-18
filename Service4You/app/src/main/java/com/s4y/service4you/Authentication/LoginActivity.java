package com.s4y.service4you.Authentication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.s4y.service4you.Authentication.Register.RegisterPersonalInfoActivity;
import com.s4y.service4you.Activities.MainActivity;
import com.s4y.service4you.Entities.User;
import com.s4y.service4you.R;

import java.util.Date;

/**
 * Created by boldi on 2018. 09. 04..
 */

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmial;
    private EditText txtPassword;

    private Button btnLogin;
    private Button btnRegister;

    private ImageButton btnFacebook;

    private String Email;
    private String Password;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore DB;

    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmial = findViewById(R.id.txt_emial_login);
        txtPassword = findViewById(R.id.txt_password_login);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register1);
        btnFacebook = findViewById(R.id.imgbtn_facebook);
        firebaseAuth = FirebaseAuth.getInstance();
        DB = FirebaseFirestore.getInstance();

        sharedPreferences = getSharedPreferences("Login",MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtEmial.getText().toString().equals("") || txtPassword.getText().toString().equals("")){
                    toastMassage("Valamelyik mező üres!");
                }else {
                    Email = txtEmial.getText().toString();
                    Password = txtPassword.getText().toString();
                    firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            editor.putString("Email",Email);
                            editor.putString("Password",Password);
                            editor.apply();
                            Intent i = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(i);
                            LoginActivity.this.finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            toastMassage("Valami elromlott próbáld újra");
                        }
                    });

                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegisterPersonalInfoActivity.class);
                startActivity(i);
            }
        });
    }

    private void toastMassage(String massage){
        Toast.makeText(this,massage,Toast.LENGTH_SHORT).show();
    }
}
