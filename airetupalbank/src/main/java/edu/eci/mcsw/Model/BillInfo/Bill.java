package edu.eci.mcsw.Model.BillInfo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.eci.mcsw.Model.enums.BillStates;
import edu.eci.mcsw.Model.userInfo.UserEnt;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
@Entity(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String reference;
    private int price;
    private int modifyPrice;
    private String service;
    private BillStates state;
    private String consume;
    private String company;
    private Date emisionDate;
    private Date paidDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEnt userRef;


    /**
     * default constructor
     */
    public Bill() {
    }

    /**
     * Constructor without reference parameter
     *
     * @param price
     * @param consume
     * @param company
     * @param emisionDate
     * @param paidDate
     * @param userRef
     */
    public Bill(int price, String service, String consume, String company, Date emisionDate, Date paidDate, UserEnt userRef) {
        this.price = price;
        this.service = service;
        this.state = BillStates.PENDING;
        this.consume = consume;
        this.company = company;
        this.emisionDate = emisionDate;
        this.paidDate = paidDate;
        this.userRef = userRef;
    }

    public Bill(BillDto billDto){
        this.price = billDto.getPrice();
        this.service = billDto.getService();
        this.state = BillStates.PENDING;
        this.consume = billDto.getConsume();
        this.company = billDto.getCompany();
        this.emisionDate = getEmisionDate();
    }

    @PrePersist
    public void genRef(){
        if (this.reference == null) {
            this.reference = "1018" + String.format("%08d", UUID.randomUUID().hashCode() % 10000000);
        }
    }

    public void removeUserRef() {
        this.userRef = null;
    }


}
