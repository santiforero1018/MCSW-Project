package edu.eci.mcsw.services.bill;

import edu.eci.mcsw.Model.Bill;
import edu.eci.mcsw.Model.enums.BillStates;
import edu.eci.mcsw.Model.userInfo.UserEnt;
import edu.eci.mcsw.exceptions.BillNotFoundException;
import edu.eci.mcsw.exceptions.InvalidCredentialException;

import java.util.List;

public interface BillService {
    void registerNewBill(Bill newBill, UserEnt assoUser);
    Bill getABill(String email, String reference) throws BillNotFoundException, InvalidCredentialException;
    List<Bill> getAllBills();
    void modifyPrice(String reference, int price) throws BillNotFoundException;
    void modifyState(String reference, BillStates state) throws BillNotFoundException;
    void approveModification(String reference, Boolean approved) throws BillNotFoundException;

}
