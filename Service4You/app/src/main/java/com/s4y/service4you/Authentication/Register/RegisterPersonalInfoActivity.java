package com.s4y.service4you.Authentication.Register;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.s4y.service4you.R;

import java.util.Date;

/**
 * Created by boldi on 2018. 09. 04..
 */

public class RegisterPersonalInfoActivity extends AppCompatActivity   {

    private EditText txtSurname;
    private EditText txtMiddlename;

    private TextView txtvBirthday;

    private Button btnNext;
    private Button bdButton;

    private DatePickerDialog.OnDateSetListener dPDialogListener;
    private String Surname;
    private String MiddleName;

    private int BdYear;
    private int BdMonth;
    private int BdDay;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_personalinfo);

        txtSurname = findViewById(R.id.txt_surname_reg);
        txtMiddlename = findViewById(R.id.txt_middlename_reg);
        btnNext = findViewById(R.id.btn_next);
        bdButton = findViewById(R.id.btn_birthday);
        txtvBirthday = findViewById(R.id.txt_birthday);

        sharedPreferences = getSharedPreferences("PersonalInfoLoginPref",MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        bdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(RegisterPersonalInfoActivity.this, R.style.Theme_AppCompat_DayNight, dPDialogListener, 1980,01,01);
                dialog.setButton("Beállítás",dialog);
                dialog.setButton2("Mégse",dialog);
                dialog.show();
            }
        });
        dPDialogListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtvBirthday.setText(year+" / "+month+" / "+dayOfMonth);
                BdYear = year;
                BdMonth = month;
                BdDay = dayOfMonth;

            }
        };

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtSurname.getText().toString().equals("") || txtMiddlename.getText().toString().equals("")                        ) {
                    toastMassage("Kérjük töltse ki az összes információt!");
                } else {
                    Surname = txtSurname.getText().toString();
                    MiddleName = txtMiddlename.getText().toString();
                    editor.putString("Surname",Surname);
                    editor.putString("MiddleName",MiddleName);
                    editor.putInt("BdYear",BdYear);
                    editor.putInt("BdMonth",BdMonth);
                    editor.putInt("BdDay",BdDay);
                    editor.apply();

                    Intent i = new Intent(RegisterPersonalInfoActivity.this, RegisterLoginInfoActivity.class);
                    startActivity(i);

                }
            }
        });
    }

    private void toastMassage(String massage){
        Toast.makeText(this,massage,Toast.LENGTH_SHORT).show();
    }

}