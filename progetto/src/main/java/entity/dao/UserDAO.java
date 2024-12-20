package entity.dao;



import entity.treno.Treno;
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
    
    @Transactional
    public void deleteUser(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
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

        List<User> result = em.createQuery(cq).getResultList();

        if (result.isEmpty()) {
            return null; // Nessun utente trovato
        } else {
            return result.get(0); // Restituisci l'utente trovato
        }
    }
    
    
    /**
     * Metodo che verifica se la password è corretta per un dato indirizzo email
     * @param email l'email dell'utente
     * @param password, la password da verificare
     * @return true, se la password è corretta, false altrimenti
     */
    public boolean checkPasswordByUser(String email, String password) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        // Crea il predicate per filtrare l'utente tramite l'email
        Predicate emailPredicate = criteriaBuilder.equal(userRoot.get("email"), email);
        criteriaQuery.select(userRoot).where(emailPredicate);

        // Esegui la query e metti il risultato in una lista
        TypedQuery<User> typedQuery = em.createQuery(criteriaQuery);
        List<User> result = typedQuery.getResultList(); // Utilizziamo getResultList per evitare eccezioni

        // Controlla se la lista è vuota (nessun utente trovato)
        if (result.isEmpty()) {
            return false; // Nessun utente trovato
        } else {
            User user = result.get(0); // Prendi il primo utente trovato (email univoca)
            // Verifica se la password è corretta
            return user.getPassword().equals(password);
        }
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
    
    @Transactional
	public List<Treno> getTrenoByUserId(Long user_id){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Treno> cq = cb.createQuery(Treno.class);

		Root<Treno> root = cq.from(Treno.class);

		cq.select(root).where(cb.equal(root.get("user").get("id"), user_id));

		return em.createQuery(cq).getResultList();
	}

    public List<User> getUsersByPage(int page, int pageSize) {
        // Implementazione per ottenere un subset di utenti per pagina
        int offset = (page - 1) * pageSize;
     // Query per ottenere gli utenti con offset e limit
        return em.createQuery("SELECT u FROM User u", User.class)
                .setFirstResult(offset)  // Salta i primi "offset" risultati
                .setMaxResults(pageSize) // Limita il numero di risultati a "pageSize"
                .getResultList();
    }

    public int getTotalUsers() {
    	Long totalUsers = em.createQuery("SELECT COUNT(u) FROM User u", Long.class)
                .getSingleResult();
        return totalUsers.intValue();
    }
}
