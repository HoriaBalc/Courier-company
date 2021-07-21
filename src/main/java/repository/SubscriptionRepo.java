package repository;


import entity.Subscription;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class SubscriptionRepo {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("couriercompany");

    public static void insertNewSubscription(Subscription sub) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(sub);
        em.getTransaction().commit();
        em.close();
    }

    public static void updateSubscription(Subscription sub) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(sub);
        em.getTransaction().commit();
        em.close();
    }

    public static void deleteSubscription(Subscription sub) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove((em.contains(sub) ? sub : em.merge(sub)));
        em.getTransaction().commit();
        em.close();
    }

    public static Subscription findSubscriptionByType(String type) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Subscription> query = builder.createQuery(Subscription.class);
        Root<Subscription> root = query.from(Subscription.class);
        query.select(root).where(builder.equal(root.get("type"), type));
        Query q=em.createQuery(query);
        if (q.getResultList().isEmpty()) {
            em.close();
            return null;
        }
        Subscription item = (Subscription) q.getSingleResult();
        em.getTransaction().commit();
        em.close();
        return item;
    }




    public static List<Subscription> getSubs() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Subscription> criteria = builder.createQuery(Subscription.class);
        criteria.from(Subscription.class);
        List<Subscription> items = em.createQuery(criteria).getResultList();
        em.close();
        return items;

    }
}
