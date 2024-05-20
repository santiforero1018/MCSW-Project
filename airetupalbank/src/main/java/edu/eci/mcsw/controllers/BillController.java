package edu.eci.mcsw.controllers;

import edu.eci.mcsw.Model.BillInfo.Bill;
import edu.eci.mcsw.Model.BillInfo.BillDto;
import edu.eci.mcsw.Model.enums.BillStates;
import edu.eci.mcsw.Model.userInfo.UserEnt;
import edu.eci.mcsw.exceptions.BillNotFoundException;
import edu.eci.mcsw.exceptions.InvalidCredentialException;
import edu.eci.mcsw.exceptions.UserNotFoundException;
import edu.eci.mcsw.services.bill.BillService;
import edu.eci.mcsw.services.user.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static edu.eci.mcsw.utils.Constanst.ADMIN_ROLE;
import static edu.eci.mcsw.utils.Constanst.AUDIT_ROLE;

@RestController
@RequestMapping("/v1/Bills")
public class BillController {

    @Autowired
    private UserService userService;

    @Autowired
    private BillService billService;

    @RolesAllowed(ADMIN_ROLE)
    @PostMapping
    public ResponseEntity<?> newBill(@RequestBody BillDto newBill) {
        try {
            UserEnt rUser = this.userService.getUserByEmail(newBill.getUserEmail());
            this.billService.registerNewBill(new Bill(newBill), rUser);
            return ResponseEntity.ok("Bill asigned and created correctly");
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }

    @RolesAllowed({ADMIN_ROLE, AUDIT_ROLE})
    @GetMapping
    public ResponseEntity<?> allBills() {
        return ResponseEntity.ok(this.billService.getAllBills());
    }

    //    @RolesAllowed({ADMIN_ROLE,AUDIT_ROLE})
    @PostMapping("/Bill")
    public ResponseEntity<?> searchForABill(@RequestBody BillDto queryBill) {
        try {
            return ResponseEntity.ok(this.billService.getABill(queryBill.getUserEmail(), queryBill.getReference()));
        } catch (BillNotFoundException | InvalidCredentialException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }

    @RolesAllowed(ADMIN_ROLE)
    @PutMapping("/price")
    public ResponseEntity<?> setPrice(@RequestBody BillDto data) {
        try {
            this.billService.modifyPrice(data.getReference(), data.getPrice());
            return ResponseEntity.ok("Petition Ok, waiting the Auditor's response");
        } catch (BillNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }

    @RolesAllowed(ADMIN_ROLE)
    @PutMapping("/state")
    public ResponseEntity<?> setState(@RequestBody BillDto data) {
        try {
            String state = data.getState();
            this.billService.modifyState(data.getReference(), (state.equals("OK")) ? BillStates.OK :
                    ((state.equals("Pending")) ? BillStates.PENDING : BillStates.FAILED));
            return ResponseEntity.ok("State changed correctly");
        } catch (BillNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }

    @RolesAllowed(AUDIT_ROLE)
    @PutMapping
    public ResponseEntity<?> approveChanges(@RequestBody BillDto confirmed){
        try{
            this.billService.approveModification(confirmed.getReference(), confirmed.getApprovedNewPrice());
            return ResponseEntity.ok("Action commited, please contact the Admin");
        } catch (BillNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }
}
