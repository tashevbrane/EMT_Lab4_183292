package mk.ukim.finki.emt.ordermanagement.domain.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.PhoneId;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Entity
@Table(name="order_phone")
@Getter
public class OrderPhone extends AbstractEntity<OrderPhoneId> {

    private Money phonePrice;

    @Column(name = "qty", nullable = false)
    private int quantity;

    @AttributeOverride(name="id", column = @Column(name = "phone_id", nullable = false))
    private PhoneId phoneId;

    public OrderPhone(@NonNull PhoneId phoneId, @NonNull Money phonePrice, int qty) {
//        super(DomainObjectId.randomId(OrderPhone.class));
        this.phoneId = phoneId;
        this.phonePrice = phonePrice;
        this.quantity = qty;

    }

    public Money subtotal() {
        return phonePrice.multiply(quantity);
    }



}
