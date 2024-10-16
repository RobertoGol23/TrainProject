package test.testTrenieServizi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import entity.dao.ServizioDAO;
import fabbriche.FabbricaServizi;

public class Test08 {

	public static void main(String[] args) {
		
		/*				TEST 0
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
		servizioDAO.salvaServizio(fabbricaServizi.creaTemperatura());
		servizioDAO.salvaServizio(fabbricaServizi.creaWifi());
		servizioDAO.salvaServizio(fabbricaServizi.creaBagno());
		servizioDAO.salvaServizio(fabbricaServizi.creaCinema());
		servizioDAO.salvaServizio(fabbricaServizi.creaColore());
		servizioDAO.salvaServizio(fabbricaServizi.creaMenu());
		servizioDAO.salvaServizio(fabbricaServizi.creaSicurezza());
		
		
		context.close();
	}

}
