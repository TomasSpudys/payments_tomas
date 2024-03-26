package PaymentOperate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "users")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int type;
    private double sum;
    private String currency;
    private String debtorIban;
    private String creditorIban;
    private String creditorBankBic;

    public Payment() {
    }
}