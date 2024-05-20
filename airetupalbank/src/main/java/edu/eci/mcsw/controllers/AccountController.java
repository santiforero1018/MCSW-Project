package edu.eci.mcsw.controllers;

import edu.eci.mcsw.Model.accountInfo.Account;
import edu.eci.mcsw.Model.accountInfo.AccountDto;
import edu.eci.mcsw.Model.userInfo.UserEnt;
import edu.eci.mcsw.exceptions.AccountNotFoundException;
import edu.eci.mcsw.exceptions.UserNotFoundException;
import edu.eci.mcsw.services.account.AccountService;
import edu.eci.mcsw.services.user.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static edu.eci.mcsw.utils.Constanst.*;

@RestController
@RequestMapping("/v1/Accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;

    @RolesAllowed({ADMIN_ROLE, USER_ROLE})
    @PostMapping("/register")
    public ResponseEntity<?> addNewAccount(@RequestBody AccountDto newData){
        try{
            Account newAccount = new Account(newData);
            UserEnt user = this.userService.getUserByEmail(newData.getUserEmail());
            this.accountService.registerAccount(newAccount,user);
            return ResponseEntity.ok("Account added correctly");
        } catch (UserNotFoundException e){
           return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }

    @RolesAllowed(ADMIN_ROLE)
    @DeleteMapping("/{number}")
    public ResponseEntity<?> deleteAccount(@PathVariable String number){
        try{
            this.accountService.deleteAccount(number);
            return ResponseEntity.ok("Account had been deleted");
        } catch(AccountNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),e.getStatusCode());
        }
    }


    @RolesAllowed({ADMIN_ROLE, AUDIT_ROLE})
    @GetMapping
    public ResponseEntity<?> getAllAccounts(){
        return ResponseEntity.ok(this.accountService.getAllAccounts());
    }

    @RolesAllowed(ADMIN_ROLE)
    @PostMapping
    public ResponseEntity<?> getAnAccount(@RequestBody AccountDto data){
        try{
            return ResponseEntity.ok(this.accountService.getAnAccount(data.getNumber()));
        } catch(AccountNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }
}
