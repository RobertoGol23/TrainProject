package test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import eccezioni.SiglaTrenoException;
import entity.classi_astratte.fabbrica_and_builder.FabbricaVagoni;
import entity.classi_astratte.fabbrica_and_builder.TrenoBuilder;
import entity.dao.TrenoDAO;
import entity.treno.Treno;
import fabbriche.FabbricaKargoModelz;
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
