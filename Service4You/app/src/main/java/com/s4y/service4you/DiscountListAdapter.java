package com.s4y.service4you;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.s4y.service4you.Entities.Discount;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by boldi on 2018. 09. 26..
 */

public class DiscountListAdapter extends ArrayAdapter<Discount> {
    private List<Discount> dList;
    private TextView txtTitle;
    private TextView txtPeroid;
    private ImageView imgHotel;
    private LayoutInflater mInflater;
    private int layoutResource;
    public Context mContecxt;

    public DiscountListAdapter(@NonNull Context context, int resource, List<Discount> discounts) {
        super(context, resource,discounts);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutResource = resource;
        this.mContecxt = context;
        this.dList = discounts;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = mInflater.inflate(layoutResource, parent, false);
            txtTitle = convertView.findViewById( R.id.txt_discountTitle);
            txtPeroid = convertView.findViewById(R.id.txt_peroid);
            imgHotel = convertView.findViewById(R.id.img_discounrtHotel);
        }
        String Title_ = getItem(position).getTitle();
//        String Period_ = getItem(position).getStartDate().getDay()+"/"+getItem(position).getStartDate().getMonth()+"/"+getItem(position).getStartDate().getYear()+"-tól "+
//                getItem(position).getEndDate().getDay()+"/"+getItem(position).getEndDate().getMonth()+"/"+getItem(position).getEndDate().getYear()+"-ig ";
        txtTitle.setText(Title_);
//        txtPeroid.setText(Period_);
        imgHotel.setImageResource(R.drawable.hoteleto);
        return convertView;
    }

}
