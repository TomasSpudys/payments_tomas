package com.tomas.demo.controller;

import PaymentOperate.Payment;
import Lombok.PaymentRequest;
import PaymentOperate.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/payment")
public class PaymentController {


    @Autowired
    private PaymentService paymentService;

    public PaymentController( PaymentService paymentService ) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{paymentId}/cancel")
    public ResponseEntity<String> cancelPayment( @PathVariable Long paymentId ) {
        Payment payment = paymentService.getPaymentById(paymentId);
        if (payment == null) {
            return new ResponseEntity<>("Payment not found", HttpStatus.NOT_FOUND);
        }


        //
        boolean canceled = paymentService.cancelPayment(payment);
        if (canceled) {
            return new ResponseEntity<>("Payment cancelled successfully", HttpStatus.OK);
        } else {

            return new ResponseEntity<>("Payment cannot be cancelled at this time", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/create")
    public Response createPayment( PaymentRequest paymentRequest ) {

        Payment payment = paymentService.createPayment(paymentRequest);
        if (payment != null) {
            return Response.status(Response.Status.CREATED)
                    .entity("Mokėjimas sėkmingai sukurtas")
                    .build();
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Netinkamas TYPE1 mokėjimas")
                .build();
    }
}