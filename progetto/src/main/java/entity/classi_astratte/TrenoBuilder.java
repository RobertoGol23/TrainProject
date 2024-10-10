package entity.classi_astratte;

import java.util.ArrayList;

import eccezioni.ControlloEccezioni;
import eccezioni.IncoerenzaVagoniException;
import eccezioni.LocomotivaInMezzoException;
import eccezioni.LocomotivaNonInTestaException;
import eccezioni.RistoranteNonInMezzoException;
import eccezioni.SiglaTrenoException;
import eccezioni.TroppiRistorantiException;
import eccezioni.TroppoPesoException;
import eccezioni.VagoneNonValidoException;
import entity.classi_astratte.Vagone;
import entity.treno.Locomotiva;
import entity.treno.Treno;

public abstract class TrenoBuilder {
	
	public Treno costruisciTreno(String nomeTreno, String sigla) throws SiglaTrenoException{
		
		//System.out.println("Sigla interna: " + sigla.toString());
		
		ArrayList<Vagone> listaVagoni = new ArrayList<Vagone>();
		Locomotiva locomotiva; 
		
		int contaR = 0;
		int contaP = 0;
		int contaC = 0;
		double sommapeso = 0.0;
		
		
		if(!sigla.startsWith("h")) {
			throw new LocomotivaNonInTestaException(sigla, "Errore: locomotiva non in testa");
			//puo' lanciare una eccezione da gestire dove viene chiamato il metodo
		}
		else
		{
			locomotiva = this.getLocomotiva();
			sommapeso = sommapeso + locomotiva.getPeso();
			//System.out.println("Locomotiva: " + locomotiva.toString());
		}
		
		
		
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
					if(!ControlloEccezioni.controllaRistoranteCentrale(sigla))
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
				throw new VagoneNonValidoException(sigla,"Errore: vagone non valido",i);
			}
		}
		
		// Controllo che se c'è un vagone ristorante ci siano altri vagoni di tipo passeggeri
				if ( contaR == 1 && contaP == 0) {
					throw new IncoerenzaVagoniException(sigla, "se c'è un vagone ristorante devono esserci altri vagoni di tipo passeggeri. Attualmente ci sono: " + contaP + " vagoni passeggeri e " + contaR + " vagoni ristorante");
				}

		for(Vagone v: listaVagoni) {
			sommapeso = sommapeso + v.getPeso();
		}
		if(sommapeso > locomotiva.getPesoTrainabile()) {
			throw new TroppoPesoException(sigla, "sono stati inseriti troppi vagoni, il peso trasportabile e' minore");
		}

		Treno t= Treno.creaTreno(nomeTreno,locomotiva, listaVagoni, "Treno KargoModelz");
		
		return t;
	}
	
	protected abstract Locomotiva getLocomotiva();
	protected abstract Vagone getVagonePasseggeri();
	protected abstract Vagone getVagoneCargo();
	protected abstract Vagone getVagoneRistorante();
	
}
