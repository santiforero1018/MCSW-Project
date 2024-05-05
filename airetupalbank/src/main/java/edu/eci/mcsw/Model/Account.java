package edu.eci.mcsw.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "accounts")
public class Account {
    @Id
    private String number;
    private String type;
    private int amount;
    private String bank;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User userRef;


    /**
     * Default constructor
     */
    public Account() {
    }
    
    /**
     * constructor with parameters
     * @param number
     * @param type
     * @param amount
     * @param bank
     */
    public Account(String number, String type, int amount, String bank, User userRef) {
        this.number = number;
        this.type = type;
        this.amount = amount;
        this.bank = bank;
        this.userRef = userRef;
    }
    
}