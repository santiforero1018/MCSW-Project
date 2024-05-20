package edu.eci.mcsw.controllers;

import edu.eci.mcsw.Model.BillInfo.BillDto;
import edu.eci.mcsw.exceptions.AccountNotFoundException;
import edu.eci.mcsw.exceptions.BillNotFoundException;
import edu.eci.mcsw.exceptions.NotEnoughPoundException;
import edu.eci.mcsw.services.payment.PaymentService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static edu.eci.mcsw.utils.Constanst.ADMIN_ROLE;
import static edu.eci.mcsw.utils.Constanst.USER_ROLE;

@RestController
@RequestMapping("/v1/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @RolesAllowed(USER_ROLE)
    @PostMapping
    public ResponseEntity<?> payAService(@RequestBody BillDto dataBill){
        try{
            this.paymentService.doTransaction(dataBill.getReference(), dataBill.getAccountNumber());
            return ResponseEntity.ok("Bill paid successfully");
        } catch (BillNotFoundException | AccountNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        } catch (NotEnoughPoundException ignored) {
            return new ResponseEntity<>(ignored.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RolesAllowed(ADMIN_ROLE)
    @PutMapping
    public ResponseEntity<?> setBillState(@RequestBody BillDto dataBill){
        try {
            this.paymentService.setPaidState(dataBill.getReference(), dataBill.getState());
            return ResponseEntity.ok("State Cahnged SuccessFully");
        } catch (BillNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }
}
