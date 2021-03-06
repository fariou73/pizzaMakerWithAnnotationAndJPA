package com.simbirsoft;

import java.util.List;

public class App {
    private static volatile PizzaQueue pizzaQueue = new PizzaQueue();
    private static volatile Ingredients realPizzaIngredients = new Ingredients();
    private static final int COUNT_OF_CUSTOMERS = 10;


    public static void main(String[] args) {
        realPizzaIngredients.loadFromFile();
        PluginJarLoader pluginJarLoader = new PluginJarLoader(realPizzaIngredients);
        startWorkWithPizza();
    }


    private static void startWorkWithPizza() {
        for (int i = 0; i < 5; i++) {
            Ingredients pizzaIngredients = new Ingredients();
            PizzaSize pizzaSize = new PizzaSize();
            List<String> pizzaSizes = pizzaSize.SIZES_OF_PIZZA;
            int rnd = (int) (Math.random() * pizzaSizes.size());
            pizzaSize.setCurrentSize(pizzaSizes.get(rnd));
            rnd = (int) (Math.random() * realPizzaIngredients.getIngredients().size());
            Ingredient ingrWithRandom = realPizzaIngredients.getIngredients().get(rnd);
            rnd = 1 + (int) (Math.random() * 5);
            pizzaIngredients.add(new Ingredient(ingrWithRandom.getIngredientsName(), rnd));
            if (realPizzaIngredients.isDifferenceReal(pizzaIngredients)) {
                realPizzaIngredients.difference(pizzaIngredients);
                realPizzaIngredients.saveFromFile();
                Pizza pizza = new Pizza(pizzaSize, pizzaIngredients);
                pizzaQueue.add(pizza);
                System.out.println("[in queue]");
                System.out.println(pizza);
                pizzaQueue.printAll();
                realPizzaIngredients.printAll();
                System.out.println("[/in queue]");
            } else {
                System.out.println("[not have]");
                pizzaIngredients.printAll();
                pizzaQueue.printAll();
                realPizzaIngredients.printAll();
                System.out.println("[/not have]");
            }
        }
    }
}






