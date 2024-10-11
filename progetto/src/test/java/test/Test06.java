package test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import eccezioni.eccezioniSigla.SiglaTrenoException;
import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.TrenoBuilder;
import entity.dao.TrenoDAO;
import entity.dao.UserDAO;
import entity.treno.Treno;
import entity.user.User;
import fabbriche.FabbricaKargoModelz;
import fabbriche.FabbricaRegionalGain;
import utility.Assemblatore;

public class Test06 {
    public static void main(String[] args) {
        
        /*				TEST 06
         *  Test creazione piu' treni
         *
         *
         *
         *
         */
    	 
        FabbricaVagoni fabbricaKM= new FabbricaKargoModelz();
		TrenoBuilder builderKM = new Assemblatore(fabbricaKM);
		
		FabbricaVagoni fabbricaRG= new FabbricaRegionalGain();
		TrenoBuilder builderRG = new Assemblatore(fabbricaRG);
		
		FabbricaVagoni fabbricaFF= new FabbricaKargoModelz();
		TrenoBuilder builderFF = new Assemblatore(fabbricaFF);
		
        String sigla = "hprp";
		try
		{
			AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
		
			User mazza = new User("Salvo","Mano", "salvo.mano@gmail.com", "Danzacudur0_04", 0.0);
			UserDAO userDAO = context.getBean(UserDAO.class);
			userDAO.salvaUser(mazza);
			
			Treno trenoKM = builderKM.costruisciTreno("Treno Cargo Passeggeri",sigla,mazza, 3);
			Treno trenoRG = builderRG.costruisciTreno("Treno Cargo Passeggeri",sigla,mazza, 2);
			Treno trenoFF = builderFF.costruisciTreno("Treno Cargo Passeggeri",sigla,mazza, 1);

			TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
            trenoDAO.salvaTreno(trenoKM);
            trenoDAO.salvaTreno(trenoRG);
            trenoDAO.salvaTreno(trenoFF);
       
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
