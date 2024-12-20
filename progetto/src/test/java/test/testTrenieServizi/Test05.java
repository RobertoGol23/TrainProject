package test.testTrenieServizi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import eccezioni.eccezioniGeneriche.GenericException;
import eccezioni.eccezioniGeneriche.ServizioGiaPresenteException;
import eccezioni.eccezioniSigla.SiglaTrenoException;
import entity.classi_astratte.TrenoBuilder;
import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.Vagone;
import entity.dao.ServizioDAO;
import entity.dao.TrenoDAO;
import entity.dao.UserDAO;
import entity.servizi.Servizio;
import entity.treno.Treno;
import entity.user.User;
import fabbriche.FabbricaKargoModelz;
import fabbriche.FabbricaServizi;
import utility.Assemblatore;

public class Test05 {
    public static void main(String[] args) throws GenericException {
        
        FabbricaVagoni fabbricaKM= new FabbricaKargoModelz();
		TrenoBuilder builderKM = new Assemblatore(fabbricaKM);


		/*				TEST 05
		 * caricamento nel db di un treno con i rispettivi vagoni e nome
		 * inserimento di un SERVIZIO al database
		 * aggiunta di un servizio ad un vagone
         * update di un treno
         * 
		 * 
         * 
		 */

		String nomeTreno = "Treno della felicità";
        String sigla = "hprp";
		try
		{
			AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);			
			
			
			//User mazza = new User("Salvo","Mano", "salvo.mano@gmail.com", "Danzacudur0_04", 0.0);
			UserDAO userDAO = context.getBean(UserDAO.class);
			User user = userDAO.getUserByEmail("salvo.mano@gmail.com");
			
//			Treno trenoKM = builderKM.costruisciTreno(nomeTreno,sigla,user, 3);
//			TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
//            trenoDAO.salvaTreno(trenoKM);
			
			TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
            Treno treno = trenoDAO.getTrenoById((long)31);
			
            

            // NON SI AGGIUNGONO COSÌ! -> USARE UN SEEDER
            // AGGIUNTA DI UN SERVIZIO
            //FabbricaServizi fabbricaServizi = new FabbricaServizi();
            ServizioDAO servizioDAO = context.getBean(ServizioDAO.class);

			
//			// RIMOZIONE DI UN SERVIZIO 
//			 servizioDAO.eliminaServizioByName("bagno");
//
//            // INSERIMENTO DI UN SERVIZIO
//            try {
//				servizioDAO.salvaServizio(fabbricaServizi.creaBagno());
//			} catch (ServizioGiaPresenteException e) {
//				System.out.println(e.message());
//			}
            

            int indexVagone1 = 1;
            int indexVagone2 = 2;

            // AGGIUNTA DI UN SERVIZIO AD UN VAGONE

            Vagone vagone1 = treno.getVagone(indexVagone1);
            Servizio cinema = servizioDAO.getServizioByName("cinema");

            System.out.println("id vagone: "+ vagone1.getId());
            vagone1.addServizio(cinema);
            
            
            treno.setVagone(indexVagone1, vagone1);
            trenoDAO.updateTreno(treno); 
            
            Vagone vagone2 = treno.getVagone(indexVagone2);
            //Servizio s2 = servizioDAO.getServizioByName("cinema");
            
            System.out.println("id vagone: "+ vagone2.getId());
            vagone2.addServizio(cinema);

            servizioDAO.updateServizio(cinema);
            
            
            //servizioDAO.updateServizio(s2);
            treno.setVagone(indexVagone2, vagone2);
            trenoDAO.updateTreno(treno);
            
            
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
