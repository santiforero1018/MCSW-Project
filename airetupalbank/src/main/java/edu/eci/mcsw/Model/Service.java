package edu.eci.mcsw.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String reference;
    @Column(unique = true)
    private String name;
    private String company;
    private int consume;

    @ManyToOne
    @JoinColumn(name = "bill_reference", referencedColumnName = "reference")
    @JsonBackReference
    private Bill bill;

    /**
     * Default constructor
     */
    public Service() {
    }


    /**
     * constructo with parameters
     * @param name
     * @param company
     * @param consume
     * @param bill
     */
    public Service(UUID reference, String name, String company, int consume, Bill bill) {
        this.reference = reference.toString();
        this.name = name;
        this.company = company;
        this.consume = consume;
        this.bill = bill;
    }
    
}
