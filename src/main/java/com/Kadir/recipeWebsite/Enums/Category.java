package com.Kadir.recipeWebsite.Enums;

public enum Category {
    SOUP("Soup"),
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    APPETIZER("Appetizer"),
    SALAD("Salad"),
    MAIN_COURSE("Main Course"),
    DESSERT("Dessert");

    private final String categoryName;

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
