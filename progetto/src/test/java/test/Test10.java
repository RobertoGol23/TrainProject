package test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import eccezioni.eccezioniGeneriche.SoldiNonSufficientiException;
import eccezioni.eccezioniSigla.SiglaTrenoException;
import entity.acquisto.Acquisto;
import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.TrenoBuilder;
import entity.dao.AcquistoDAO;
import entity.dao.TrenoDAO;
import entity.dao.UserDAO;
import entity.treno.Treno;
import entity.user.User;
import fabbriche.FabbricaKargoModelz;
import fabbriche.FabbricaXFurryFast;
import utility.Assemblatore;

public class Test10 {

	public static void main(String[] args) {
		
		FabbricaVagoni fabbricaFF= new FabbricaXFurryFast();
		TrenoBuilder builderFF = new Assemblatore(fabbricaFF);
		
        FabbricaVagoni fabbricaKM= new FabbricaKargoModelz();
		TrenoBuilder builderKM = new Assemblatore(fabbricaKM);
		
		/*				TEST 10
		 * prova di acquisto di due treni da due user
		 * 
		 * 
		 * 
		 */

        String sigla = "hprp";
        try {
        	
        	try
        	{
        		
        	
        	AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        	
        	User pippo = new User("Pippo","Franco", "pippo.franco@gmail.com", "1234", 0.1);
        	User pipetta = new User("Pipetta", "Soffio", "pipetta.soffio@gmail.com", "uiop", 10000000.0);
        	
        	Treno trenoFF = builderFF.costruisciTreno("Treno Cargo Passeggeri",sigla,pippo, 1);
        	Treno trenoKM = builderKM.costruisciTreno("Treno Cargo Passeggeri",sigla,pipetta, 2);
        	
        	Acquisto primo = new Acquisto(pipetta, trenoFF, "11/09/2001");
        	Acquisto secondo = new Acquisto(pippo, trenoKM, "11/09/2001");
			
        	AcquistoDAO acquistoDAO = context.getBean(AcquistoDAO.class); 
			TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
        	UserDAO userDAO = context.getBean(UserDAO.class);

        	
        	userDAO.salvaUser(pippo);
        	userDAO.salvaUser(pipetta);
        	trenoDAO.salvaTreno(trenoFF);
        	trenoDAO.salvaTreno(trenoKM);
        	acquistoDAO.salvaAcquisto(primo);
        	acquistoDAO.salvaAcquisto(secondo);
    
        	
        	context.close();
        	}
        	catch(SoldiNonSufficientiException e)
        	{
        		System.out.println(e.getMessage()+e.getSuggerimento());
        	}

        }catch(SiglaTrenoException e) {
        	String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
        }

	}

}
