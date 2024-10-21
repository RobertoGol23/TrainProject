package test.testTrenieServizi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.TrenoBuilder;
import entity.dao.TrenoDAO;
import entity.dao.UserDAO;
import entity.treno.Treno;
import entity.user.User;
import fabbriche.FabbricaKargoModelz;
import utility.Assemblatore;
import utility.TrenoUtility;

public class Test09 {
	

	public static void main(String[] args) {
		
		/*				TEST 09
         *  Test per ribaltare il treno
         *
         *
         */
		
		String sigla = "hprph";
		
		
			FabbricaVagoni fabbricaKM= new FabbricaKargoModelz();
			TrenoBuilder builderKM = new Assemblatore(fabbricaKM);

			TrenoUtility trenoUtility = new TrenoUtility();
		
			AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
		
            // 1 -  CREAZIONE e SALVATAGGIO USERf
			User user1 = new User("Pippo","Baudo", "pippo3.baudo@gmail.com", "1234", 0.0);
			UserDAO userDAO = context.getBean(UserDAO.class);
			userDAO.salvaUser(user1);

            // 2 - CREAZIONE e SALVATAGGIO TRENO
			Treno trenoKM = builderKM.costruisciTreno("DAJE ROMA DAJEEE4", sigla, user1, 2);
			TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
			trenoDAO.salvaTreno(trenoKM);

			System.out.println("\n\n" + trenoKM + "\n\n"); 
		
			trenoUtility.ribaltaTreno(trenoKM);
			
			System.out.println("\n\n" + trenoKM + "\n\n"); 
			
			

}
}