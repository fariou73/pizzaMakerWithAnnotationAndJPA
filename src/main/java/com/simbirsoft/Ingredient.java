package com.simbirsoft;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@NamedQuery(name = "Ingredient.getAll", query = "from Ingredient")
@Table(name = "Ingredient")
public class Ingredient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name", length = 128)
    private final String ingredientsName;
    @Column(name = "count")
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
