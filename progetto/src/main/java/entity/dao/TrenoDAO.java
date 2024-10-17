package entity.dao;

import jakarta.persistence.EntityManager;
import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.TrenoBuilder;
import entity.classi_astratte.Vagone;
import entity.servizi.Servizio;
import entity.treno.Locomotiva;
import entity.treno.Treno;
import entity.votazioni.Voto;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import utility.Assemblatore;
import utility.TrenoUtility;

import java.util.*;

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
	 * Metodo che recupera l'attributo pesoTrainabile dalla locomotiva dato l'id del treno passato
	 * @param idTreno
	 * @return lista del pesoTrainabile delle locomotive del treno
	 */
	@SuppressWarnings("unchecked")
	public List<Double> getPesoTrainabileByTrenoId(Long idTreno) {
	    String sql = "SELECT l.peso_trainabile " +
	                 "FROM Locomotiva l " +
	                 "JOIN treno_vagoni tv ON l.id_vagone = tv.id_vagone " +
	                 "WHERE tv.id_treno = :trenoId";

	    Query query = em.createNativeQuery(sql);
	    query.setParameter("trenoId", idTreno);


	    return query.getResultList();
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
	public List<Treno> getTrenoByUserId(Long user_id){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Treno> cq = cb.createQuery(Treno.class);

		Root<Treno> root = cq.from(Treno.class);

		cq.select(root).where(cb.equal(root.get("user").get("id"), user_id));

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
	public boolean eliminaVagoni(Long id_treno, ArrayList<Integer> posVagoni) {

		TrenoUtility trenoUtility = new TrenoUtility();
		Treno treno = this.getTrenoById(id_treno);
		String siglaNuova = trenoUtility.riduciSigla(posVagoni, trenoUtility.getSigla(treno));

		try
		{
			if(trenoUtility.controllaSigla(siglaNuova)){
				for(int i=posVagoni.size()-1; i >= 0; i--){
					int pos = posVagoni.get(i);
					treno.deleteVagone(pos);
				}

				em.merge(treno);
				em.flush();
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
     */																			   
	@Transactional
	public boolean aggiungiVagoni(Long id_treno, ArrayList<Integer> posVagoni, String siglaNuova) throws Exception, SiglaTrenoException {
		TrenoUtility trenoUtility = new TrenoUtility();
		Treno treno = this.getTrenoById(id_treno);

		// Controlla la lunghezza della sigla
		if (siglaNuova.length() < trenoUtility.getSigla(treno).length()) {
			return false; // Questo non causa un rollback automatico
		}

		if (trenoUtility.controllaSigla(siglaNuova)) {
			int id_marca = trenoUtility.getIntByMarca(treno.getMarca());
			FabbricaVagoni fabbrica = trenoUtility.getFabbricaById(id_marca);
			TrenoBuilder builder = new Assemblatore(fabbrica);
			Vagone vagone;

			try {
				for (int pos : posVagoni) {
					if (pos > siglaNuova.length()) {
						return false; // Questo non causa un rollback
					}

					vagone = builder.getVagoneByType(siglaNuova.charAt(pos));
					treno.addVagone(pos, vagone);
				}

				// Controlla il peso trainabile
				if (!trenoUtility.controllaPesoTrainabile(siglaNuova, treno.getListaVagoni())) {
					return false; // Questo non causa un rollback
				}

				em.merge(treno);
				em.flush();
				return true;

			} catch (Exception e) {
				System.out.println("Errore durante l'aggiunta dei vagoni: " + e.getMessage());
				throw e; // Lancia nuovamente l'eccezione per attivare il rollback
			}
		}

		return false; 
	}
	
	//TODO: controllare se serve ALLA FINE
	/**
	 * Metodo che esegue una query SQL nativa per ottenere la somma dei pesi dei vagoni di un treno
	 * @param trenoId L'id del treno
	 * @return sommaPeso La somma dei pesi dei vagoni associati al treno
	 */
	public Double getSommaPesoVagoni(Long trenoId) {
	    String sql = "SELECT "
	    		 	+ "SUM(v.peso) "
	    		 	+ "FROM "
	    		 	+ " Vagone v "
	    		 	+ "  JOIN treno_vagoni tv ON v.id_vagone = tv.id_vagone "
	    		 	+ "WHERE "
	    		 	+ "   tv.id_treno = :trenoId ";
	    
	    Query query = em.createNativeQuery(sql);
	    query.setParameter("trenoId", trenoId);
	
	    return (Double) query.getSingleResult();
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

	@Transactional
	public double getVotazioneMedia(Treno treno){
		double result = 0.0;

		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Voto> cq = cb.createQuery(Voto.class);

        Root<Voto> root_voto = cq.from(Voto.class);

		cq.select(root_voto).where(cb.equal(root_voto.get("treno"), treno));


		// cq.select(VOTO).where(cb.equal(cq.select(root_treno).where), treno_id);

		List<Voto> listaVoti = em.createQuery(cq).getResultList();

		for(Voto voto : listaVoti){
			result += voto.getPunteggio();
		}

		result = result/listaVoti.size();

		return result;
	}

	// Restituisci la lista di tutti i treni
	public List<Treno> getAllTrain() { 
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Treno> cq = cb.createQuery(Treno.class);
        
        Root<Treno> root_treno = cq.from(Treno.class);
        
        cq.select(root_treno);
        
        List<Treno> treni = em.createQuery(cq).getResultList();
        
        return treni;
    }
	
	public List<Treno> getTreniPaginated(int offset, int limit) {
        String jpql = "SELECT t FROM Treno t";
        TypedQuery<Treno> query = em.createQuery(jpql, Treno.class);
        query.setFirstResult(offset); // Imposta l'offset
        query.setMaxResults(limit); // Imposta il limite
        return query.getResultList(); // Restituisce la lista dei treni
    }

}
