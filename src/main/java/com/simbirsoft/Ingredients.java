package com.simbirsoft;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Ingredients implements Iterable<Ingredient> {

    private final List<Ingredient> ingredientsList = new ArrayList<Ingredient>();


    public Iterator<Ingredient> iterator() {
        return ingredientsList.iterator();
    }

    public List<Ingredient> getIngredients() {
        return ingredientsList;
    }

    public void saveFromFile() {
       new HibernateHelper().addIngredient(ingredientsList);
    }

    public void loadFromFile() {
       ingredientsList.addAll(new HibernateHelper().getAll());
    }

    public boolean isDifferenceReal(Ingredients ingredients) {
        boolean result = true;
        for (Ingredient ingredient : ingredients.getIngredients()) {
            Ingredient ingredient1FromDifference = get(ingredient.getIngredientsName());
            if (ingredient1FromDifference != null) {
                if ((ingredient1FromDifference.getIngredientCount() - ingredient.getIngredientCount()) < 0) {
                    result = false;
                }
            } else {
                result = false;
            }
        }
        return result;
    }

    public void difference(Ingredients ingredients) {
        for (Ingredient ingredient : ingredients.getIngredients()) {
            Ingredient ingredient1FromDifference = get(ingredient.getIngredientsName());
            ingredient1FromDifference.setIngredientCount(ingredient1FromDifference.getIngredientCount() - ingredient.getIngredientCount());
        }
    }

    public Ingredient get(String name) {
        Ingredient result = null;
        for (Ingredient ingredient : ingredientsList) {
            if (ingredient.getIngredientsName() == name) {
                result = ingredient;
            }
        }
        return result;
    }

    public void add(Ingredient ingredient) {
        ingredientsList.add(ingredient);
    }

    public void clear() {
        ingredientsList.clear();
    }

    public void printAll() {
        int j = 0;
        for (Ingredient myIngredient : ingredientsList) {
            j++;
            System.out.println(j + ") " + myIngredient);
        }
    }
}
