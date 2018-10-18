package com.s4y.service4you.Entities;

import java.util.Date;

/**
 * Created by boldi on 2018. 09. 26..
 */

public class Discount {
     private String Title;
     private String DiscountCode;
     private String IMG;
     private String Details;
     private Date StartDate;
     private Date EndDate;
     private int Price;

public Discount(String title, Date startDate, Date endDate){
    this.Title = title;
    this.StartDate = startDate;
    this.EndDate = endDate;
}
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDiscountCode() {
        return DiscountCode;
    }

    public void setDiscountCode(String discountCode) {
        DiscountCode = discountCode;
    }

    public String getIMG() {
        return IMG;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
    //     private String Link;
}
