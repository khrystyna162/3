package com.growspace.ddd.entity;

import com.growspace.ddd.exception.DomainException;
import com.growspace.ddd.valueobject.Address;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Customer {
    private final UUID id;
    private String name;
    private String email;
    private Address address;
    private final List<Order> orders = new ArrayList<>();

    public Customer(UUID id, String name, String email, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Address getAddress() { return address; }
    public List<Order> getOrders() { return Collections.unmodifiableList(orders); }

    public void updateAddress(Address newAddress) {
        boolean hasActiveOrder = orders.stream()
                .anyMatch(o -> o.getStatus() != com.growspace.ddd.enums.OrderStatus.DELIVERED &&
                        o.getStatus() != com.growspace.ddd.enums.OrderStatus.CANCELLED);
        if (hasActiveOrder) throw new DomainException("Cannot change address with active orders");
        this.address = newAddress;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}