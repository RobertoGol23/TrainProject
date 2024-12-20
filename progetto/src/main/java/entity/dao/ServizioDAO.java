package entity.dao;

import jakarta.persistence.EntityManager;

import java.util.List;

import eccezioni.eccezioniGeneriche.ServizioGiaPresenteException;
import entity.servizi.Servizio;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

public class ServizioDAO{
	
	 	@PersistenceContext
	    private EntityManager em;

	 	public ServizioDAO(EntityManager em){
	 		
	 		this.em = em;
	 	}
	 	
	 	public ServizioDAO()
	 	{}
	 	
	    @Transactional
	    public void salvaServizio(Servizio servizio) throws ServizioGiaPresenteException {
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

	    public Servizio getServizioById(Long idServizio) {
	        return em.find(Servizio.class, idServizio);
	    }
	    
	    @Transactional
	    public void updateServizio(Servizio servizio) {
	    	em.merge(servizio);
	    }
	    
	    @Transactional
	    public List<Servizio> trovaServiziDisponibili() {
	        String queryStr = "SELECT s FROM Servizio s"; // Assuming Servizio is an entity class
	        TypedQuery<Servizio> query = em.createQuery(queryStr, Servizio.class);
	        return query.getResultList();
	    }
}