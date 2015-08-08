package simbirsoft;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;
import java.util.Properties;

public class HibernateHelper {
    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;

    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();

        Properties properties = configuration.getProperties();

        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

    public HibernateHelper() {
        if (sessionFactory == null) {
            configureSessionFactory();
        }
    }

    public void addIngredient(Ingredient ingredient) {
        if (sessionFactory != null) {
            Session session = null;
            Transaction transaction = null;

            try {
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();

                session.save(ingredient);

                // Committing the change in the database.
                session.flush();
                transaction.commit();


            } catch (Exception ex) {
                ex.printStackTrace();

                // Rolling back the changes to make the data consistent in case of any failure
                // in between multiple database write operations.
                transaction.rollback();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
    }

    public void addIngredient(List<Ingredient> ingredientList) {
        if (sessionFactory != null) {
            Session session = null;
            Transaction transaction = null;

            try {
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();

                for (Ingredient ingredient : ingredientList) {
                    session.saveOrUpdate(ingredient);
                }

                // Committing the change in the database.
                session.flush();
                transaction.commit();


            } catch (Exception ex) {
                ex.printStackTrace();

                // Rolling back the changes to make the data consistent in case of any failure
                // in between multiple database write operations.
                transaction.rollback();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
    }

    public List<Ingredient> getAll() {
        List<Ingredient> result = null;
        if (sessionFactory != null) {
            Session session = null;
            Transaction transaction = null;

            try {
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();

                result = session.createQuery("from Ingredient").list();

            } catch (Exception ex) {
                ex.printStackTrace();

                // Rolling back the changes to make the data consistent in case of any failure
                // in between multiple database write operations.
                transaction.rollback();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }
}
