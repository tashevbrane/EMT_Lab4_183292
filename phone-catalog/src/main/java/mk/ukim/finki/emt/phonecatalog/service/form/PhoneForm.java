package mk.ukim.finki.emt.phonecatalog.service.form;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Data
public class PhoneForm {

    private String phoneName;
    private Money price;
    private int sales;

}
