package utility;

import java.util.ArrayList;

import eccezioni.eccezioniGeneriche.MarcaNonValidaException;
import eccezioni.eccezioniSigla.IncoerenzaVagoniException;
import eccezioni.eccezioniSigla.LocomotivaInMezzoException;
import eccezioni.eccezioniSigla.LocomotivaNonInTestaException;
import eccezioni.eccezioniSigla.RistoranteNonInMezzoException;
import eccezioni.eccezioniSigla.SiglaTrenoException;
import eccezioni.eccezioniSigla.StringaNonValidaException;
import eccezioni.eccezioniSigla.TroppiRistorantiException;
import eccezioni.eccezioniSigla.TroppoPesoException;
import entity.classi_astratte.Vagone;
import entity.treno.Locomotiva;
import entity.treno.Treno;

public class TrenoUtility {

	public TrenoUtility() {
		
	}

	public String getMarcaByInt(int numeroMarca) throws Exception
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
	
	public void controllaSigla(String sigla) throws SiglaTrenoException
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

		//return true;
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
	
	/**
	 * Metodo che controlla la 
	 * @param sigla
	 * @param listaVagoni
	 * @throws SiglaTrenoException
	 */
	public void controllaPeso(String sigla,ArrayList<Vagone> listaVagoni) throws SiglaTrenoException
	{
		double sommapeso = 0.0;
		for(Vagone v: listaVagoni) {
			sommapeso = sommapeso + v.getPeso();
		}

		Locomotiva locomotiva = (Locomotiva)listaVagoni.get(0);
		if(sommapeso > locomotiva.getPesoTrainabile()) {
			throw new TroppoPesoException(sigla, "sono stati inseriti troppi vagoni, il peso trasportabile e' minore");
		}
		
	}
}
