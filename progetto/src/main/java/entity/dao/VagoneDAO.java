package entity.dao;

import entity.classi_astratte.Vagone;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class VagoneDAO {

	@PersistenceContext
    private EntityManager em;

    @Transactional
    public void salvaVagone(Vagone vagone) {
        em.persist(vagone);
    }  

    public Vagone getVagoneById(Long id) {
        return em.find(Vagone.class, id);
    }

    @Transactional
    public void updateVagone(Vagone vagone) {
        em.merge(vagone);
    }
}
