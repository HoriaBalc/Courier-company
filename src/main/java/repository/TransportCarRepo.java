package repository;


import entity.TransportCar;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TransportCarRepo {


    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("couriercompany");
    private static String DELETE_CAR = "DELETE from TransportCar where id = :id";


    public static void insertNewCar(TransportCar car) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(car);
        em.getTransaction().commit();
        em.close();
    }

    public static void updateCar(TransportCar car) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(car);
        em.getTransaction().commit();
        em.close();
    }

    public static void deleteCar(TransportCar car) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove((em.contains(car) ? car : em.merge(car)));
        em.getTransaction().commit();
        em.close();
    }

    /*public static void deleteCarById(String id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(DELETE_CAR).setParameter("id", id);
        jpqlQuery.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }*/

    public static TransportCar findCarByName(String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<TransportCar> query = builder.createQuery(TransportCar.class);
        Root<TransportCar> root = query.from(TransportCar.class);
        query.select(root).where(builder.equal(root.get("nume"), name));
        Query q=em.createQuery(query);
        if (q.getResultList().isEmpty()) {
            em.close();
            return null;
        }
        TransportCar item = (TransportCar) q.getSingleResult();

        em.getTransaction().commit();
        em.close();
        return item;
    }

    /*public static TransportCar findCarById(String id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<TransportCar> query = builder.createQuery(TransportCar.class);
        Root<TransportCar> root = query.from(TransportCar.class);
        query.select(root).where(builder.equal(root.get("id"), id));
        Query q=em.createQuery(query);
        if (q.getResultList().isEmpty()) {
            em.close();
            return null;
        }
        TransportCar item = (TransportCar) q.getSingleResult();

        em.getTransaction().commit();
        em.close();
        return item;
    }*/

    public static List<TransportCar> getCars() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<TransportCar> criteria = builder.createQuery(TransportCar.class);
        criteria.from(TransportCar.class);
        List<TransportCar> items = em.createQuery(criteria).getResultList();
        em.close();
        return items;
    }

}
