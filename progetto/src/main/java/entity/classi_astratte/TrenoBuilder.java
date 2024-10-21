package entity.classi_astratte;

import java.util.ArrayList;

import eccezioni.eccezioniGeneriche.GenericException;
import eccezioni.eccezioniSigla.*;
import entity.treno.Treno;
import entity.user.User;
import entity.treno.Locomotiva;
import utility.TrenoUtility;

public abstract class TrenoBuilder {
	
	public Treno costruisciTreno(String nomeTreno, String sigla, User utente, int numeroMarca) throws GenericException, SiglaTrenoException{
		
		
		TrenoUtility trenoUtility = new TrenoUtility();
		
		
		ArrayList<Vagone> listaVagoni = new ArrayList<Vagone>();

		trenoUtility.controllaSigla(sigla);
		
		listaVagoni.add(getLocomotiva());
		
		for(int i = 1; i < sigla.length(); i++) {
			
			char c = sigla.charAt(i);
			listaVagoni.add(getVagoneByType(c));
			
		}

		trenoUtility.controllaPesoTrainabile(sigla, listaVagoni);
		
		String marca = "";
		try
		{
			marca = trenoUtility.getMarcaByInt(numeroMarca);
			return Treno.creaTreno(nomeTreno, listaVagoni, marca, utente);
		}
		catch (GenericException e)
		{
			System.out.println(e);
		}
		
		return null;
			
	}
	
	/**
	 * Metodo che prende in input un carattere e restituisce il vagone generato in base al tipo
	 * Metodo utilizzato nella crazione del treno e nella creazione dei vagoni singoli
	 * @param c
	 * @return vagone
	 */
	public Vagone getVagoneByType(char c)
	{
		Vagone vagone = null;
		
		switch(c) {
		
		case 'h':
			vagone = getLocomotiva();
			break;
			
		case 'r':
			vagone = getVagoneRistorante();
			break;
			
		case 'p':
			vagone = getVagonePasseggeri();
			break;
			
		case 'c':
			vagone = (getVagoneCargo());
			break;
		}
		return vagone;
	}
	
	protected abstract Locomotiva getLocomotiva();
	protected abstract Vagone getVagonePasseggeri();
	protected abstract Vagone getVagoneCargo();
	protected abstract Vagone getVagoneRistorante();

	
}
