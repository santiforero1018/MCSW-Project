package edu.eci.mcsw.services.payment;

import edu.eci.mcsw.Model.BillInfo.Bill;
import edu.eci.mcsw.Model.accountInfo.Account;
import edu.eci.mcsw.Model.enums.BillStates;
import edu.eci.mcsw.exceptions.AccountNotFoundException;
import edu.eci.mcsw.exceptions.BillNotFoundException;
import edu.eci.mcsw.exceptions.NotEnoughPoundException;
import edu.eci.mcsw.persistence.AccountRepository;
import edu.eci.mcsw.persistence.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BillRepository billRepository;

    /**
     * @param billReference
     * @param accountNumber
     * @throws BillNotFoundException
     * @throws AccountNotFoundException
     * @throws NotEnoughPoundException
     */
    @Override
    public void doTransaction(String billReference, String accountNumber) throws BillNotFoundException, AccountNotFoundException, NotEnoughPoundException {
        Bill bill = this.billRepository.findByReference(billReference).orElseThrow(() -> new BillNotFoundException(billReference));
        Account account = this.accountRepository.findByNumber(accountNumber).orElseThrow(AccountNotFoundException::new);
        if (account.getAmount() < bill.getPrice()) throw new NotEnoughPoundException();
        account.setAmount(account.getAmount() - bill.getPrice());
        bill.setPaidDate(new Date(System.currentTimeMillis()));
        this.billRepository.save(bill);
        this.accountRepository.save(account);
    }

    /**
     * @param billReference
     * @param state
     * @throws BillNotFoundException
     */
    @Override
    public void setPaidState(String billReference, String state) throws BillNotFoundException {
        Bill bill = this.billRepository.findByReference(billReference).orElseThrow(() -> new BillNotFoundException(billReference));
        bill.setState((state.equals("PAID") && !bill.getPaidDate().after(bill.getDateLimit())) ? BillStates.OK :
                ((state.equals("FAILED")) ? BillStates.FAILED : BillStates.LATE));
        this.billRepository.save(bill);
    }
}
