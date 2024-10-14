package entity.dao;



import entity.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.*;

public class UserDAO {
	@PersistenceContext
    private EntityManager em;

    @Transactional
    public void salvaUser(User persona) {
        em.persist(persona);
    }

    @Transactional
    public void eliminaUserById(Long id) {
    	User user = getUserById(id);
        if (user != null) {
            em.remove(user);
        }
    }

    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

    @Transactional
    public void updateUser(User persona) {
        em.merge(persona);
    }


    @Transactional
    public List<User> getUserByName(String nome) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> root = cq.from(User.class);

        cq.select(root).where(cb.equal(root.get("nome"), nome));
        // è possibile anche fare select(root.get("nome attributo"))

        List<User> results = em.createQuery(cq).getResultList();
        

        return results;
    }


}
