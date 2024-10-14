package entity.dao;

import jakarta.persistence.EntityManager;
import entity.servizi.Servizio;
import entity.treno.Locomotiva;
import entity.treno.Treno;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import utility.TrenoUtility;

import java.util.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import eccezioni.eccezioniSigla.SiglaTrenoException;


public class TrenoDAO {
	
	 	@PersistenceContext
	    private EntityManager em;

	    @Transactional
	    public void salvaTreno(Treno treno) {
	        em.persist(treno);
	    }

	    @Transactional
	    public void eliminaTrenoById(Long id) {
	    	Treno treno = getTrenoById(id);
	        if (treno != null) {
	            em.remove(treno);
	        }
	    }

	    public Treno getTrenoById(Long id) {
	        return em.find(Treno.class, id);
	    }

	    @Transactional
	    public void updateTreno(Treno treno) {
	        em.merge(treno);
	    }


		/* ------ CRITERIA ------- */


	// FUNZIONA MA NON STAMPA BENE
	@Transactional
	public List<Treno> getTrenoByName(String nome) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Treno> cq = cb.createQuery(Treno.class);

		Root<Treno> root = cq.from(Treno.class);

		cq.select(root).where(cb.equal(root.get("nomeTreno"), nome));
		// è possibile anche fare select(root.get("nome attributo"))
		
		return em.createQuery(cq).getResultList();
	}

    @Transactional
    public List<Treno> getTrenoByMarca(int marca) {
		String marcaString = "" + marca;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Treno> cq = cb.createQuery(Treno.class);

        Root<Treno> root = cq.from(Treno.class);
	
        cq.select(root).where(cb.equal(root.get("marca"), marcaString));
        // è possibile anche fare select(root.get("nome attributo"))

        return em.createQuery(cq).getResultList();
    } 


	@Transactional
	public boolean eliminaVagoneByIndex(Treno treno, int index) {

		TrenoUtility trenoUtility = new TrenoUtility();

		try{
			boolean bool = trenoUtility.controllaSigla(trenoUtility.getSiglaRimozione(treno, index));
			if(bool){
				treno.deleteVagone(index);
				
				em.merge(treno);
			}
		return bool;
		}
		catch(SiglaTrenoException e)
		{
			System.out.println("Errore: " + e.getMessage());
		}

		return false; 
	}

	// VA MA NON SI FA COSÌ
	@Transactional
    public List<Treno> getTreniByPesoTrasportabile(double pesoMin) {
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

	@Transactional
	public boolean addServizio(Treno treno, int index, String servizio){

		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Servizio> cq = cb.createQuery(Servizio.class);

        Root<Servizio> root_servizio = cq.from(Servizio.class);

		cq.select(root_servizio);
		List<Servizio> result = em.createQuery(cq).getResultList();
		
		treno.getListaVagoni().get(index).addServizio(result.get(0));
	
		
		em.merge(treno);
		
		
		return false;
	}

	

	

}
