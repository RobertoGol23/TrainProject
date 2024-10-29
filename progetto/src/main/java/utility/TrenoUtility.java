package utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import eccezioni.eccezioniGeneriche.GenericException;
import eccezioni.eccezioniGeneriche.MarcaNonValidaException;
import eccezioni.eccezioniGeneriche.TroppoPesoException;
import eccezioni.eccezioniGeneriche.VagoneNonRiconosciutoException;
import eccezioni.eccezioniSigla.IncoerenzaVagoniException;
import eccezioni.eccezioniSigla.LocomotivaInMezzoException;
import eccezioni.eccezioniSigla.LocomotivaNonInTestaException;
import eccezioni.eccezioniSigla.RistoranteNonInMezzoException;
import eccezioni.eccezioniSigla.SiglaTrenoException;
import eccezioni.eccezioniSigla.StringaNonValidaException;
import eccezioni.eccezioniSigla.TroppiRistorantiException;
import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.Vagone;
import entity.dao.ServizioDAO;
import entity.dao.TrenoDAO;
import entity.dao.VagoneDAO;
import entity.servizi.Servizio;
import entity.treno.Locomotiva;
import entity.treno.Treno;
import entity.treno.VagoneCargo;
import entity.treno.VagonePasseggeri;
import entity.treno.VagoneRistorante;
import entity.user.User;
import fabbriche.FabbricaKargoModelz;
import fabbriche.FabbricaRegionalGain;
import fabbriche.FabbricaXFurryFast;

public class TrenoUtility {

	/**
	 * Restituisce la marca del treno in base al numero di marca
	 * @param numeroMarca
	 * @return marca
	 * @throws Exception
	 */
	public String getMarcaByInt(int numeroMarca) throws GenericException
	{
		String marca = "";
		
		switch(numeroMarca) { //switch per la marca dei treni
		case 1:
			marca = "Treno xFurryFast";
			break;
			
		case 2:
			marca = "Treno KargoModelz";
			break;
			
		case 3:
			marca = "Treno RegionalGain";
			break;
			
		default:
			throw new MarcaNonValidaException(marca, "Errore: marca non valida");
		}
		
		return marca;
	}
	
	
	/**
	 * Metodo che restituisce una fabbrica in base al valore passato
	 * @param id
	 * @return
	 */
	public FabbricaVagoni getFabbricaById(int id)
	{
		FabbricaVagoni fabbrica = null;
		
		switch(id) { //switch per la marca dei treni
		
		case 1:
			fabbrica = new FabbricaXFurryFast();
			break;
			
		case 2:
			fabbrica = new FabbricaKargoModelz();		
			break;
			
		case 3:
			fabbrica = new FabbricaRegionalGain();
			break;
		}
		
		return fabbrica;
	}
		
	/**
	 * Restituisce l'id della marca dato il nome
	 * @param marca
	 * @return
	 * @throws MarcaNonValidaException
	 */
	public int getIntByMarca(String marca) throws MarcaNonValidaException
	{	
		int id;
		switch(marca) { //switch per la marca dei treni
		
		case "Treno xFurryFast":
			id = 1;
			break;
			
		case "Treno KargoModelz":
			id = 2;
			break;
			
		case "Treno RegionalGain":
			id = 3;
			break;
			
		default:
			throw new MarcaNonValidaException(marca, "Errore: marca non valida");
		}
		
		return id;
	}

	// Controlla che la sigla del treno sia valida ritornando true, altrimenti lancia un'eccezione
	public boolean controllaSigla(String sigla) throws SiglaTrenoException
	{
		int contaR = 0;
		int contaP = 0;
		int contaC = 0;
		
		if(!sigla.startsWith("h")){
			throw new LocomotivaNonInTestaException(sigla, "Errore: locomotiva non in testa");
			//puo' lanciare una eccezione da gestire dove viene chiamato il metodo
		}
		
		for(int i = 1; i < sigla.length(); i++) {
			
			char c = sigla.charAt(i);
			switch(c) {
			
			case 'h':
				if(i != (sigla.length()-1))
				{
					throw new LocomotivaInMezzoException(sigla, "Errore: locomotiva in mezzo al treno", i);
				}
				break;
				
			case 'r':
				// se ci sono altri vagoni oltre al tipo Cargo, errore
				if(contaC != 0){
					throw new IncoerenzaVagoniException(sigla, "se c'è un vagone cargo non può esserci un vagone ristorante");
				}
				contaR++;
				// Controllo che non ci sia piu di un vagone
				if(contaR >= 2)
				{
					throw new TroppiRistorantiException(sigla,"Errore: troppi ristoranti",i);
				}
				else
				{
					if(!controllaRistoranteCentrale(sigla))
					{
						throw new RistoranteNonInMezzoException(sigla,"Errore: ristorante non in posizione centrale");
					}
				}
				break;
				
			case 'p':
				contaP++;
				// se ci sono altri vagoni oltre al tipo Cargo, errore
				if(contaC != 0){
					throw new IncoerenzaVagoniException(sigla, "se c'è un vagone cargo non può esserci un vagone passeggeri");
				}
				break;
				
			case 'c':
				contaC++;
				// se ci sono altri vagoni oltre al tipo Cargo, errore
				if(contaR != 0 || contaP != 0){
					throw new IncoerenzaVagoniException(sigla, "se c'è un vagone carg non possono esserci un vagone ristorante o passeggeri");
				} 
				break;
			default:
				throw new StringaNonValidaException(sigla, "Errore: carattere non valido");
			}
		}
	
		// Controllo che se c'è un vagone ristorante ci siano altri vagoni di tipo passeggeri
		if ((contaR == 1 && contaP == 0)) {
			throw new IncoerenzaVagoniException(sigla, "se c'è un vagone ristorante devono esserci altri vagoni di tipo passeggeri. Attualmente ci sono: " + contaP + " vagoni passeggeri e " + contaR + " vagoni ristorante");
		}

		return true;
	}
	
