package com.s4y.service4you.Authentication.Register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.s4y.service4you.Activities.MainActivity;
import com.s4y.service4you.Entities.User;
import com.s4y.service4you.LocalDBHelper;
import com.s4y.service4you.R;

import java.util.Date;

/**
 * Created by boldi on 2018. 09. 04..
 */

public class RegisterLoginInfoActivity extends AppCompatActivity {

    private final static String TAG = "RegisterLoginInfoActivity";

    private EditText txtUserName;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtPWagain;

    private Button btnRegister;

    private String Surname;
    private String MiddleName;

    private int BdYear;
    private int BdMonth;
    private int BdDay;

    private String UserName;
    private String Email;
    private String Password;
    private String PWagain;

    private ImageButton btn_a1;
    private ImageButton btn_a2;
    private ImageButton btn_a3;
    private ImageButton btn_a4;
    private ImageButton btn_a5;
    private ImageButton btn_a6;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore DB;

    private User cUser;

    private int selectedAvatar = 0;

    private SharedPreferences sharedPreferences;
    private LocalDBHelper LDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_logininfo);

        txtUserName = findViewById(R.id.txt_username_reg);
        txtEmail = findViewById(R.id.txt_email_reg);
        txtPassword = findViewById(R.id.txt_password_reg);
        txtPWagain = findViewById(R.id.txt_pw_again);
        btnRegister = findViewById(R.id.btn_register);

        btn_a1 = findViewById(R.id.img_avatar);
        btn_a2 = findViewById(R.id.img_avatar1);
        btn_a3 = findViewById(R.id.img_avatar2);
        btn_a4 = findViewById(R.id.img_avatar3);
        btn_a5 = findViewById(R.id.img_avatar4);
        btn_a6 = findViewById(R.id.img_avatar5);

        firebaseAuth = FirebaseAuth.getInstance();
        DB = FirebaseFirestore.getInstance();

        sharedPreferences = getSharedPreferences("PersonalInfoLoginPref",MODE_PRIVATE);
        Surname = sharedPreferences.getString("Surname","");
        MiddleName = sharedPreferences.getString("MiddleName","");
        BdYear = sharedPreferences.getInt("BdYear",0);
        BdMonth = sharedPreferences.getInt("BdMonth",0);
        BdDay = sharedPreferences.getInt("BdDay",0);


        imgButtonControl();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtUserName.getText().toString().equals("") || txtEmail.getText().toString().equals("") ||
                        txtPassword.getText().toString().equals("") || txtPWagain.getText().toString().equals("")) {
                    toastMassage("Kérjük töltse ki az összes információt!");
                }

                UserName = txtUserName.getText().toString();
                Email = txtEmail.getText().toString();
                Password = txtPassword.getText().toString();
                PWagain = txtPWagain.getText().toString();
                Date bdDate = new Date(BdYear,BdMonth,BdDay);
                cUser = new User(UserName,Email,Password,Surname,MiddleName,bdDate,selectedAvatar);

                sharedPreferences = getSharedPreferences("Login",MODE_PRIVATE);
                final SharedPreferences.Editor editor = sharedPreferences.edit();

                if(!Password.equals(PWagain)) {
                    toastMassage("A jelszavak nem egyeznek meg!");
                }else{
                    firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:success");
                        DB.collection("Users").document( firebaseAuth.getCurrentUser().getUid()).set(cUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                editor.putString("Email",Email);
                                editor.putString("Password",Password);
                                editor.apply();
                                Intent i = new Intent(RegisterLoginInfoActivity.this, MainActivity.class);
                                startActivity(i);
                                RegisterLoginInfoActivity.this.finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onDBFailure: "+ e);
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onLoginFailure: "+e);
                        toastMassage("Ellenőrzd  az email címed és hogy a kód minimum 6 karakter-e");
                    }
                });
                }

            }
        });
    }

    private void imgButtonControl(){
        btn_a1.setBackgroundColor(Color.TRANSPARENT);
        btn_a2.setBackgroundColor(Color.TRANSPARENT);
        btn_a3.setBackgroundColor(Color.TRANSPARENT);
        btn_a4.setBackgroundColor(Color.TRANSPARENT);
        btn_a5.setBackgroundColor(Color.TRANSPARENT);
        btn_a6.setBackgroundColor(Color.TRANSPARENT);
        btn_a1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_a1.setBackgroundColor(Color.BLUE);
                btn_a2.setBackgroundColor(Color.TRANSPARENT);
                btn_a3.setBackgroundColor(Color.TRANSPARENT);
                btn_a4.setBackgroundColor(Color.TRANSPARENT);
                btn_a5.setBackgroundColor(Color.TRANSPARENT);
                btn_a6.setBackgroundColor(Color.TRANSPARENT);
                selectedAvatar = 1;
            }
        });
        btn_a2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_a1.setBackgroundColor(Color.TRANSPARENT);
                btn_a2.setBackgroundColor(Color.BLUE);
                btn_a3.setBackgroundColor(Color.TRANSPARENT);
                btn_a4.setBackgroundColor(Color.TRANSPARENT);
                btn_a5.setBackgroundColor(Color.TRANSPARENT);
                btn_a6.setBackgroundColor(Color.TRANSPARENT);
                selectedAvatar = 2;
            }
        });
        btn_a3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_a1.setBackgroundColor(Color.TRANSPARENT);
                btn_a2.setBackgroundColor(Color.TRANSPARENT);
                btn_a3.setBackgroundColor(Color.BLUE);
                btn_a4.setBackgroundColor(Color.TRANSPARENT);
                btn_a5.setBackgroundColor(Color.TRANSPARENT);
                btn_a6.setBackgroundColor(Color.TRANSPARENT);
                selectedAvatar = 3;
            }
        });
        btn_a4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_a1.setBackgroundColor(Color.TRANSPARENT);
                btn_a2.setBackgroundColor(Color.TRANSPARENT);
                btn_a3.setBackgroundColor(Color.TRANSPARENT);
                btn_a4.setBackgroundColor(Color.BLUE);
                btn_a5.setBackgroundColor(Color.TRANSPARENT);
                btn_a6.setBackgroundColor(Color.TRANSPARENT);
                selectedAvatar = 4;
            }
        });
        btn_a5.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_a1.setBackgroundColor(Color.TRANSPARENT);
                btn_a2.setBackgroundColor(Color.TRANSPARENT);
                btn_a3.setBackgroundColor(Color.TRANSPARENT);
                btn_a4.setBackgroundColor(Color.TRANSPARENT);
                btn_a5.setBackgroundColor(Color.BLUE);
                btn_a6.setBackgroundColor(Color.TRANSPARENT);
                selectedAvatar = 5;
            }
        });
        btn_a6.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_a1.setBackgroundColor(Color.TRANSPARENT);
                btn_a2.setBackgroundColor(Color.TRANSPARENT);
                btn_a3.setBackgroundColor(Color.TRANSPARENT);
                btn_a4.setBackgroundColor(Color.TRANSPARENT);
                btn_a5.setBackgroundColor(Color.TRANSPARENT);
                btn_a6.setBackgroundColor(Color.BLUE);
                selectedAvatar = 6;
            }
        });
    }
    private void toastMassage(String massage){
        Toast.makeText(this,massage,Toast.LENGTH_SHORT).show();
    }
}