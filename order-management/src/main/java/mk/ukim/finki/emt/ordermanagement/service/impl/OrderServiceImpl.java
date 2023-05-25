package mk.ukim.finki.emt.ordermanagement.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.ordermanagement.domain.exception.OrderIdNotExistException;
import mk.ukim.finki.emt.ordermanagement.domain.exception.OrderPhoneIdNotExistException;
import mk.ukim.finki.emt.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderPhoneId;
import mk.ukim.finki.emt.ordermanagement.domain.repository.OrderRepository;
import mk.ukim.finki.emt.ordermanagement.service.OrderService;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderPhoneForm;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final Validator validator;

    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm, "order must not be null");
        var constraintValidation = validator.validate(orderForm);
        if (constraintValidation.size()>0) {
            throw new ConstraintViolationException("The order form is not valid", constraintValidation);
        }

        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        return newOrder.getId();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public void addItem(OrderId orderId, OrderPhoneForm orderPhoneForm) throws OrderIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.addPhone(orderPhoneForm.getPhone(),orderPhoneForm.getQuantity());
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void deleteItem(OrderId orderId, OrderPhoneId orderPhoneId) throws OrderIdNotExistException, OrderPhoneIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.removePhone(orderPhoneId);
        orderRepository.saveAndFlush(order);
    }

    private Order toDomainObject(OrderForm orderForm) {
        var order = new Order(Instant.now(), orderForm.getCurrency());
        orderForm.getPhones().forEach(item -> order.addPhone(item.getPhone(), item.getQuantity()));
        return order;
    }
}
