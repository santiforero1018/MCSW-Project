package edu.eci.mcsw.controllers;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is about public utility bill payments.
 */
public class PaymentService {

    private List<Service> services;

    /**
     * Constructor of the class
     */
    public PaymentService() {
        this.services = new ArrayList<>();
    }

    /**
     * Add a new service
     * @param service
     */
    public void addService(Service service) {
        services.add(service);
    }

    /**
     * Return all services so far
     * @return
     */
    public List<Service> getAllServices() {
        return services;
    }

    /**
     * Get a service by id
     * @param id
     * @return
     */
    public Service getServiceById(String id) {
        for (Service service : services) {
            if (service.getId().equals(id)) {
                return service;
            }
        }
        return null;
    }

}
