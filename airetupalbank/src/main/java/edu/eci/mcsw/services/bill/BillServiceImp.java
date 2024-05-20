package edu.eci.mcsw.services.bill;

import edu.eci.mcsw.Model.BillInfo.Bill;
import edu.eci.mcsw.Model.enums.BillStates;
import edu.eci.mcsw.Model.userInfo.UserEnt;
import edu.eci.mcsw.exceptions.BillNotFoundException;
import edu.eci.mcsw.exceptions.InvalidCredentialException;
import edu.eci.mcsw.persistence.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImp implements BillService{

    @Autowired
    private BillRepository billRepository;


    /**
     * method to register a new Bill
     * @param newBill
     */
    @Override
    public void registerNewBill(Bill newBill, UserEnt assoUser) {
        newBill.setUserRef(assoUser);
        this.billRepository.save(newBill);
    }

    /**
     * method to search for a specific Bill
     * @param email
     * @param reference
     * @return
     * @throws BillNotFoundException
     */
    @Override
    public Bill getABill(String email, String reference) throws BillNotFoundException, InvalidCredentialException {
        Bill search = this.billRepository.findByReference(reference).orElseThrow(() -> new BillNotFoundException(reference));
        if(!search.getUserRef().getEmail().equals(email)) throw new InvalidCredentialException();
        return search;
    }

    /**
     * method that returns all bills
     *
     * @return
     */
    @Override
    public List<Bill> getAllBills() {
        return this.billRepository.findAll();
    }

    /**
     * method to modify the price of a specific bill
     *
     * @param reference
     * @param price
     * @throws BillNotFoundException
     */
    @Override
    public void modifyPrice(String reference, int price) throws BillNotFoundException {
        Bill bill = this.billRepository.findByReference(reference).orElseThrow(() -> new BillNotFoundException(reference));
        bill.setModifyPrice(price);
        this.billRepository.save(bill);
    }

    /**
     * method to modify the state of a bill
     * @param reference
     * @param state
     * @throws BillNotFoundException
     */
    @Override
    public void modifyState(String reference, BillStates state) throws BillNotFoundException {
        Bill bill = this.billRepository.findByReference(reference).orElseThrow(() -> new BillNotFoundException(reference));
        bill.setState(state);
        this.billRepository.save(bill);
    }

    /**
     * method to approve modifications
     * @param reference
     * @param approved
     * @throws BillNotFoundException
     */
    @Override
    public void approveModification(String reference, Boolean approved) throws BillNotFoundException {
        Bill bill = this.billRepository.findByReference(reference).orElseThrow(() -> new BillNotFoundException(reference));
        bill.setPrice(approved ? bill.getModifyPrice() : bill.getPrice());
        bill.setModifyPrice(0);
        this.billRepository.save(bill);
    }
}
