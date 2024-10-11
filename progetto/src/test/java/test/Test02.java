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
		
			User mazza = new User("Salvatore","Mazza", "salvatore.mazza@gmail.com", "Danzacudur0_04");
			UserDAO userDAO = context.getBean(UserDAO.class);
			userDAO.salvaUser(mazza);
			
			Treno trenoKM = builderKM.costruisciTreno("Treno Cargo Passeggeri",sigla,mazza, 3);

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
