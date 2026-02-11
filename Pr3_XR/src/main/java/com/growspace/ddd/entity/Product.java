package com.growspace.ddd.entity;

import com.growspace.ddd.exception.DomainException;
import com.growspace.ddd.valueobject.*;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final UUID id;
    private ProductDetails details;
    private Money price;
    private Stock stock;

    public Product(UUID id, ProductDetails details, Money price, Stock stock) {
        this.id = id;
        this.details = details;
        this.price = price;
        this.stock = stock;
    }

    public UUID getId() { return id; }
    public ProductDetails getDetails() { return details; }
    public Money getPrice() { return price; }
    public Stock getStock() { return stock; }

    public void updatePrice(Money newPrice) {
        if (newPrice.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainException("Price must be positive");
        }
        this.price = newPrice;
    }

    public void reduceStock(int quantity) {
        this.stock = stock.reduce(quantity);
    }

    public boolean hasEnoughStock(int quantity) {
        return stock.getQuantity() >= quantity;
    }
}