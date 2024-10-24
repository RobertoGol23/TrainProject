package entity.dao;

import entity.votazioni.Voto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
	    
	 // Metodo per calcolare la media dei punteggi di un treno dato il suo ID
	    public Double getVotazioneMedia(Long trenoId) {
	        CriteriaBuilder cb = em.getCriteriaBuilder();

	        // Creazione della query per la media
	        CriteriaQuery<Double> cq = cb.createQuery(Double.class);

	        // Selezioniamo dalla classe Voto
	        Root<Voto> voto = cq.from(Voto.class);

	        // Costruiamo la query per ottenere la media
	        cq.select(cb.avg(voto.get("punteggio")))
	          .where(cb.equal(voto.get("treno").get("id"), trenoId));

	        // Eseguiamo la query e ritorniamo il risultato
	        Double mediaPunteggio = em.createQuery(cq).getSingleResult();

	        // Controlliamo se la media è nulla (nessun voto presente)
	        return mediaPunteggio != null ? mediaPunteggio : 0.0;
	    }
	    
	    public Voto trovaVotoPerUtenteETreno(Long userId, Long trenoId) {
	        try {
	            String query = "SELECT v FROM Voto v WHERE v.user.id_user = :userId AND v.treno.id = :trenoId";
	            return em.createQuery(query, Voto.class)
	                                .setParameter("userId", userId)
	                                .setParameter("trenoId", trenoId)
	                                .getSingleResult();
	        } catch (NoResultException e) {
	            return null; // Se non trova risultati, ritorna null
	        }
	    }
}
