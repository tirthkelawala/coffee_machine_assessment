package com.company.coffeemakermachine.util;

import com.company.coffeemakermachine.model.IngredientStatus;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CoffeeMachineUtil {

    private Map<String, Integer> ingredientToQuantityMap;

    public void initialIngredients(Map<String, Integer> total_items_quantity) {
        ingredientToQuantityMap = new ConcurrentHashMap<>();
        ingredientToQuantityMap.putAll(total_items_quantity);
    }

    public void makeBeverages(Map<String, Map<String, Integer>> beverages) {

        Set<String> beverageSet = beverages.keySet();
        beverageSet.parallelStream().forEach(beverage -> {
            IngredientStatus unavailableIngredient = reserveIngredients(ingredientToQuantityMap, beverages.get(beverage));

            if (!unavailableIngredient.isAvailable()) {
                System.out.println(beverage + " cannot be prepared because " + unavailableIngredient.getIngredient() + " is not available");
            } else if (!unavailableIngredient.isSufficient()) {
                System.out.println(beverage + " cannot be prepared because " + unavailableIngredient.getIngredient() + " is not sufficient");
            } else {
                System.out.println(beverage + " is prepared");
            }
        });
    }

    private synchronized IngredientStatus reserveIngredients(Map<String, Integer> ingredientToQuantityMap, Map<String, Integer> ingredients) {
        IngredientStatus ingredientStatus = new IngredientStatus();
        ingredientStatus.setSufficient(true);
        ingredientStatus.setAvailable(true);

        ingredients.forEach((key, value) -> {
            if (!ingredientToQuantityMap.containsKey(key)) {
                if (ingredientStatus.isSufficient()) {
                    ingredientStatus.setIngredient(key);
                    ingredientStatus.setAvailable(false);
                }
            } else {
                if (value > ingredientToQuantityMap.get(key)) {
                    if (ingredientStatus.isAvailable()) {
                        ingredientStatus.setIngredient(key);
                        ingredientStatus.setSufficient(false);
                    }
                }
            }
        });

        if (ingredientStatus.isAvailable() && ingredientStatus.isSufficient()) {
            ingredients.forEach((key, value) -> {
                ingredientToQuantityMap.put(key, ingredientToQuantityMap.get(key) - value);
            });
        }

        Set<String> ingredientSet = ingredientToQuantityMap.keySet();

        for (String ingredient : ingredientSet) {
            if (ingredientToQuantityMap.get(ingredient) == 0) {
                ingredientToQuantityMap.remove(ingredient);
            }
        }
        return ingredientStatus;
    }

}
