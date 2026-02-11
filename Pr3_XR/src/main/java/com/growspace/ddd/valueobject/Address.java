package com.growspace.ddd.valueobject;

import com.growspace.ddd.exception.DomainException;

import java.util.Objects;

public final class Address {

    private final String country;
    private final String city;
    private final String street;
    private final String postalCode;

    public Address(String country, String city, String street, String postalCode) {
        if (country == null || country.isBlank()) throw new DomainException("Country is required");
        if (city == null || city.isBlank()) throw new DomainException("City is required");
        if (street == null || street.isBlank()) throw new DomainException("Street is required");
        if (postalCode == null || postalCode.isBlank()) throw new DomainException("Postal code is required");
        if ("UA".equals(country) && !postalCode.matches("\\d{5}")) {
            throw new DomainException("Invalid postal code for Ukraine");
        }
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    public String getCountry() { return country; }
    public String getCity() { return city; }
    public String getStreet() { return street; }
    public String getPostalCode() { return postalCode; }

    public String fullAddress() {
        return street + ", " + city + ", " + postalCode + ", " + country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return country.equals(address.country) && city.equals(address.city) &&
                street.equals(address.street) && postalCode.equals(address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, street, postalCode);
    }
}