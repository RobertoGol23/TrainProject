package entity.classi_astratte;

import java.util.ArrayList;

import eccezioni.eccezioniSigla.*;
import entity.treno.Treno;
import entity.treno.Locomotiva;

public abstract class TrenoBuilder {
	
	public Treno costruisciTreno(String sigla) throws SiglaTrenoException{
		
		//System.out.println("Sigla interna: " + sigla.toString());
		
		ArrayList<Vagone> listaVagoni = new ArrayList<Vagone>();
		
		int contaR = 0;
		int contaP = 0;
		int contaC = 0;
		double sommapeso = 0.0;
		
		
		if(!sigla.startsWith("h")) {
			throw new LocomotivaNonInTestaException(sigla, "Errore: locomotiva non in testa");
			//puo' lanciare una eccezione da gestire dove viene chiamato il metodo
		}
		
		listaVagoni.add(getLocomotiva());
		
		for(int i = 1; i < sigla.length(); i++) {
			
			char c = sigla.charAt(i);
			switch(c) {
			
			case 'h':
				if(i != (sigla.length()-1))
				{
					throw new LocomotivaInMezzoException(sigla, "Errore: locomotiva in mezzo al treno", i);
				}
				else
				{
					listaVagoni.add(getLocomotiva());
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
					else
					{
						listaVagoni.add(getVagoneRistorante());
					}
				}
				break;
				
			case 'p':
				contaP++;
				// se ci sono altri vagoni oltre al tipo Cargo, errore
				if(contaC != 0){
					throw new IncoerenzaVagoniException(sigla, "se c'è un vagone cargo non può esserci un vagone passeggeri");
				}
				
				listaVagoni.add(getVagonePasseggeri());
				break;
				
			case 'c':
				contaC++;
				// se ci sono altri vagoni oltre al tipo Cargo, errore
				if(contaR != 0 || contaP != 0){
					throw new IncoerenzaVagoniException(sigla, "se c'è un vagone carg non possono esserci un vagone ristorante o passeggeri");
				} 
				listaVagoni.add(getVagoneCargo());
				break;
			default:
				throw new StringaNonValidaException(sigla, "Errore: carattere non valido");
			}
		}
		
		// Controllo che se c'è un vagone ristorante ci siano altri vagoni di tipo passeggeri
				if ( contaR == 1 && contaP == 0) {
					throw new IncoerenzaVagoniException(sigla, "se c'è un vagone ristorante devono esserci altri vagoni di tipo passeggeri. Attualmente ci sono: " + contaP + " vagoni passeggeri e " + contaR + " vagoni ristorante");
				}

		for(Vagone v: listaVagoni) {
			sommapeso = sommapeso + v.getPeso();
		}

		Locomotiva locomotiva = (Locomotiva)listaVagoni.get(0);
		if(sommapeso > locomotiva.getPesoTrainabile()) {
			throw new TroppoPesoException(sigla, "sono stati inseriti troppi vagoni, il peso trasportabile e' minore");
		}

		Treno t= Treno.creaTreno(listaVagoni, "null"); //null = marca
		
		return t;
	}
	
	protected abstract Locomotiva getLocomotiva();
	protected abstract Vagone getVagonePasseggeri();
	protected abstract Vagone getVagoneCargo();
	protected abstract Vagone getVagoneRistorante();








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
	
}
