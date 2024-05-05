package edu.eci.mcsw.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String reference;
    private int price;
    private Boolean paid;
    private Date emisionDate;
    private Date paidDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User userRef;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Service> services;

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
    public Bill(UUID reference, int price, Boolean paid, Date emisionDate, Date paidDate, User userRef, List<Service> services) {
        this.reference = reference.toString();
        this.price = price;
        this.paid = paid;
        this.emisionDate = emisionDate;
        this.paidDate = paidDate;
        this.userRef = userRef;
        this.services = services;
    }
    public Bill(int price, Boolean paid, Date emisionDate, Date paidDate, User userRef,List<Service> services) {
        this.price = price;
        this.paid = paid;
        this.emisionDate = emisionDate;
        this.paidDate = paidDate;
        this.userRef = userRef;
        this.services = services;
    }

    public void addService(Service newService){
        if(!services.contains(newService)){
            services.add(newService);
            newService.setBill(this);
        }
    }

    
}
