package com.growspace.ddd.entity;

import com.growspace.ddd.entity.Customer;
import com.growspace.ddd.enums.OrderStatus;
import com.growspace.ddd.exception.DomainException;
import com.growspace.ddd.valueobject.Address;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class CustomerTest {

    @Test(expected = DomainException.class)
    public void shouldNotChangeAddressWithActiveOrders() {
        Customer customer = new Customer(UUID.randomUUID(), "Іван", "ivan@example.com",
                new Address("UA", "Ужгород", "вул. Корятовича", "88000"));

        Order activeOrder = new Order(UUID.randomUUID(), customer,
                new Address("UA", "Ужгород", "вул. Корятовича", "88000"));
        activeOrder.changeStatus(OrderStatus.NEW);
        customer.addOrder(activeOrder);

        customer.updateAddress(new Address("UA", "Львів", "вул. Свободи", "79000"));
    }
}