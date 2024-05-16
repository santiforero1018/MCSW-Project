package edu.eci.mcsw.services.account;

import edu.eci.mcsw.Model.accountInfo.Account;
import edu.eci.mcsw.Model.userInfo.UserEnt;
import edu.eci.mcsw.exceptions.AccountNotFoundException;

import java.util.List;

public interface AccountService {
    void registerAccount(Account newAccount, UserEnt refUser);
    Account getAnAccount(String number) throws AccountNotFoundException;
    List<Account> getAllAccounts();
    void deleteAccount(String number) throws AccountNotFoundException;

}
