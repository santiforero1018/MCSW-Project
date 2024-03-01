package edu.eci.mcsw.Model;

import java.sql.Date;

public class Bill {
    private String reference;
    private int price;
    private Boolean paid;
    private Date emisionDate;
    private Date paidDate;
    private int user_id;

    /**
     * default constructor
     *
     */
    public Bill() {
    }

    /**
     * Default constructor with parameters
     * @param reference
     * @param price
     * @param paid
     * @param emisionDate
     * @param paidDate
     */
    public Bill(String reference, int price, Boolean paid, Date emisionDate, Date paidDate, int user_id) {
        this.reference = reference;
        this.price = price;
        this.paid = paid;
        this.emisionDate = emisionDate;
        this.paidDate = paidDate;
        this.user_id = user_id;
    }
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public Boolean getPaid() {
        return paid;
    }
    public void setPaid(Boolean paid) {
        this.paid = paid;
    }
    public Date getEmisionDate() {
        return emisionDate;
    }
    public void setEmisionDate(Date emisionDate) {
        this.emisionDate = emisionDate;
    }
    public Date getPaidDate() {
        return paidDate;
    }
    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    
}
