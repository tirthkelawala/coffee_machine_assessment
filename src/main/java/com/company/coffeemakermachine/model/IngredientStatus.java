package com.company.coffeemakermachine.model;

public class IngredientStatus {

    private String ingredient;
    private boolean isAvailable;
    private boolean isSufficient;

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isSufficient() {
        return isSufficient;
    }

    public void setSufficient(boolean sufficient) {
        isSufficient = sufficient;
    }
}
