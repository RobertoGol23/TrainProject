package entity.dao;

import jakarta.persistence.EntityManager;
import entity.servizi.Servizio;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class ServizioDAO{
	
	 	@PersistenceContext
	    private EntityManager em;

	    @Transactional
	    public void salvaServizio(Servizio servizio) {
	        em.persist(servizio);
	    }

	    @Transactional
	    public void eliminaServizioByName(String nome) {
	    	Servizio servizio = getServizioByName(nome);
	        if (servizio != null) {
	            em.remove(servizio);
	        }
	    }

	    public Servizio getServizioByName(String nome) {
	        return em.find(Servizio.class, nome);
	    }

	    @Transactional
	    public void updateServizio(Servizio servizio) {
	        em.merge(servizio);
	    }
}