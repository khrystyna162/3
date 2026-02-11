package com.growspace.ddd.entity;

import com.growspace.ddd.exception.DomainException;
import com.growspace.ddd.valueobject.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void shouldReduceStock() {
        ProductDetails details = new ProductDetails("Ноутбук", "Потужний ноут", null);
        Money price = new Money("UAH", new BigDecimal("30000.00"));
        Stock stock = new Stock(10);

        Product product = new Product(UUID.randomUUID(), details, price, stock);
        product.reduceStock(3);

        assertEquals(7, product.getStock().getQuantity());
    }

    @Test(expected = DomainException.class)
    public void shouldNotReduceStockBelowZero() {
        ProductDetails details = new ProductDetails("Ноутбук", "Потужний ноут", null);
        Money price = new Money("UAH", new BigDecimal("30000.00"));
        Stock stock = new Stock(2);

        Product product = new Product(UUID.randomUUID(), details, price, stock);
        product.reduceStock(5);
    }
}