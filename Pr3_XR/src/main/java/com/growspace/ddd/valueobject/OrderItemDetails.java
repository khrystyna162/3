package com.growspace.ddd.valueobject;

import com.growspace.ddd.exception.DomainException;

import java.util.Objects;

public final class OrderItemDetails {

    private final String productId;
    private final int quantity;
    private final Money unitPrice;

    public OrderItemDetails(String productId, int quantity, Money unitPrice) {
        if (productId == null || productId.isBlank()) throw new DomainException("Product ID required");
        if (quantity < 1) throw new DomainException("Quantity must be at least 1");
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public Money getUnitPrice() { return unitPrice; }

    public Money totalPrice() {
        return unitPrice.multiply(quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemDetails that = (OrderItemDetails) o;
        return quantity == that.quantity && productId.equals(that.productId) && unitPrice.equals(that.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity, unitPrice);
    }
}