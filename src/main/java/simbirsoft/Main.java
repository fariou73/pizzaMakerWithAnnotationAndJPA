package simbirsoft;

import java.io.File;
import java.util.List;

public class Main {
    private static volatile PizzaQueue pizzaQueue = new PizzaQueue();
    private static volatile Ingredients realPizzaIngredients = new Ingredients();
    private static final String PATH_FROM_FILE = "src/main/resources/db_dump.db3";
    private static final int COUNT_OF_CUSTOMERS = 10;

    public static void main(String[] args) {
        if ((new File(PATH_FROM_FILE)).exists()) {
            realPizzaIngredients.loadFromFile();
            PluginJarLoader pluginJarLoader = new PluginJarLoader(realPizzaIngredients);
            startWorkWithPizza();
        } else {
            System.out.println("File With ingredients not found.");
        }
    }


    private static void startWorkWithPizza() {
        for (int i = 0; i<5; i++){
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






