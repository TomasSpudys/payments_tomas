package PaymentOperate;

import com.tomas.demo.PaymentRepository;
import Lombok.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Path;
import java.util.Optional;


@Path("/payments")
public class PaymentService {


    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService( PaymentRepository paymentRepository ) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createPayment( PaymentRequest paymentRequest ) {
        Payment payment = new Payment();

        //PaymentType paymentTyp
        payment.setType(paymentRequest.getType());
        payment.setCurrency(paymentRequest.getCurrency());
        payment.setSum(paymentRequest.getAmount());

        return paymentRepository.save(payment);
    }

    public Payment getPaymentById( Long id ) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        return paymentOptional.orElse(null); // Return null if payment not found, handle accordingly
    }

    public boolean cancelPayment( Payment payment ) {
        // Padaryti veiksumus duombazeje nauojant  PaymentRepository paymentRepository\


        return false; // Laikinai grazina reiksme kad nemestu error,
    }
}