package com.growspace.ddd;

import com.growspace.ddd.entity.*;
import com.growspace.ddd.valueobject.*;
import com.growspace.ddd.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.UUID;

public class Demo {
    public static void main(String[] args) {

        Customer customer = new Customer(UUID.randomUUID(), "Христина", "hrystyna@example.com",
                new Address("UA", "Ужгород", "вул. Корятовича", "88000"));


        Order order = new Order(UUID.randomUUID(), customer,
                new Address("UA", "Ужгород", "вул. Корятовича", "88000"));


        Money price = new Money("UAH", new BigDecimal("500.00"));
        OrderItemDetails item = new OrderItemDetails("prod-123", 3, price);
        order.addItem(item);

        System.out.println("Замовлення створено!");
        System.out.println("Клієнт: " + customer.getName());
        System.out.println("Загальна сума: " + order.getTotalPrice().format());
        System.out.println("Статус: " + order.getStatus());
    }
}