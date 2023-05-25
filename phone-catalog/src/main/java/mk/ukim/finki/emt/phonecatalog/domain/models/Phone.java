package mk.ukim.finki.emt.phonecatalog.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import mk.ukim.finki.emt.phonecatalog.domain.valueObjects.Quantity;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

@Entity
@Table(name = "phones")
@Getter
public class Phone extends AbstractEntity<PhoneId> {

    private String phoneName;

    private int sales = 0;

    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="price_amount")),
            @AttributeOverride(name="currency",column = @Column(name = "price_currency"))
    })
    private Money price;
    private String company;

    private Phone() {
//        super(PhoneId.randomId(PhoneId.class));
    }

    public static Phone build(String phoneName, Money price, int sales) {
        Phone p = new Phone();
        p.price = price;
        p.phoneName = phoneName;
        p.sales = sales;
        return p;
    }

    public void addSales(int qty) {
        this.sales = this.sales - qty;
    }

    public void removeSales(int qty) {
        this.sales -= qty;
    }
}
