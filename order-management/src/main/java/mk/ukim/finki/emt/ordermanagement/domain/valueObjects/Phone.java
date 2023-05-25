package mk.ukim.finki.emt.ordermanagement.domain.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Getter
public class Phone implements ValueObject {

    private final PhoneId id;
    private final String name;
    private final Money price;

    private final int sales;

    private Phone(int sales) {
        this.sales = sales;
        this.id = PhoneId.randomId(PhoneId.class);
        this.name = "";
        this.price = Money.valueOf(Currency.MKD, 0);
    }


    @JsonCreator
    public Phone(@JsonProperty("id") PhoneId id,
                   @JsonProperty("productName") String name,
                   @JsonProperty("price") Money price,
                   @JsonProperty("sales") int sales) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sales = sales;
    }
}
