package entity.dao;

import jakarta.persistence.EntityManager;
import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.TrenoBuilder;
import entity.classi_astratte.Vagone;
import entity.servizi.Servizio;
import entity.treno.Locomotiva;
import entity.treno.Treno;
import fabbriche.FabbricaKargoModelz;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import utility.Assemblatore;
import utility.TrenoUtility;

import java.util.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import eccezioni.eccezioniGeneriche.MarcaNonValidaException;
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


    /**
     * Metodo che consente di rimuovere uno o più vagoni dato l'id di un treno, passandogli id da rimuovere
     * Dal sito web l'utente selezionerà dalle checkbox i vagoni da rimuovere. Per evitare errori si può evitare di mettere la checkbox nella locomotiv,
     * così non sarà possibile per l'utente rimuoverla e non sarà da gestire negli errori
     * @param id_treno, id del treno da cui togliere i vagoni
     * @param idVagoni, questa lista di id dei vagoni non rappresenta una raccolta di id dei vagoni presenti sul db. Quelli sono fissi.
     * Gli id che saranno passati qui, saranno quelli scelti dall'utente dalla lista vagoni, quindi a seconda della disposizione sul sito
     * @param siglaNuova
     * @return un boleano che indica la riuscita dell'operazione
     */
	@Transactional
	public boolean eliminaVagoni(Long id_treno, ArrayList<Integer> idVagoni) {

		TrenoUtility trenoUtility = new TrenoUtility();
		Treno treno = this.getTrenoById(id_treno);
		String siglaNuova = trenoUtility.riduciSigla(idVagoni, trenoUtility.getSigla(treno));
		
		try
		{
			if(trenoUtility.controllaSigla(siglaNuova))
			{
				for(int id:idVagoni)
				{
					treno.getVagone(id);
					treno.deleteVagone(id);
				}
				em.merge(treno);
			}
		}
		catch(SiglaTrenoException e)
		{
			System.out.println("Errore: " + e.getMessage());
		}
		
		return false; 
	}

	
	/**
     * Metodo che consente di aggiungere uno o più vagoni dato l'id di un treno, passandogli in input la nuova sigla e gli id
     * con la posizione dei vagoni da aggiungere
     * @param id_treno, id del treno a cui aggiungere i vagoni
     * @param idVagoni, questa lista di id dei vagoni indica la posizione dei vagoni nuovi che vuoi aggiungere
     * @param siglaNuova
     * @return un boleano che indica la riuscita dell'operazione
	 * @throws MarcaNonValidaException 
     */																			   //01 2  3 45
	@Transactional											   //   2,3				 HP(P)(P)PP
	public boolean aggiungiVagoni(Long id_treno, ArrayList<Integer> idVagoni, String siglaNuova) throws Exception
	{
		TrenoUtility trenoUtility = new TrenoUtility();
	
		try
		{
			if(trenoUtility.controllaSigla(siglaNuova))
			{
				Treno treno = this.getTrenoById(id_treno);
				int id_marca = trenoUtility.getIntByMarca(treno.getMarca());
				
				FabbricaVagoni fabbrica = trenoUtility.getFabbricaById(id_marca);
				TrenoBuilder builder = new Assemblatore(fabbrica);
				Vagone vagone;
				
				for(int id:idVagoni)
				{
					vagone = builder.getVagoneByType(siglaNuova.charAt(id));
					treno.addVagone(id, vagone);
				}
				
				ArrayList<Vagone> listaVagoni = treno.getArrayListaVagoni();
				
				System.out.println("Lista vagoni: "+listaVagoni);
				
				// TODO fare controllo del peso, bisognerebbe fare una query per prendere
				// la lista di vagoni e locomotiva e effettuare la somma di quei vagoni più quelli
				// aggiunti. Questo servirebbe come controllo per evitare di caricare il treno sul db
				// se poi non può essere mosso dalla locomotiva
				//trenoUtility.controllaPesoTrainabile(siglaNuova, listaVagoni);
			
				em.merge(treno);
			}
		}
		catch(Exception e)
		{
			System.out.println("Errore: " + e.getMessage());
		}
		
		return true; 
	}
	
	
	// VA MA NON SI FA COSÌ
	@Transactional
    public List<Treno> getTreniByPesoTrasportabile(double pesoMin) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Treno> cq = cb.createQuery(Treno.class);

        Root<Treno> root = cq.from(Treno.class);

		List<Treno> listaFinale = new ArrayList<Treno>();

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
	public boolean addServizio(Long id_treno, int id_vagone, String servizio){

		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Servizio> cq = cb.createQuery(Servizio.class);

        Root<Servizio> root_servizio = cq.from(Servizio.class);

		cq.select(root_servizio);
		List<Servizio> result = em.createQuery(cq).getResultList();
		
		Treno treno = getTrenoById(id_treno);
		
		treno.getListaVagoni().get(id_vagone).addServizio(result.get(0));
	
		em.merge(treno);
		
		
		return false;
	}

	

	

}
