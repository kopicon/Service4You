package com.s4y.service4you.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.s4y.service4you.Entities.User;
import com.s4y.service4you.R;


public class EditProfileAvtivity extends AppCompatActivity {
    private ImageView imgBack;

    private EditText txtSurname;
    private EditText txtMidleName;
    private EditText txtEmail;
    private EditText txtUserName;
    private EditText Password;

    private ImageButton btn_a1;
    private ImageButton btn_a2;
    private ImageButton btn_a3;
    private ImageButton btn_a4;
    private ImageButton btn_a5;
    private ImageButton btn_a6;

    private Button btnSave;

    private User cUser;

    private FirebaseAuth fAuth;
    private FirebaseFirestore FDB;

    private int selectedAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_avtivity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        btnSave = findViewById(R.id.btn_saveprof);
        txtSurname = findViewById(R.id.txt_surname_ed);
        txtMidleName = findViewById(R.id.txt_middlename_ed);
        txtEmail = findViewById(R.id.txt_email_ed);
        txtUserName = findViewById(R.id.txt_username_ed);
        Password = findViewById(R.id.txt_password_ed);
        setSupportActionBar(toolbar);
        FDB = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserName = txtUserName.getText().toString();
                String SurName = txtSurname.getText().toString();
                String MidName = txtMidleName.getText().toString();
                String Email = txtEmail.getText().toString();
                String pw = Password.getText().toString();
                if(!UserName.isEmpty() && !SurName.isEmpty() && !MidName.isEmpty() && !Email.isEmpty() && !pw.isEmpty()){
                    cUser = new User(UserName,Email,pw,SurName,MidName,selectedAvatar);
                    FDB.collection("Users").document(fAuth.getCurrentUser().getUid()).set(cUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent i = new Intent(EditProfileAvtivity.this, MainActivity.class);
                            startActivity(i);
                            EditProfileAvtivity.this.finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            toastMassage("Valami elromlott Próbáld újra");
                        }
                    });
                }else{ toastMassage("Ellenőrizze a mezőket");}

            }
        });
        imgBack = findViewById(R.id.ivBackArrow);
        btn_a1 = findViewById(R.id.img_avatare);
        btn_a2 = findViewById(R.id.img_avatar1e);
        btn_a3 = findViewById(R.id.img_avatar2e);
        btn_a4 = findViewById(R.id.img_avatar3e);
        btn_a5 = findViewById(R.id.img_avatar4e);
        btn_a6 = findViewById(R.id.img_avatar5e);
        imgButtonControl();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditProfileAvtivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void toastMassage(String massage){
        Toast.makeText(this,massage,Toast.LENGTH_SHORT).show();
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
}
