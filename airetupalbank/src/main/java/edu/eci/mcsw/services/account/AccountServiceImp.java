package edu.eci.mcsw.services.account;

import edu.eci.mcsw.Model.accountInfo.Account;
import edu.eci.mcsw.Model.userInfo.UserEnt;
import edu.eci.mcsw.exceptions.AccountNotFoundException;
import edu.eci.mcsw.exceptions.UserNotFoundException;
import edu.eci.mcsw.persistence.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImp implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    /**
     * @param newAccount
     * @param refUser
     */
    @Override
    public void registerAccount(Account newAccount, UserEnt refUser){
        newAccount.setUserRef(refUser);
        this.accountRepository.save(newAccount);
    }

    /**
     * @param number
     * @return
     */
    @Override
    public Account getAnAccount(String number) throws AccountNotFoundException {
        return this.accountRepository.findByNumber(number).orElseThrow(AccountNotFoundException::new);
    }

    /**
     * @return
     */
    @Override
    public List<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }

    /**
     * @param number
     */
    @Override
    public void deleteAccount(String number) throws AccountNotFoundException {
        if(!this.accountRepository.existsByNumber(number)) throw new AccountNotFoundException();
        this.accountRepository.deleteByNumber(number);
    }
}
