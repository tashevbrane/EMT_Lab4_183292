package mk.ukim.finki.emt.ordermanagement.service;

import mk.ukim.finki.emt.ordermanagement.domain.exception.OrderIdNotExistException;
import mk.ukim.finki.emt.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.Phone;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.PhoneId;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderPhoneForm;
import mk.ukim.finki.emt.ordermanagement.xport.client.PhoneClient;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderServiceImplTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PhoneClient phoneClient;

    private static Phone newPhone(String name, Money price) {
        Phone p = new Phone(PhoneId.randomId(PhoneId.class), name, price, 0);
        return p;
    }

    @Test
    public void testPlaceOrder() {

        OrderPhoneForm oi1 = new OrderPhoneForm();
        oi1.setPhone(newPhone("IPhone 12",Money.valueOf(Currency.MKD,15000)));
        oi1.setQuantity(1);

        OrderPhoneForm oi2 = new OrderPhoneForm();
        oi2.setPhone(newPhone("Samsung 12",Money.valueOf(Currency.MKD,5000)));
        oi2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setPhones(Arrays.asList(oi1,oi2));

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);
        Assertions.assertEquals(newOrder.total(),Money.valueOf(Currency.MKD,2500));

    }

    @Test
    public void testPlaceOrderWithRealData() {
        List<Phone> phoneList = phoneClient.findAll();
        System.out.println(phoneList);

        Phone p1 = phoneList.get(0);
        Phone p2 = phoneList.get(1);

        OrderPhoneForm oi1 = new OrderPhoneForm();
        oi1.setPhone(p1);
        oi1.setQuantity(1);

        OrderPhoneForm oi2 = new OrderPhoneForm();
        oi2.setPhone(p2);
        oi2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setPhones(Arrays.asList(oi1,oi2));

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);

        Money outMoney = p1.getPrice().multiply(oi1.getQuantity()).add(p2.getPrice().multiply(oi2.getQuantity()));
        Assertions.assertEquals(newOrder.total(),outMoney);
    }


}
