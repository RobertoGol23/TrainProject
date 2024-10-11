package entity.dao;

import entity.votazioni.Voto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class VotoDAO {
	
		@PersistenceContext
	    private EntityManager em;

	    @Transactional
	    public void salvaVoto(Voto voto) {
	        em.persist(voto);
	    }

	    @Transactional
	    public void eliminaVotoById(int id) {
	    	Voto voto = getVotoById(id);
	        if (voto != null) {
	            em.remove(voto);
	        }
	    }

	    public Voto getVotoById(int id) {
	        return em.find(Voto.class, id);
	    }

	    @Transactional
	    public void updateVoto(Voto voto) {
	        em.merge(voto);
	    }
}
