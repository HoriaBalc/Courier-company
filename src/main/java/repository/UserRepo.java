package repository;


import entity.Subscription;

import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

import java.util.List;


public class UserRepo {

	private static String USER_BY_USERNAME_QUERY = "SELECT a from User a where a.username = :username";
	private static String DELETE_USER_QUERY = "DELETE FROM user WHERE username=?";
	private static String UPDATE_USER_QUERY = "UPDATE user u set u.password=?, u.type=? WHERE u.name=?";
	private static String INSERT_USER_QUERY = "INSERT INTO user (id, name, password, type, username)"+ " VALUES (?,?,?,?,?)";;

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("couriercompany");



	public static void insertNewUser(User user) {
		EntityManager em = entityManagerFactory.createEntityManager();

		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	public static void updateUser(User user) {
		EntityManager em = entityManagerFactory.createEntityManager();

		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		em.close();
	}



	public static void deleteUser(User user) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.remove((em.contains(user) ? user : em.merge(user)));
		em.getTransaction().commit();
		em.close();
	}


	public static User findUserByUsername(String name) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(builder.equal(root.get("username"), name));
		Query q=em.createQuery(query);
		if (q.getResultList().isEmpty()) {
			em.close();
			return null;
		}
		User item = (User) q.getSingleResult();

		em.getTransaction().commit();
		em.close();
		return item;
	}

	public static List<User> findUserBySubscription(Subscription subscription) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(builder.equal(root.get("subscription"), subscription));
		Query q=em.createQuery(query);
		if (q.getResultList().isEmpty()) {
			em.close();
			return null;
		}
		List<User> item = (ArrayList<User>)q.getResultList();

		em.getTransaction().commit();
		em.close();
		return item;
	}

	public static User findUserById(String id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(builder.equal(root.get("id"), id));
		Query q=em.createQuery(query);
		if (q.getSingleResult()==null) {
			em.close();
			return null;
		}
		User item = (User) q.getSingleResult();

		em.getTransaction().commit();
		em.close();
		return item;
	}

	public static List<User> getUsers() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		criteria.from(User.class);
		List<User> items = em.createQuery(criteria).getResultList();
		em.close();
		return items;
	}


}
