package com.simbirsoft;



import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class HibernateHelper {
    private static final EntityManager ENTITY_MANAGER = Persistence.createEntityManagerFactory("testjpa").createEntityManager();


    public void addIngredient(List<Ingredient> ingredientList) {
        ENTITY_MANAGER.getTransaction().begin();
        for (Ingredient ingredient : ingredientList) {
            ENTITY_MANAGER.merge(ingredient);
        }
        ENTITY_MANAGER.getTransaction().commit();
    }

    public List<Ingredient> getAll() {
        TypedQuery<Ingredient> namedQuery = ENTITY_MANAGER.createNamedQuery("Ingredient.getAll", Ingredient.class);
        return namedQuery.getResultList();
    }
}
