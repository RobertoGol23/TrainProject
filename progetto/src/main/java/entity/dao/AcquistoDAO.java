package entity.dao;

import java.util.List;

import entity.acquisto.Acquisto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
}
