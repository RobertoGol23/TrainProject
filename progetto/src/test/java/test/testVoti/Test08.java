package test.testVoti;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import eccezioni.eccezioniSigla.*;
import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.TrenoBuilder;
import entity.dao.TrenoDAO;
import entity.dao.UserDAO;
import entity.dao.VotoDAO;
import entity.treno.Treno;
import entity.user.User;
import entity.votazioni.Voto;
import fabbriche.FabbricaXFurryFast;
import utility.Assemblatore;

public class Test08 {

	public static void main(String[] args) {
		
		FabbricaVagoni fabbricaFF= new FabbricaXFurryFast();
		TrenoBuilder builderFF = new Assemblatore(fabbricaFF);
		
		/*				TEST 08
		 * caricamento dei voti per un treno nel db
		 * 
		 * 
		 * 
		 */

        String sigla = "hprp";
        try {
        	AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        	
        	User pippo = new User("Pippo","Franco", "pippo.franco@gmail.com", "1234", 0.0);
        	User pipetta = new User("Pipetta", "Soffio", "pipetta.soffio@gmail.com", "uiop", 0.0);
        	
        	Treno trenoFF = builderFF.costruisciTreno("Treno Cargo Passeggeri",sigla,pippo, 1);
        	
            Voto pippoVoto = new Voto(5, pippo, trenoFF);
            Voto pipettaVoto = new Voto(10, pipetta, trenoFF);
			
            VotoDAO votoDAO = context.getBean(VotoDAO.class); 
			TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
        	UserDAO userDAO = context.getBean(UserDAO.class);

        	
        	userDAO.salvaUser(pippo);
        	userDAO.salvaUser(pipetta);
        	trenoDAO.salvaTreno(trenoFF);
        	votoDAO.salvaVoto(pippoVoto);
        	votoDAO.salvaVoto(pipettaVoto);

        	context.close();

        }catch(SiglaTrenoException e) {
        	String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
        }
	}
}
