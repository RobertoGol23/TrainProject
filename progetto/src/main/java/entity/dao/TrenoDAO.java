package entity.dao;

import jakarta.persistence.EntityManager;
import entity.classi_astratte.Vagone;
import entity.treno.Locomotiva;
import entity.treno.Treno;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.util.*;

import org.hibernate.criterion.Restrictions;

public class TrenoDAO {
	
	 	@PersistenceContext
	    private EntityManager em;

	    @Transactional
	    public void salvaTreno(Treno treno) {
	        em.persist(treno);
	    }

	    @Transactional
	    public void eliminaTrenoById(int id) {
	    	Treno treno = getTrenoById(id);
	        if (treno != null) {
	            em.remove(treno);
	        }
	    }

	    public Treno getTrenoById(int id) {
	        return em.find(Treno.class, id);
	    }

	    @Transactional
	    public void updateTreno(Treno treno) {
	        em.merge(treno);
	    }

		/* ------ CRITERIA ------- */


	// FUNZIONA MA NON STAMPA
	@Transactional
	public List<Treno> findTrenoByName(String nome) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Treno> cq = cb.createQuery(Treno.class);

		Root<Treno> root = cq.from(Treno.class);

		cq.select(root).where(cb.equal(root.get("nomeTreno"), nome));
		// è possibile anche fare select(root.get("nome attributo"))
		
		return em.createQuery(cq).getResultList();
	}

    @Transactional
    public List<Treno> findTrenoByMarca(String marca) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Treno> cq = cb.createQuery(Treno.class);

        Root<Treno> root = cq.from(Treno.class);

        cq.select(root).where(cb.equal(root.get("marca"), marca));
        // è possibile anche fare select(root.get("nome attributo"))

        return em.createQuery(cq).getResultList();
    } 





	/* @Transactional
	public List<Treno> findTreniByPesoTrasportabile(double pesoMin) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Treno> cq = cb.createQuery(Treno.class);
		
		// Creare la radice per Locomotiva
		Root<Locomotiva> locomotivaRoot = cq.from(Locomotiva.class);

		// Join con Treno
		Join<Locomotiva, Treno> trenoJoin = locomotivaRoot.join("id_vagone"); // Assicurati che "treno" sia il nome corretto della proprietà

		// Aggiungi la restrizione
		cq.select(trenoJoin).where(cb.gt(locomotivaRoot.get("pesoTrasportabile"), pesoMin));

		// Esegui la query
		return em.createQuery(cq).getResultList();
	} */



	// VA MA NON SI FA COSÌ
	@Transactional
    public List<Treno> findTreniByPesoTrasportabile(double pesoMin) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Treno> cq = cb.createQuery(Treno.class);

        Root<Treno> root = cq.from(Treno.class);

		List<Treno> listaFinale = new ArrayList();

		cq.select(root);
		List<Treno> results = em.createQuery(cq).getResultList();
		Locomotiva loco;
		for (Treno treno: results){
			loco = (Locomotiva)treno.getListaVagoni().get(0);
			if(loco.getPesoTrainabile() > pesoMin){
				listaFinale.add(treno);
			}
		} 

		return listaFinale;
    }


	

	

}
