package entity.dao;

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
}
