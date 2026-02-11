package com.growspace.ddd.entity;

import com.growspace.ddd.enums.OrderStatus;
import com.growspace.ddd.exception.DomainException;
import com.growspace.ddd.valueobject.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void shouldAddItemAndRecalculateTotal() {
        Customer customer = new Customer(UUID.randomUUID(), "Іван", "ivan@example.com",
                new Address("UA", "Ужгород", "вул. Корятовича", "88000"));
        Order order = new Order(UUID.randomUUID(), customer,
                new Address("UA", "Ужгород", "вул. Корятовича", "88000"));

        Money price = new Money("UAH", new BigDecimal("100.00"));
        OrderItemDetails item = new OrderItemDetails("prod-1", 2, price);

        order.addItem(item);

        assertEquals(1, order.getItems().size());
        assertEquals(new BigDecimal("200.00"), order.getTotalPrice().getAmount());
    }

    @Test(expected = DomainException.class)
    public void shouldNotAddItemAfterShipping() {
        Customer customer = new Customer(UUID.randomUUID(), "Іван", "ivan@example.com",
                new Address("UA", "Ужгород", "вул. Корятовича", "88000"));
        Order order = new Order(UUID.randomUUID(), customer,
                new Address("UA", "Ужгород", "вул. Корятовича", "88000"));
        order.changeStatus(OrderStatus.SHIPPED);

        Money price = new Money("UAH", new BigDecimal("100.00"));
        OrderItemDetails item = new OrderItemDetails("prod-1", 1, price);
        order.addItem(item); // Має кинути виняток
    }

    @Test(expected = DomainException.class)
    public void shouldNotChangeAddressAfterShipping() {
        Customer customer = new Customer(UUID.randomUUID(), "Іван", "ivan@example.com",
                new Address("UA", "Ужгород", "вул. Корятовича", "88000"));
        Order order = new Order(UUID.randomUUID(), customer,
                new Address("UA", "Ужгород", "вул. Корятовича", "88000"));
        order.changeStatus(OrderStatus.SHIPPED);

        order.updateShippingAddress(new Address("UA", "Київ", "вул. Хрещатик", "01001"));
    }
}