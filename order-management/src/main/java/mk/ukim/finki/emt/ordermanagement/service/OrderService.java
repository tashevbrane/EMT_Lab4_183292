package mk.ukim.finki.emt.ordermanagement.service;

import mk.ukim.finki.emt.ordermanagement.domain.exception.OrderIdNotExistException;
import mk.ukim.finki.emt.ordermanagement.domain.exception.OrderPhoneIdNotExistException;
import mk.ukim.finki.emt.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderPhoneId;
import mk.ukim.finki.emt.ordermanagement.domain.repository.OrderRepository;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderPhoneForm;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderId placeOrder(OrderForm orderForm);
    List<Order> findAll();
    Optional<Order> findById(OrderId orderId);

    void addItem(OrderId orderId, OrderPhoneForm orderPhoneForm) throws OrderIdNotExistException;

    void deleteItem(OrderId orderId, OrderPhoneId orderPhoneId) throws OrderIdNotExistException, OrderPhoneIdNotExistException;
}
