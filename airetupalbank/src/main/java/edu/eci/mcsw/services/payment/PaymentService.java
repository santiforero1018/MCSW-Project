package edu.eci.mcsw.services.payment;

import edu.eci.mcsw.exceptions.AccountNotFoundException;
import edu.eci.mcsw.exceptions.BillNotFoundException;
import edu.eci.mcsw.exceptions.NotEnoughPoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is about public utility bill payments.
 */
public interface PaymentService {
    void doTransaction(String billReference, String accountNumber)throws BillNotFoundException, AccountNotFoundException, NotEnoughPoundException;
    void setPaidState(String billReference, String state)throws BillNotFoundException;
}
