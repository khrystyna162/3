package com.growspace.ddd.valueobject;

import com.growspace.ddd.exception.DomainException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Money {

    private final String currency;
    private final BigDecimal amount;

    public Money(String currency, BigDecimal amount) {
        if (currency == null || currency.isBlank()) {
            throw new DomainException("Currency is required");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainException("Amount must be non-negative");
        }
        this.currency = currency.trim().toUpperCase();
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new DomainException("Currencies must match");
        }
        return new Money(currency, amount.add(other.amount));
    }

    public Money subtract(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new DomainException("Currencies must match");
        }
        return new Money(currency, amount.subtract(other.amount));
    }

    public Money multiply(int quantity) {
        if (quantity <= 0) {
            throw new DomainException("Quantity must be positive");
        }
        return new Money(currency, amount.multiply(BigDecimal.valueOf(quantity)));
    }

    public String format() {
        if ("USD".equals(currency)) return "$" + amount;
        if ("EUR".equals(currency)) return "€" + amount;
        if ("UAH".equals(currency)) return amount + " ₴";
        return amount + " " + currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return currency.equals(money.currency) && amount.compareTo(money.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }

    @Override
    public String toString() {
        return format();
    }
}