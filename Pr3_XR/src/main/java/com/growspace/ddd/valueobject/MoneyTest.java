package com.growspace.ddd.valueobject;

import com.growspace.ddd.exception.DomainException;
import org.junit.Test;
import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTest {

    @Test
    public void shouldAddSameCurrency() {
        Money m1 = new Money("UAH", new BigDecimal("100.00"));
        Money m2 = new Money("UAH", new BigDecimal("50.50"));
        Money result = m1.add(m2);
        assertEquals(new BigDecimal("150.50"), result.getAmount());
        assertEquals("UAH", result.getCurrency());
    }

    @Test(expected = DomainException.class)
    public void shouldThrowOnDifferentCurrency() {
        Money m1 = new Money("UAH", new BigDecimal("100"));
        Money m2 = new Money("USD", new BigDecimal("100"));
        m1.add(m2);
    }

    @Test
    public void shouldMultiply() {
        Money price = new Money("UAH", new BigDecimal("25.00"));
        Money total = price.multiply(4);
        assertEquals(new BigDecimal("100.00"), total.getAmount());
        assertEquals("UAH", total.getCurrency());
    }

    @Test
    public void shouldFormatCorrectly() {
        Money usd = new Money("USD", new BigDecimal("50.00"));
        Money eur = new Money("EUR", new BigDecimal("50.00"));
        Money uah = new Money("UAH", new BigDecimal("50.00"));
        assertEquals("$50.00", usd.format());
        assertEquals("€50.00", eur.format());
        assertEquals("50.00 ₴", uah.format());
    }
}