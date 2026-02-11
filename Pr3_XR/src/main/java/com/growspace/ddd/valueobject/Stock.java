package com.growspace.ddd.valueobject;

import com.growspace.ddd.exception.DomainException;

public final class Stock {

    private final int quantity;

    public Stock(int quantity) {
        if (quantity < 0) throw new DomainException("Stock cannot be negative");
        this.quantity = quantity;
    }

    public int getQuantity() { return quantity; }

    public Stock reduce(int amount) {
        if (amount > quantity) throw new DomainException("Not enough stock");
        return new Stock(quantity - amount);
    }

    public Stock add(int amount) {
        return new Stock(quantity + amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return quantity == stock.quantity;
    }

    @Override
    public int hashCode() {
        return quantity;
    }
}