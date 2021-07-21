package repository;

import entity.Package;
import entity.PackageRequest;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

public class PackageRequestRepo {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("couriercompany");

    public static void insertNewPackRequest(PackageRequest pack){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(pack);
        em.getTransaction().commit();
        em.close();
    }

    /*public static void updatePackRequest(PackageRequest pack) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(pack);
        em.getTransaction().commit();
        em.close();
    }

    public static PackageRequest findPackageById(String id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<PackageRequest> query = builder.createQuery(PackageRequest.class);
        Root<PackageRequest> root = query.from(PackageRequest.class);
        query.select(root).where(builder.equal(root.get("id"), id));
        Query q=em.createQuery(query);
        if (q.getResultList().isEmpty()) {
            em.close();
            return null;
        }
        PackageRequest item = (PackageRequest) q.getSingleResult();
        em.getTransaction().commit();
        em.close();
        return item;
    }
*/
    public static void deletePackageRequest(PackageRequest packageRequest) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove((em.contains(packageRequest) ? packageRequest : em.merge(packageRequest)));
        em.getTransaction().commit();
        em.close();
    }


    public static PackageRequest findByPackageName(String packName) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Package pack=PackageRepo.findPackageByName(packName);

        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<PackageRequest> query = builder.createQuery(PackageRequest.class);
        Root<PackageRequest> root = query.from(PackageRequest.class);
        query.select(root).where(builder.equal(root.get("id"), pack.getId()));
        Query q=em.createQuery(query);
        if (q.getResultList().isEmpty()) {
            em.close();
            return null;
        }
        PackageRequest item = (PackageRequest) q.getSingleResult();
        em.getTransaction().commit();
        em.close();
        return item;
    }

    public static List<PackageRequest> findAllPackageRequest() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<PackageRequest> criteria = builder.createQuery(PackageRequest.class);
        criteria.from(PackageRequest.class);
        List<PackageRequest> items = em.createQuery(criteria).getResultList();
        em.close();
        return items;
    }

   /* public static List<PackageRequest> findByClient(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<PackageRequest> query = builder.createQuery(PackageRequest.class);
        Root<PackageRequest> root = query.from(PackageRequest.class);
        query.select(root).where(builder.equal(root.get("client"), user));
        Query q=em.createQuery(query);
        if (q.getResultList().isEmpty()) {
            em.close();
            return null;
        }
        ArrayList<PackageRequest> pack=(ArrayList<PackageRequest>)q.getResultList();
        em.getTransaction().commit();
        em.close();
        return pack;
    }
*/



}
