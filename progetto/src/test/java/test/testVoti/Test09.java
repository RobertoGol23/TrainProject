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
import jakarta.persistence.PersistenceException;
import utility.Assemblatore;

public class Test09 {

	public static void main(String[] args) {
		
		FabbricaVagoni fabbricaFF= new FabbricaXFurryFast();
		TrenoBuilder builderFF = new Assemblatore(fabbricaFF);
		
		/*				TEST 08
		 * selezione dei treni by Votazione media
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
            Voto pipettaVoto = new Voto(4, pipetta, trenoFF);
            Voto pippoVoto2 = new Voto(5, pippo, trenoFF);
			
            VotoDAO votoDAO = context.getBean(VotoDAO.class); 
			TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
        	UserDAO userDAO = context.getBean(UserDAO.class);

        	
        	userDAO.salvaUser(pippo);
        	userDAO.salvaUser(pipetta);
        	trenoDAO.salvaTreno(trenoFF);
        	votoDAO.salvaVoto(pippoVoto);
        	votoDAO.salvaVoto(pipettaVoto);
        	try {
        	    // Codice per salvare il voto
        		votoDAO.salvaVoto(pippoVoto2); //si spacca perche' si inserisce due volte la chiave pippo trenoFF
        	} catch (PersistenceException e) {
        	    // Gestisci il caso in cui l'utente ha già votato quel treno
        	    System.out.println("L'utente ha già votato questo treno");
        	}
        	

            System.out.println(trenoDAO.getTreniOrderByVotazione());

        	context.close();

        }catch(SiglaTrenoException e) {
        	String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
        }
	}
}
