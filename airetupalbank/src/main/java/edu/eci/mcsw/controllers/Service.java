package edu.eci.mcsw.controllers;

import java.util.Date;

/**
 * This class is for the public service, and all its information
 */
public class Service {

    private String id;
    private String serviceName;
    private String referenceNumber;
    private double amountToPay;
    private Date dueDate;
    private boolean isPaid = false;
    private String state;

    /**
     * Constructor of the class
     * @param id
     * @param serviceName
     * @param referenceNumber
     * @param amountToPay
     * @param dueDate
     * @param state
     */
    public Service(String id, String serviceName, String referenceNumber, double amountToPay, Date dueDate, String state){
        this.id = id;
        this.serviceName = serviceName;
        this.referenceNumber = referenceNumber;
        this.amountToPay = amountToPay;
        this.dueDate = dueDate;
        this.state = state;
    }

    /**
     * Return _id of the service
     * @return
     */
    public String getId(){return id;}

    /**
     * Change the payment for the service and its status
     * @param user
     * @param _isPaid
     * @return
     */
    public String setPaid(User user, Boolean _isPaid){
        if(user.getRole().equals("Admin")){
            isPaid = _isPaid;
            return "Successful changes";
        }
        return "Permission denied";
    }
}