	public static boolean controllaRistoranteCentrale(String sigla)
	{
		boolean flag = false;
		
		if((sigla.length()%2)==0) //pari,  A A A R(3) R(4) A A A
		{
			int posizione1 = sigla.length()/2;
			int posizione2 = posizione1-1;
			if(sigla.charAt(posizione1) == 'r' || sigla.charAt(posizione1) == 'R'
				|| sigla.charAt(posizione2) == 'r' || sigla.charAt(posizione2) == 'R')
			{
				flag = true;
			}
		}
		else // A A A A R(4) A A A A
		{
			int posizione = (sigla.length()-1)/2;
			if(sigla.charAt(posizione) == 'r' || sigla.charAt(posizione) == 'R')
			{
				flag = true;
			}
		}
		
		return flag;
	}
	

	public boolean controllaPesoTrainabile(String sigla, List<Vagone> listaVagoni) throws SiglaTrenoException, GenericException
	{
		double sommapeso = 0.0;
		for(Vagone v: listaVagoni) {
			sommapeso = sommapeso + v.getPeso();
		}

		Locomotiva locomotiva = (Locomotiva)listaVagoni.get(0);
		System.out.println("\n\n PESO TOT: " + sommapeso + " PESO TRAINABILE: " + locomotiva.getPesoTrainabile() + "\n\n");
		if(sommapeso > locomotiva.getPesoTrainabile()) {
			throw new TroppoPesoException(sigla, "\n\nsono stati inseriti troppi vagoni, il peso trasportato supera quello trasportabile di: " + (sommapeso - locomotiva.getPesoTrainabile()) + " kg\n\n");
		}
		return true;
	}

	/**
	 * Metodo usato per restituire il carattere in base al tipo.
	 * Il carattere è maiuscolo per via di una gestione dell'aggiunta dei vagoni
	 * @param tipo
	 * @return
	 */
	public char getCharByTipo(String tipo){
		switch(tipo){
			case "Locomotiva": 
				return 'H';
			case "VagonePasseggeri":
				return 'P';
			case "VagoneRistorante":
				return 'R';
			case "VagoneCargo":
				return 'C';
			default:
				return ' ';
		}
	}
	
	
	/**
	 * Metodo usato per restituire la stringa con le info in base al tipo di vagone
	 * @param vagone
	 * @return dettagli vagoni
	 */
	public String getDettagli(Vagone vagone){
		
		String tipo = vagone.getTipo();
		
		switch(tipo){
			case "Locomotiva": 
				return "Peso trainabile:\n"+((Locomotiva)vagone).getPesoTrainabile().toString();
			case "VagonePasseggeri":
				return  "Posti a sedere:\n"+String.valueOf(((VagonePasseggeri)vagone).getPostiASedere());
			case "VagoneRistorante":
				return "Coperti:\n"+String.valueOf(((VagoneRistorante)vagone).getCoperti());
			case "VagoneCargo":
				return "Peso massimo trasportabile:\n"+String.valueOf(((VagoneCargo)vagone).getPesoMassimoTrasportabile());
			default:
				throw new VagoneNonRiconosciutoException("Nessun dettaglio, tipo del vagone non definito");
		}
	}
	
	
	
	//Metodo che serve per ricreare la sigla dato un treno
	private char findTipo(String tipo){
		switch(tipo){
			case "Locomotiva": 
				return 'h';
			case "VagonePasseggeri":
				return 'p';
			case "VagoneRistorante":
				return 'r';
			case "VagoneCargo":
				return 'c';
			default:
				return ' ';
		}
	}
	
