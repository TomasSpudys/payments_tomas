package com.tomas.demo.controller;

import com.tomas.demo.Payment;
import com.tomas.demo.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/payment")
public class PaymentController {


    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{paymentId}/cancel")
    public ResponseEntity<String> cancelPayment(@PathVariable Long paymentId) {
        Payment payment = PaymentService.getPaymentById(paymentId);
        if (payment == null) {
            return new ResponseEntity<>("Payment not found", HttpStatus.NOT_FOUND);
        }

        Payment cancellationSuccess = PaymentService.cancelPayment(payment);
        if (cancellationSuccess! =null) {
            return new ResponseEntity<>("Payment cancelled successfully", HttpStatus.OK);
        } else {

            return new ResponseEntity<>("Payment cannot be cancelled at this time", HttpStatus.BAD_REQUEST);
        }

    }
}