package edu.eci.mcsw.Model.BillInfo;

import lombok.Data;

import java.sql.Date;

@Data
public class BillDto {
    private String reference;
    private int price;
    private String service;
    private String consume;
    private String company;
    private String state;
    private Date emisionDate;
    private String userEmail;
    private Boolean approvedNewPrice;
    private Date dateLimit;
    private String accountNumber;

    /**
     * default constructor
     */
    public BillDto() {
    }

    /**
     * contructor with parameters
     *
     * @param reference
     * @param price
     * @param service
     * @param consume
     * @param company
     * @param state
     * @param emisionDate
     * @param userEmail
     * @param dateLimit
     */
    public BillDto(String reference, int price, String service, String consume, String company, String state, Date emisionDate, String userEmail, Date dateLimit, String accountNumber) {
        this.reference = reference;
        this.price = price;
        this.service = service;
        this.consume = consume;
        this.company = company;
        this.state = state;
        this.emisionDate = emisionDate;
        this.userEmail = userEmail;
        this.dateLimit = dateLimit;
        this.accountNumber = accountNumber;
    }

}