	// Dato un treno, ritorna la sigla del treno
	public String getSigla(Treno treno){
        String sigla = "";
        for (int i=0; i<treno.getListaVagoni().size(); i++) {
			sigla = sigla + findTipo(treno.getVagone(i).getTipo());   
        }
        return sigla;
    }

	
	/**
	 * Metodo che prende in input la lista dei vagoni e rimuove dalla sigla la lettera nella stessa posizione del vagone
	 * @param listaId
	 * @param sigla
	 * @return siglaNuova, la nuova sigla con i vagoni rimossi
	 */
	public String riduciSigla(ArrayList<Integer> listaId, String sigla)
	{
		String siglaNuova = "";
		char[] listaChar = sigla.toCharArray();
		
		for(int id:listaId)
		{
			listaChar[id] = ' ';
		}
		
		for(char car:listaChar)
		{
			if(car != ' ')
			siglaNuova += car;
		}
		
		return siglaNuova;
	}
	
	/**
	 * Metodo che prende in input il treno e ne restituisce una copia con un nuovo id
	 * @param treno
	 * @param nome del treno nuovo
	 * @param utente
	 * @return treno clonato oppure null
	 */
	public Treno clonaTreno(Treno t, String nomeNuovo, User user) {
		
		TrenoUtility tu = new TrenoUtility();
		FabbricaVagoni fabbrica = tu.getFabbricaById(tu.getIntByMarca(t.getMarca()));
		Assemblatore assemblatore = new Assemblatore(fabbrica);
		
		Treno trenoClonato = assemblatore.costruisciTreno(nomeNuovo, tu.getSigla(t), user, tu.getIntByMarca(t.getMarca()));
		if (trenoClonato != null) {
			AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
            TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
            trenoDAO.salvaTreno(trenoClonato);
            VagoneDAO vagoneDAO = context.getBean(VagoneDAO.class);
            ServizioDAO servizioDAO = context.getBean(ServizioDAO.class);
            
            for(int i = 0; i < t.getListaVagoni().size(); i++) {
            	Vagone v = t.getVagone(i);
            	for(int j = 0; j < v.getListaServizi().size(); j++) {
            		Servizio s = v.getListaServizi().get(j);
            		trenoClonato.getVagone(i).addServizio(s);
            		vagoneDAO.updateVagone(trenoClonato.getVagone(i));
                    servizioDAO.updateServizio(s);
            	}
            }
            context.close();	
            return trenoClonato;
		}
		
		return null;
	}
	
	/**
	 * Metodo ribalta il treno se possibile
	 * @param treno
	 * 
	 * @return true se è riuscito, false se fallito
	 */

	public boolean ribaltaTreno(Treno t) {
		TrenoUtility tu = new TrenoUtility();
		List<Vagone> nuovaListaDiVagoni = new ArrayList<Vagone>();
		if(!tu.getSigla(t).endsWith("h")) {
			return false;
		}
		for(int j = t.getListaVagoni().size() - 1; j >= 0; j--) {
			nuovaListaDiVagoni.add(t.getListaVagoni().get(j));
		}
		t.setListaVagoni((ArrayList<Vagone>) nuovaListaDiVagoni);
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
	    TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
	    trenoDAO.updateTreno(t);
		context.close();
		return true;
	}

