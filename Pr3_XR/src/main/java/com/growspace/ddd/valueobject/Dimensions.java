package com.growspace.ddd.valueobject;

import com.growspace.ddd.exception.DomainException;

import java.util.Objects;

public final class Dimensions {

    private final double length;
    private final double width;
    private final double height;

    public Dimensions(double length, double width, double height) {
        if (length <= 0 || width <= 0 || height <= 0) {
            throw new DomainException("Dimensions must be positive");
        }
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double getLength() { return length; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }

    public double volume() {
        return length * width * height;
    }

    public boolean isWithinLimit(double maxLength, double maxWidth, double maxHeight) {
        return length <= maxLength && width <= maxWidth && height <= maxHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dimensions that = (Dimensions) o;
        return Double.compare(that.length, length) == 0 &&
                Double.compare(that.width, width) == 0 &&
                Double.compare(that.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width, height);
    }
}