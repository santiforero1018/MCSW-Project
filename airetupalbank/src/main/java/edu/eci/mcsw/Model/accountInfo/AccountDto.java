package edu.eci.mcsw.Model.accountInfo;

import lombok.Data;

@Data
public class AccountDto {

    private String number;
    private String type;
    private int amount;
    private String bank;
    private String userEmail;

    public AccountDto(){

    }

    public AccountDto(String number, String type, int amount, String bank, String userEmail){
        this.number = number;
        this.type = type;
        this.amount = amount;
        this.bank = bank;
        this.userEmail = userEmail;
    }
}
