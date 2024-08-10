package com.pizza.awesomepizza.enumeration;

public enum ProductCategory implements EnumTemplate<String> {
    APPETIZERS("Appetizers"),
    FIRST_COURSES("First Courses"),
    MAIN_COURSES("Main Courses"),
    SIDE_DISHES("Side Dishes"),
    PIZZAS("Pizzas"),
    DESSERTS("Desserts");

    public final String value;

    ProductCategory(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }

}
