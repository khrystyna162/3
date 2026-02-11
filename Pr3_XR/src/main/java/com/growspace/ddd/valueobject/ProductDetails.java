package com.growspace.ddd.valueobject;

import com.growspace.ddd.exception.DomainException;

import java.util.Objects;

public final class ProductDetails {

    private final String name;
    private final String description;
    private final Dimensions dimensions;

    public ProductDetails(String name, String description, Dimensions dimensions) {
        if (name == null || name.isBlank()) throw new DomainException("Product name is required");
        this.name = name;
        this.description = description;
        this.dimensions = dimensions;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public Dimensions getDimensions() { return dimensions; }

    public String shortDescription() {
        if (description == null || description.length() <= 50) return description;
        return description.substring(0, 47) + "...";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDetails that = (ProductDetails) o;
        return name.equals(that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(dimensions, that.dimensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, dimensions);
    }
}