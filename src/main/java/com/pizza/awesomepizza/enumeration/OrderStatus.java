package com.pizza.awesomepizza.enumeration;

public enum OrderStatus implements EnumTemplate<String> {
    PLACED("Placed"),
    IN_PROGRESS("In progress"),
    DELIVERY("Delivery"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    public final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }

}
