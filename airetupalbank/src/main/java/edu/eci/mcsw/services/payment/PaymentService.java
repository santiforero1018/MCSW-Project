package edu.eci.mcsw.services.payment;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is about public utility bill payments.
 */
public interface PaymentService {
    void doTransaction();
    void setPaidState();
}
