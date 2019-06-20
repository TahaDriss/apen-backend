package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import interfaces.UserServiceLocal;
import interfaces.UserServiceRemote;
import persistence.User;
import util.PasswordUtils;
import util.UpdatableBCrypt;

@Stateless
public class UserService implements UserServiceLocal, UserServiceRemote {

	@PersistenceContext(unitName = "Apen-Back-End-ejb")
	EntityManager em;

	private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(11);

	@Override
	public User getById(int id) {
		return em.find(User.class, id);
	}

	@Override
	public List<User> getAll() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}

	@Override
	public void remove(User u) {
		User user = em.find(User.class, u.getId());
		em.remove(user);
	}

	@Override
	public int update(String userName, String oldPassword, String newPassword) {

		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username=:username", User.class);
			query.setParameter("username", userName);
			User u = query.getSingleResult();

			boolean passwordMatch = PasswordUtils.verifyUserPassword(oldPassword, u.getPassword(), u.getSalt());

			if (passwordMatch) {
				// Generate Salt. The generated value can be stored in DB.
				String salt = PasswordUtils.getSalt(30);

				// Protect user's password. The generated value can be stored in DB.
				String mySecurePassword = PasswordUtils.generateSecurePassword(newPassword, salt);
				u.setPassword(mySecurePassword);
				u.setSalt(salt);
				User user = em.merge(u);
				return user.getId();
			} else {
				return 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int add(User u) {
		// Generate Salt. The generated value can be stored in DB.
		String salt = PasswordUtils.getSalt(30);

		// Protect user's password. The generated value can be stored in DB.
		String mySecurePassword = PasswordUtils.generateSecurePassword(u.getPassword(), salt);
		u.setPassword(mySecurePassword);
		u.setSalt(salt);
		em.persist(u);
		return u.getId();
	}

	@Override
	public User getForLogin(String username, String password) {
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username=:username", User.class);
			query.setParameter("username", username);
			User u = query.getSingleResult();
			boolean passwordMatch = PasswordUtils.verifyUserPassword(password, u.getPassword(), u.getSalt());
			if (passwordMatch)
				return u;
			return null;
		} catch (NoResultException e) {

			e.printStackTrace();
			return null;
		}
	}

}
