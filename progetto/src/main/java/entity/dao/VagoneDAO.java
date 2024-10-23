package entity.dao;

import eccezioni.eccezioniGeneriche.AssociazioneServizioVagoneNonTrovataException;
import entity.classi_astratte.Vagone;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

public class VagoneDAO {

	@PersistenceContext
    private EntityManager em;

	
	
    @Transactional
    public void salvaVagone(Vagone vagone) {
        em.persist(vagone);
    }  

    @Transactional
    public Vagone getVagoneById(Long id) {
    	//em.flush();
        return em.find(Vagone.class, id);
    }

    @Transactional
    public void updateVagone(Vagone vagone) {
        em.merge(vagone);
    	//em.persist(vagone);
    	
    }

    /**
     * Rimuove un servizio associato a un vagone dalla tabella vagone_servizio.
     *
     * @param idVagone, l'id del vagone.
     * @param nomeServizio Il nome del servizio da rimuovere.
     */
    @Transactional
    public boolean removeServizioFromVagone(Long idVagone, String nomeServizio) {
        // Crea la query per rimuovere l'associazione dalla tabella 'servizi_utilizzati'
    	Query query = em.createNativeQuery(
    		    "DELETE FROM servizi_utilizzati WHERE nome = :nome AND id_vagone = :id_vagone"
    		);
    		query.setParameter("nome", nomeServizio);
    		query.setParameter("id_vagone", idVagone);

        // Esegui la query
        int result = query.executeUpdate();

        // Verifica il risultato
        if (result > 0) {
            System.out.println("Servizio rimosso con successo.");
            return true;
        } else {
            System.out.println("Il servizio non è stato trovato per questo vagone.");
            throw new AssociazioneServizioVagoneNonTrovataException("Coppia vagone e servizio non trovata nella tabella 'servizi_utilizzati'");
        }
    }
    
}
