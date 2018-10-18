package com.s4y.service4you.SplashScreens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.s4y.service4you.Activities.MainActivity;
import com.s4y.service4you.Authentication.LoginActivity;
import com.s4y.service4you.Entities.User;
import com.s4y.service4you.LocalDBHelper;
import com.s4y.service4you.R;

public class SplashScreen extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore FDB;
    private User cUser;

    private TextView txtUserName;
    private ImageView imgAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);

        imgAvatar = findViewById(R.id.img_AVATAR);
        txtUserName = findViewById(R.id.txtUsErName);

        firebaseAuth = FirebaseAuth.getInstance();
        FDB = FirebaseFirestore.getInstance();
        sharedPreferences = getSharedPreferences("Login",MODE_PRIVATE);


        firebaseAuth = FirebaseAuth.getInstance();
        if(!sharedPreferences.getString("Email","").isEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(sharedPreferences.getString("Email", ""), sharedPreferences.getString("Password", "")).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    FDB.collection("Users").document(firebaseAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()){
                                cUser = task.getResult().toObject(User.class);
                                int avatar = cUser.getAvatar();
                                if (avatar == 1){
                                    imgAvatar.setImageResource(R.mipmap.img_avatar1);
                                }else if(avatar == 2){
                                    imgAvatar.setImageResource(R.mipmap.img_avatar2);
                                }else if(avatar == 3){
                                    imgAvatar.setImageResource(R.mipmap.img_avatar3);
                                }else if(avatar == 4){
                                    imgAvatar.setImageResource(R.mipmap.img_avatar4);
                                }else if(avatar == 5){
                                    imgAvatar.setImageResource(R.mipmap.img_avatar5);
                                }else if(avatar == 6){
                                    imgAvatar.setImageResource(R.mipmap.img_avatar6);
                                }
                                txtUserName.setText(cUser.getUserName());
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                                        startActivity(mainIntent);
                                        SplashScreen.this.finish();
                                    }
                                }, 1000);
                            }
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    toastMassage("Valami Elromlott kérlek jelentkezz be ujra");
                    Intent mainIntent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(mainIntent);
                    SplashScreen.this.finish();
                }
            });
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(mainIntent);
                    SplashScreen.this.finish();
                }
            }, 1000);
        }
        }
    private void toastMassage(String massage){
        Toast.makeText(this,massage,Toast.LENGTH_SHORT).show();
    }
}

