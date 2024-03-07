package com.tomas.demo;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;


@Path("/payments")
public class PaymentService {

    private static Map<Long, Payment> paymentMap = new HashMap<>();

    public static Payment getPaymentById(Long paymentId) {
        return paymentMap.get(paymentId);
    }

    public static Payment cancelPayment(Payment payment) {
        return paymentMap.get(payment);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPayment(Payment payment) {

        if (payment.getSum() <= 0 ||
                payment.getCurrency() == null ||
                payment.getDebtorIban() == null ||
                payment.getCreditorIban() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("")
                    .build();
        }

        if (payment.getType() == (PaymentType.TYPE1.getIntValue())) {
            if (!payment.getCurrency().equals("EUR") || payment.getDetails() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Netinkamas TYPE1 mokėjimas")
                        .build();
            }
        } else if (payment.getType() == PaymentType.TYPE2.getIntValue()) {
            if (!payment.getCurrency().equals("USD")) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Netinkamas TYPE2 mokėjimas")
                        .build();
            }
        } else if (payment.getType() == PaymentType.TYPE3.getIntValue()) {
            if (payment.getCreditorBankBic() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Netinkamas TYPE3 mokėjimas")
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Netinkamas mokėjimo tipas")
                    .build();
        }

        return Response.status(Response.Status.CREATED)
                .entity("Mokėjimas sėkmingai sukurtas")
                .build();
    }
}


