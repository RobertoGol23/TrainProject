package test.testTrenieServizi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import entity.dao.ServizioDAO;
import fabbriche.FabbricaServizi;
import utility.ServiziUtility;

public class Test08 {

	public static void main(String[] args) throws Exception {
		
		/*				TEST 8
		 * 
		 * inserimento di tutti i servizi al database
		 * 
         * 
         * 
		 * 
         * 
		 */
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
		FabbricaServizi fabbricaServizi = new FabbricaServizi();
		ServizioDAO servizioDAO = context.getBean(ServizioDAO.class);
		ServiziUtility su = new ServiziUtility();
		su.aggiungiServiziAlDB(servizioDAO);
		
		
		context.close();
	}

}
