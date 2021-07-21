package repository;

import entity.Package;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PackageRepo {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("couriercompany");



    public static void insertNewPackage(Package pack) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(pack);
        em.getTransaction().commit();
        em.close();
    }

    public static void updatePackage(Package pack) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(pack);
        em.getTransaction().commit();
        em.close();
    }


    public static void deletePackage(Package pack) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove((em.contains(pack) ? pack : em.merge(pack)));
        em.getTransaction().commit();
        em.close();
    }

    public static Package findPackageByName(String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Package> query = builder.createQuery(Package.class);
        Root<Package> root = query.from(Package.class);
        query.select(root).where(builder.equal(root.get("name"), name));
        Query q=em.createQuery(query);
        if (q.getResultList().isEmpty()) {
            em.close();
            return null;
        }
        Package item = (Package) q.getSingleResult();
        em.getTransaction().commit();
        em.close();
        return item;
    }

    public static Package findPackageById(String id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Package> query = builder.createQuery(Package.class);
        Root<Package> root = query.from(Package.class);
        query.select(root).where(builder.equal(root.get("id"), id));
        Query q=em.createQuery(query);
        if (q.getResultList().isEmpty()) {
            em.close();
            return null;
        }
        Package item = (Package) q.getSingleResult();
        em.getTransaction().commit();
        em.close();
        return item;
    }


    public static List<Package> getAllPackages() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Package> criteria = builder.createQuery(Package.class);
        criteria.from(Package.class);
        List<Package> items = em.createQuery(criteria).getResultList();
        em.close();
        return items;
    }

    public static List<Package> findPackageBySource(String source) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Package> query = builder.createQuery(Package.class);
        Root<Package> root = query.from(Package.class);
        //query.select(root).where(builder.equal(root.get("source"), source));
        query.select(root).where(builder.like(root.get("source"),source+"%"));

        Query q=em.createQuery(query);
        if (q.getResultList().isEmpty()) {
            em.close();
            return null;
        }
        ArrayList<Package> pack=(ArrayList<Package>)q.getResultList();
        em.getTransaction().commit();
        em.close();
        return pack;
    }

    public static List<Package> findPackageByDestination(String destination) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Package> query = builder.createQuery(Package.class);
        Root<Package> root = query.from(Package.class);
        //query.select(root).where(builder.equal(root.get("destination"), destination));
        query.select(root).where(builder.like(root.get("destination"),destination+"%"));

        Query q=em.createQuery(query);
        if (q.getResultList().isEmpty()) {
            em.close();
            return null;
        }
        ArrayList<Package> pack=(ArrayList<Package>)q.getResultList();
        em.getTransaction().commit();
        em.close();
        return pack;
    }

    public static List<Package> findPackageBySize(int size) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Package> query = builder.createQuery(Package.class);
        Root<Package> root = query.from(Package.class);
        query.select(root).where(builder.equal(root.get("size"), size));
        Query q=em.createQuery(query);
        if (q.getResultList().isEmpty()) {
            em.close();
            return null;
        }
        ArrayList<Package> pack=(ArrayList<Package>)q.getResultList();
        em.getTransaction().commit();
        em.close();
        return pack;
    }

    public static List<Package> findPackageByUrgency(boolean urg) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Package> query = builder.createQuery(Package.class);
        Root<Package> root = query.from(Package.class);
        query.select(root).where(builder.equal(root.get("urgency"), urg));
        Query q=em.createQuery(query);
        if (q.getResultList().isEmpty()) {
            em.close();
            return null;
        }
        ArrayList<Package> pack=(ArrayList<Package>)q.getResultList();
        em.getTransaction().commit();
        em.close();
        return pack;
    }





}
