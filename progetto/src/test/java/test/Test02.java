package test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import eccezioni.SiglaTrenoException;
import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.TrenoBuilder;
import entity.dao.ServizioDAO;
import entity.dao.TrenoDAO;
import entity.servizi.Servizio;
import entity.treno.Treno;
import fabbriche.FabbricaKargoModelz;
import fabbriche.FabbricaServizi;
import utility.Assemblatore;

public class Test02 {
    public static void main(String[] args) {
        
        FabbricaVagoni fabbricaKM= new FabbricaKargoModelz();
		TrenoBuilder builderKM = new Assemblatore(fabbricaKM);


		/*				TEST 02
		 * caricamento nel db di un treno con i rispettivi vagoni
		 * 
		 * 
		 * 
		 */




        String sigla = "hprp";
		try
		{
			AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
			


			Treno trenoKM = builderKM.costruisciTreno(sigla);
			TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
            trenoDAO.salvaTreno(trenoKM);


            // NON SI AGGIUNGONO COSÌ!
            FabbricaServizi fabbricaServizi = new FabbricaServizi();
            ServizioDAO servizioDAO = context.getBean(ServizioDAO.class);
            servizioDAO.salvaServizio(fabbricaServizi.creaTemperatura());

		          

			context.close();
			
		}
		catch (SiglaTrenoException e)
		{
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}
				
	}

}
