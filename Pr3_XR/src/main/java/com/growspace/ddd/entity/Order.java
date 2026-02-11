package com.growspace.ddd.entity;

import com.growspace.ddd.enums.OrderStatus;
import com.growspace.ddd.exception.DomainException;
import com.growspace.ddd.valueobject.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final Customer customer;
    private final List<OrderItemDetails> items = new ArrayList<>();
    private Money totalPrice;
    private OrderStatus status;
    private Address shippingAddress;

    public Order(UUID id, Customer customer, Address shippingAddress) {
        this.id = id;
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.totalPrice = new Money("UAH", BigDecimal.ZERO);
        this.status = OrderStatus.NEW;
    }

    public UUID getId() { return id; }
    public Customer getCustomer() { return customer; }
    public OrderStatus getStatus() { return status; }
    public Address getShippingAddress() { return shippingAddress; }
    public Money getTotalPrice() { return totalPrice; }
    public List<OrderItemDetails> getItems() { return Collections.unmodifiableList(items); }

    public void addItem(OrderItemDetails item) {
        if (status != OrderStatus.NEW) throw new DomainException("Cannot modify shipped or later order");
        items.add(item);
        recalculateTotal();
    }

    public void changeStatus(OrderStatus newStatus) {
        if (this.status == OrderStatus.SHIPPED && newStatus != OrderStatus.DELIVERED) {
            throw new DomainException("Shipped order can only be delivered");
        }
        this.status = newStatus;
    }

    public void updateShippingAddress(Address newAddress) {
        if (status == OrderStatus.SHIPPED || status == OrderStatus.DELIVERED) {
            throw new DomainException("Cannot change address after shipping");
        }
        this.shippingAddress = newAddress;
    }

    private void recalculateTotal() {
        BigDecimal sum = BigDecimal.ZERO;
        for (OrderItemDetails item : items) {
            BigDecimal itemTotal = item.getUnitPrice().getAmount().multiply(BigDecimal.valueOf(item.getQuantity()));
            sum = sum.add(itemTotal);
        }
        this.totalPrice = new Money("UAH", sum);
    }
}