	/**
	 * Metodo che restituisce un tag img html per l'inserimento in una pagina
	 * @param vagone
	 * @param marca
	 * @return
	 */
	public String getHtmlImgByVagone(String tipoVagone, String marca)
	{
		if(marca.equals("Treno RegionalGain"))
		{ 
	        if(tipoVagone.equals("Locomotiva"))
	        { 
	        	return "<img src='/train-bazaar/treni/LocomotivaRG.jpg' class='wagon-image' alt='LocomotivaRG'>";
        	} else if(tipoVagone.equals("VagonePasseggeri")) { 
        		return "<img src='/train-bazaar/treni/VagonePasseggeriRG.jpg' class='wagon-image' alt='VagonePasseggeriRG'>";
        	} else if(tipoVagone.equals("VagoneRistorante")) { 
        		return "<img src='/train-bazaar/treni/VagoneRistoranteRG.jpg' class='wagon-image' alt='VagoneRistoranteRG'>";
        	} else if(tipoVagone.equals("VagoneCargo"))
        	{ 
        		return "<img src='/train-bazaar/treni/VagoneCargoRG.jpg' class='wagon-image' alt='VagoneCargoRG'>";
        	}
	    }
		else if(marca.equals("Treno xFurryFast"))
		{ 
	        if(tipoVagone.equals("Locomotiva"))
	        { 
	        	return "<img src='/train-bazaar/treni/locomotivaFF.jpg' class='wagon-image' alt='LocomotivaFF'>";
        	} else if(tipoVagone.equals("VagonePasseggeri")) { 
        		return "<img src='/train-bazaar/treni/VagonePasseggeriFF.jpg' class='wagon-image' alt='VagonePasseggeriFF'>";
        	} else if(tipoVagone.equals("VagoneRistorante")) { 
        		return "<img src='/train-bazaar/treni/VagoneRistoranteFF.jpg' class='wagon-image' alt='VagoneRistoranteFF'>";
        	} else if(tipoVagone.equals("VagoneCargo")) { 
        		return "<img src='/train-bazaar/treni/VagoneCargoFF.jpg' class='wagon-image' alt='VagoneCargoFF'>";
        	}
	    }
		else if(marca.equals("Treno KargoModelz"))
		{
	        if(tipoVagone.equals("Locomotiva"))
	        { 
	        	return "<img src='/train-bazaar/treni/locomotivaKM.jpg' class='wagon-image' alt='LocomotivaKM'>";
        	} else if(tipoVagone.equals("VagonePasseggeri")) { 
        		return "<img src='/train-bazaar/treni/VagonePasseggeriKM.jpg' class='wagon-image' alt='VagonePasseggeriKM'>";
        	} else if(tipoVagone.equals("VagoneRistorante")) { 
        		return "<img src='/train-bazaar/treni/VagoneRistoranteKM.jpg' class='wagon-image' alt='VagoneRistoranteKM'>";
        	} else if(tipoVagone.equals("VagoneCargo")) { 
        		return "<img src='/train-bazaar/treni/VagoneCargoKM.jpg' class='wagon-image' alt='VagoneCargoKM'>";
        	} 
	    }

		return "";
	}
	

	
	public String getImgPathByVagone(String tipoVagone, String marca)
	{
		if(marca.equals("Treno RegionalGain"))
		{ 
	        if(tipoVagone.equals("Locomotiva"))
	        { 
	        	return "/train-bazaar/treni/LocomotivaRG.jpg";
        	} else if(tipoVagone.equals("VagonePasseggeri")) { 
        		return "/train-bazaar/treni/VagonePasseggeriRG.jpg";
        	} else if(tipoVagone.equals("VagoneRistorante")) { 
        		return "/train-bazaar/treni/VagoneRistoranteRG.jpg";
        	} else if(tipoVagone.equals("VagoneCargo"))
        	{ 
        		return "/train-bazaar/treni/VagoneCargoRG.jpg";
        	}
	    }
		else if(marca.equals("Treno xFurryFast"))
		{ 
	        if(tipoVagone.equals("Locomotiva"))
	        { 
	        	return "/train-bazaar/treni/locomotivaFF.jpg";
        	} else if(tipoVagone.equals("VagonePasseggeri")) { 
        		return "/train-bazaar/treni/VagonePasseggeriFF.jpg";
        	} else if(tipoVagone.equals("VagoneRistorante")) { 
        		return "/train-bazaar/treni/VagoneRistoranteFF.jpg";
        	} else if(tipoVagone.equals("VagoneCargo")) { 
        		return "/train-bazaar/treni/VagoneCargoFF.jpg";
        	}
	    }
		else if(marca.equals("Treno KargoModelz"))
		{
	        if(tipoVagone.equals("Locomotiva"))
	        { 
	        	return "/train-bazaar/treni/locomotivaKM.jpg";
        	} else if(tipoVagone.equals("VagonePasseggeri")) { 
        		return "/train-bazaar/treni/VagonePasseggeriKM.jpg";
        	} else if(tipoVagone.equals("VagoneRistorante")) { 
        		return "/train-bazaar/treni/VagoneRistoranteKM.jpg";
        	} else if(tipoVagone.equals("VagoneCargo")) { 
        		return "/train-bazaar/treni/VagoneCargoKM.jpg";
        	} 
	    }

		return "";
	}
	
	
	
	
	/**
	 * Metodo che cerca un servizio in tutta la lista dei treni per vedere se è già presente
	 * Al primo elemento trovato esce dal ciclo
	 * @param listaTreni
	 * @param nomeServizio
	 * @return servizio se presente almeno uno, altrimenti null
	 */
	public Servizio cercaServizioInTreni(List<Treno> listaTreni, String nomeServizio)
	{
		Servizio servizio;
		ServiziUtility su = new ServiziUtility();
		
		for(Treno treno:listaTreni)
		{
			servizio = su.cercaServizioInTreno(treno, nomeServizio);
			if(servizio!=null)
			{	
				System.out.println("Servizio trovato! Servizio: "+ servizio +"\nEsco dal trenoUtility...");
				return servizio;
			}
		}
		System.out.println("Nessun servizio trovato\nEsco dal trenoUtility...");
		return null;
	}
	
	
}
