package entity.dao;

import java.util.List;

import entity.acquisto.Acquisto;
import entity.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

public class AcquistoDAO {
	
		@PersistenceContext
		private EntityManager em;

		@Transactional
		public void salvaAcquisto(Acquisto acquisto) {
			em.persist(acquisto);
		}

		@Transactional
		public void eliminaAcquistoById(int id) {
			Acquisto acquisto = getAcquistoById(id);
			if (acquisto != null) {
				em.remove(acquisto);
			}
		}

		public Acquisto getAcquistoById(int id) {
			return em.find(Acquisto.class, id);
		}

		@Transactional
		public void updateAcquisto(Acquisto acquisto) {
			em.merge(acquisto);
		}
		
		// Aggiungi questo metodo per ottenere gli acquisti di un utente
	    public List<Acquisto> getAcquistiByUserId(Long userId) {
	        return em.createQuery("SELECT a FROM Acquisto a WHERE a.user.id = :userId", Acquisto.class)
	                 .setParameter("userId", userId)
	                 .getResultList();
	    }
	    
	    //restituisce tutti gli acquisti
	    public List<Acquisto> getAllAcquisti(){
	    	CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Acquisto> cq = cb.createQuery(Acquisto.class);
	        Root<Acquisto> root = cq.from(Acquisto.class);
	        cq.select(root);
	        return em.createQuery(cq).getResultList();
	    }
}
