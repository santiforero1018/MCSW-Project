package edu.eci.mcsw.Model;

public class Account {
    private String num;
    private String type;
    private int amount;
    private String bank;

    /**
     * Default constructor
     */
    public Account() {
    }
    
    /**
     * constructor with parameters
     * @param num
     * @param type
     * @param amount
     * @param bank
     */
    public Account(String num, String type, int amount, String bank) {
        this.num = num;
        this.type = type;
        this.amount = amount;
        this.bank = bank;
    }

    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getBank() {
        return bank;
    }
    public void setBank(String bank) {
        this.bank = bank;
    }

    
}