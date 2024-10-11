package entity.classi_astratte;

import java.util.ArrayList;

import eccezioni.eccezioniSigla.*;
import entity.treno.Treno;
import entity.user.User;
import entity.treno.Locomotiva;
import utility.TrenoUtility;

public abstract class TrenoBuilder {
	
	public Treno costruisciTreno(String nomeTreno, String sigla, User utente, int numeroMarca) throws SiglaTrenoException{
		
		
		TrenoUtility trenoUtility = new TrenoUtility();
		
		
		ArrayList<Vagone> listaVagoni = new ArrayList<Vagone>();

		trenoUtility.controllaSigla(sigla);
		
		listaVagoni.add(getLocomotiva());
		
		for(int i = 1; i < sigla.length(); i++) {
			
			char c = sigla.charAt(i);
			switch(c) {
			
			case 'h':
				listaVagoni.add(getLocomotiva());
				break;
				
			case 'r':
				listaVagoni.add(getVagoneRistorante());
				break;
				
			case 'p':
				listaVagoni.add(getVagonePasseggeri());
				break;
				
			case 'c':
				listaVagoni.add(getVagoneCargo());
				break;
			}
		}

		trenoUtility.controllaPeso(sigla, listaVagoni);
		
		String marca = "";
		try
		{
			marca = trenoUtility.getMarcaByInt(1);
			return Treno.creaTreno(nomeTreno, listaVagoni, marca, utente);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return null;
			
	}
	
	protected abstract Locomotiva getLocomotiva();
	protected abstract Vagone getVagonePasseggeri();
	protected abstract Vagone getVagoneCargo();
	protected abstract Vagone getVagoneRistorante();

	
}
