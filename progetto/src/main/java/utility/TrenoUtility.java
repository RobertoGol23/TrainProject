package utility;

import java.util.ArrayList;
import java.util.List;

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
import entity.treno.Locomotiva;
import entity.treno.Treno;
import entity.treno.VagoneCargo;
import entity.treno.VagonePasseggeri;
import entity.treno.VagoneRistorante;
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
			fabbrica = new FabbricaRegionalGain();
			break;
			
		case 3:
			fabbrica = new FabbricaKargoModelz();
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
	
	public static Boolean controllaRistoranteCentrale(String sigla)
	{
		Boolean flag = false;
		
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
	 * Metodo usato per restituire il carattere in base al tipo.
	 * Il carattere è maiuscolo per via di una gestione dell'aggiunta dei vagoni
	 * @param tipo
	 * @return
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

	


}
