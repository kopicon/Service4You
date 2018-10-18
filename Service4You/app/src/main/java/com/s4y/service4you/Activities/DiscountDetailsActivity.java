package com.s4y.service4you.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.s4y.service4you.R;

public class DiscountDetailsActivity extends AppCompatActivity {


    private TextView txtTitle;
    private TextView txtCode;
    private TextView txtStartDate;
    private TextView txtEndDate;
    private TextView txtMassage;
    private ImageView imgBack;
    private ImageView img_hotel;

    private String Tite;
    private String Code;
    private String StartDate;
    private String EndDate;
    private String Massage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_details);
        imgBack = findViewById(R.id.ivBackArrow);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DiscountDetailsActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
