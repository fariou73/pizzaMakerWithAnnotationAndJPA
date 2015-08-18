package com.simbirsoft;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@NamedQuery(name = "Ingredient.getAll", query = "SELECT  c from Ingredient c")
@Table(name = "Ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private final String ingredientsName;
    private Integer ingredientCount;

    public Ingredient(){
        this.ingredientsName = "default";
        this.ingredientCount = 0;
    };


    public Ingredient(String ingredientsName, Integer ingredientCount) {
        this.ingredientsName = ingredientsName;
        this.ingredientCount = ingredientCount;
    }

    public String getIngredientsName() {
        return ingredientsName;
    }

    public Integer getIngredientCount() {
        return ingredientCount;
    }

    public void setIngredientCount(Integer count) {
        ingredientCount = count;
    }

    @Override
    public String toString() {
        return (ingredientsName + ' ' + ingredientCount);
    }
}
