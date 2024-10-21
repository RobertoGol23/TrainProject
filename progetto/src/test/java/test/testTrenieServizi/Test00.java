package test.testTrenieServizi;

import eccezioni.eccezioniGeneriche.GenericException;
import eccezioni.eccezioniSigla.SiglaTrenoException;
import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.TrenoBuilder;
import entity.treno.Treno;
import entity.user.User;
import fabbriche.FabbricaXFurryFast;
import utility.Assemblatore;

public class Test00 {

	public static void main(String[] args) throws GenericException {
		
		String sigla = "hccc";
		
		try
		{
			FabbricaVagoni fabbricaFF= new FabbricaXFurryFast();
			TrenoBuilder builderFF = new Assemblatore(fabbricaFF);
			
			User pippo = new User("Pippo","Franco", "pippo.franco@gmail.com", "1234", 0.0);
			
			Treno t = builderFF.costruisciTreno("Treno della speranza", sigla, pippo, 1);
			System.out.println("Treno\n" + t.toString());
			
		}
		catch (SiglaTrenoException e)
		{
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}
				
	}

}
