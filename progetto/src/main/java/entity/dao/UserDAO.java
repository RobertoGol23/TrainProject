package entity.dao;

import entity.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class UserDAO {
	@PersistenceContext
    private EntityManager em;

    @Transactional
    public void salvaUser(User persona) {
        em.persist(persona);
    }

    @Transactional
    public void eliminaUserById(int id) {
    	User user = getUserById(id);
        if (user != null) {
            em.remove(user);
        }
    }

    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    @Transactional
    public void updateUser(User persona) {
        em.merge(persona);
    }
}
