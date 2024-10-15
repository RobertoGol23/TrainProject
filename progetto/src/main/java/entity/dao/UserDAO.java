package entity.dao;



import entity.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;

import java.util.*;

import org.springframework.stereotype.Repository;

import eccezioni.eccezioniGeneriche.UtenteNonTrovatoException;

@Repository
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
    
    /**
     * Metodo che cerca e restituisce uno user sul db tramite la chiave primaria email
     * @param email
     * @return user, utente se è stato trovato sul db
     */
    @Transactional
    public User getUserByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> root = cq.from(User.class);

        cq.select(root).where(cb.equal(root.get("email"), email));
        
        User user = null;
    	try
        {
    		TypedQuery<User> typedQuery = em.createQuery(cq);
        	user = typedQuery.getSingleResult();
        }
        catch(UtenteNonTrovatoException e)
        {
        	System.out.println(e.message());
        }

        return user;
    }
    
    /**
     * Metodo che cerca sul db la coppia utente e password 
     * @param email
     * @param password
     * @return user, utente se è stato trovato sul db
     */
    public User findUserByEmailAndPassword(String email, String password) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        // Aggiungi le condizioni per email e password
        Predicate emailPredicate = criteriaBuilder.equal(userRoot.get("email"), email);
        Predicate passwordPredicate = criteriaBuilder.equal(userRoot.get("password"), password);
        criteriaQuery.select(userRoot).where(criteriaBuilder.and(emailPredicate, passwordPredicate));

        User user = null;
        
        try
        {
        	TypedQuery<User> typedQuery = em.createQuery(criteriaQuery);
        	user = typedQuery.getSingleResult();
        }
        catch(UtenteNonTrovatoException e)
        {
        	System.out.println(e.message());
        }
		
        return user;
    }
    
    @Transactional
    public List<User> getAllUsers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }

}
