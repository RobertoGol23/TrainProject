package entity.dao;

import java.util.List;

import entity.acquisto.Acquisto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
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
	    
	    public boolean existsAcquistoByUserIdAndTrenoId(Long userId, Long trenoId) {
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
	        Root<Acquisto> root = cq.from(Acquisto.class);

	        // Crea le condizioni per userId e trenoId
	        Predicate userPredicate = cb.equal(root.get("user").get("id"), userId);
	        Predicate trenoPredicate = cb.equal(root.get("treno").get("id"), trenoId);

	        // Costruisci la query con il conteggio
	        cq.select(cb.count(root)).where(cb.and(userPredicate, trenoPredicate));

	        // Esegui la query e controlla se il conteggio è maggiore di 0
	        Long count = em.createQuery(cq).getSingleResult();
	        
	        return count > 0;
	    }

		public List<Acquisto> getAcquistoByPage(int page, int pageSize) {
			// Implementazione per ottenere un subset di acquisti per pagina
			int offset = (page - 1) * pageSize;
			// Query per ottenere gli acquisti con offset e limit
				return em.createQuery("SELECT u FROM Acquisto u", Acquisto.class)
						.setFirstResult(offset)  // Salta i primi "offset" risultati
						.setMaxResults(pageSize) // Limita il numero di risultati a "pageSize"
						.getResultList();
		}

		public int getTotalAcquisti() {
			Long totalAcquisti = em.createQuery("SELECT COUNT(u) FROM Acquisto u", Long.class)
					.getSingleResult();
			return totalAcquisti.intValue();
		}
}
