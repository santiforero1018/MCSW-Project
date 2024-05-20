package edu.eci.mcsw.Model.accountInfo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.eci.mcsw.Model.userInfo.UserEnt;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEnt userRef;


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
    public Account(String number, String type, int amount, String bank, UserEnt userRef) {
        this.number = number;
        this.type = type;
        this.amount = amount;
        this.bank = bank;
        this.userRef = userRef;
    }

    public Account(AccountDto accountDto){
        this.number = accountDto.getNumber();
        this.type = accountDto.getType();
        this.amount = accountDto.getAmount();
        this.bank = accountDto.getBank();
    }

    public void removeUserRef() {
        this.userRef = null;
    }
    
}