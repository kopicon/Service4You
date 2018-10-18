package com.s4y.service4you.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.s4y.service4you.DiscountListAdapter;
import com.s4y.service4you.R;
import com.s4y.service4you.Entities.Discount;


import java.util.ArrayList;
import java.util.Date;

public class HistoryActivity extends AppCompatActivity {

    private ListView lvDiscountList;
    private ImageView imgBack;

    private DiscountListAdapter dListAdapter;
    private Discount cDiscount;
    private ArrayList<Discount> discounts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        lvDiscountList = findViewById(R.id.lv_history);
        imgBack = findViewById(R.id.ivBackArrow);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoryActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        int year = 2018;
        int month = 10;
        int day = 10;
        int day2 = 20;
        Date d = new Date(year,month,day);
        Date d1 = new Date(year,month,day2);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoryActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        for(int i = 0; i <=10 ; i++){
            cDiscount = new Discount("FOGLALJON LE FÉLÁRON A 3 ÉJSZAKÁS HÉTKÖZNAPOS CSOMAGAJÁNLATOT A CEGLÉDI HOTEL AQURAELL****-BEN!",d,d1);
            discounts.add(cDiscount);
        }
        dListAdapter = new DiscountListAdapter(getApplicationContext(),R.layout.fragment_history_listview, discounts);
        lvDiscountList.setAdapter(dListAdapter);

    }
}
