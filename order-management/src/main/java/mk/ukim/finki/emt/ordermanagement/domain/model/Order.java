package mk.ukim.finki.emt.ordermanagement.domain.model;

import jakarta.persistence.*;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.Phone;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="orders")
public class Order extends AbstractEntity<OrderId> {

    private Instant orderOn;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Column(name="order_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderPhone> orderPhoneList;

    private Order() {
        //super(OrderId.randomId(OrderId.class));
    }

    public Order(Instant now, @NonNull Currency currency) {
        this.orderOn = now;
        this.currency = currency;

    }


    public Money total() {
        return orderPhoneList.stream().map(OrderPhone::subtotal).reduce(new Money(currency,0), Money::add);
    }

    public OrderPhone addPhone(@NonNull Phone phone, int qty) {
        Objects.requireNonNull(phone,"Phone must not be null");
        var item = new OrderPhone(phone.getId(),phone.getPrice(), qty);
        orderPhoneList.add(item);
        return item;
    }

    public void removePhone(@NonNull OrderPhoneId orderPhoneId) {
        Objects.requireNonNull(orderPhoneId,"Order Phone must not be null");
        orderPhoneList.removeIf(v->v.getId().equals(orderPhoneId));
    }

}
