package test;

import java.util.ArrayList;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import eccezioni.eccezioniGeneriche.ServizioGiaPresenteException;
import eccezioni.eccezioniGeneriche.SoldiNonSufficientiException;
import eccezioni.eccezioniSigla.SiglaTrenoException;
import entity.acquisto.Acquisto;
import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.TrenoBuilder;
import entity.dao.AcquistoDAO;
import entity.dao.ServizioDAO;
import entity.dao.TrenoDAO;
import entity.dao.UserDAO;
import entity.dao.VotoDAO;
import entity.treno.Treno;
import entity.user.User;
import entity.votazioni.Voto;
import fabbriche.FabbricaKargoModelz;
import fabbriche.FabbricaRegionalGain;
import fabbriche.FabbricaServizi;
import utility.Assemblatore;

public class TestGenerale {
    public static void main(String[] args) {
        
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
		
            // 1.1 - CREAZIONE USER
            System.out.println("1.1 - CREAZIONE USER");
			User user1 = new User("Salvo","Mano", "salvo.mano@gmail.com", "Danzacudur0_04", 0.0);
			UserDAO userDAO = context.getBean(UserDAO.class);

            // 1.2 - SALVATAGGIO USER
            System.out.println("1.2 - SALVATAGGIO USER");
			userDAO.salvaUser(user1);

            	
            // 2.1 - CREAZIONE TRENI
            System.out.println("\n2.1 - CREAZIONE TRENI");
			Treno trenoKM = builderKM.costruisciTreno("Treno Cargo KM Passeggeri", sigla, user1, 2);
			Treno trenoRG = builderRG.costruisciTreno("Treno Cargo RG Passeggeri", sigla, user1, 3);
			Treno trenoFF = builderFF.costruisciTreno("Treno Cargo FF Passeggeri", sigla, user1, 1);


            // 1.3 - UPDATE DI UN UTENTE : CHECKED
            System.out.println("1.3 - UPDATE DI UN UTENTE");
            user1.setNome("Salvino");
            userDAO.updateUser(user1);


            //2.2 - SALVATAGGIO TRENI
            System.out.println("2.2 - SALVATAGGIO TRENI");
			TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
            trenoDAO.salvaTreno(trenoKM);
            trenoDAO.salvaTreno(trenoRG);
            trenoDAO.salvaTreno(trenoFF);

            // 2.3 - UPDATE DI UN TRENO : CHECKED
            System.out.println("2.3 - UPDATE TRENO: KM");
            trenoKM.setNomeTreno("Treno molto bello");
            trenoDAO.updateTreno(trenoKM); //controlla sul db il nome

            // 2.4 - RIMOZIONE DI UN TRENO : CHECKED
            System.out.println(("2.4 - RIMOZIONE TRENO: FF"));
            trenoDAO.eliminaTrenoById(trenoFF.getId());


            
            //int id_treno = 2; //id treno in uso per i test
            
            // 3.1 - RIMOZIONE DI VAGONI : CHECKED
            System.out.println(("3.1 - RIMOZIONE VAGONE"));
            ArrayList<Integer> listaId = new ArrayList<Integer>();
            listaId.add(2);
            trenoDAO.eliminaVagoni((long)1, listaId); //con 1-2- va , con 0-3 non deve andare
            //System.out.println(trenoRG);

            // 3.2 - AGGIUNTA DI VAGONI
            System.out.println(("3.2 - AGGIUNTA DI VAGONE"));

            // TODO: 3.3 MODIFICA DI UN VAGONE

           
                         
            // 4.0 - RIMOZIONE SERVIZIO 
            System.out.println("4.0 - RIMOZIONE SERVIZIO");
            ServizioDAO servizioDAO0= context.getBean(ServizioDAO.class);
            servizioDAO0.eliminaServizioByName("bagno");

            // 4.1 - CREAZIONE SERVIZIO : CHECKED
            System.out.println("4.1 - CREAZIONE SERVIZIO");
            FabbricaServizi fabbricaServizi = new FabbricaServizi();
            ServizioDAO servizioDAO = context.getBean(ServizioDAO.class);

            //4.2 - SALVATAGGIO DI UN SERVIZIO :  CHECKED 
            //potenziale ERRORE!!! se c'è già un servizio con quel nome
            System.out.println("4.2 - SALVATAGGIO SERVIZIO");
            servizioDAO.salvaServizio(fabbricaServizi.creaBagno());


            // 4.3 - ASSEGNAZIONE SERVIZIO (ad un vagone) :  DA ERRORE
            System.out.println("4.3 - ASSEGNAZIONE SERVIZIO");
            trenoDAO.addServizio((long)1, 1, "bagno");
             

            // 5.0 - ACQUISTO DI UN TRENO - CHECKED
            System.out.println("5.0 - ACQUISTO E SALVATAGGIO DI UN TRENO");
            AcquistoDAO acquistoDAO = context.getBean(AcquistoDAO.class);
            user1.setWallet(1900000.0);

            System.out.println("Salvataggio primo acquisto");
            Acquisto primoAcquisto = new Acquisto(user1, trenoKM);
            acquistoDAO.salvaAcquisto(primoAcquisto);

            /* System.out.println("Salvataggio secondo acquisto SENZA SOLDI");
        	Acquisto secondoAcquisto = new Acquisto(user1, trenoKM, "21/09/2001");
            acquistoDAO.salvaAcquisto(secondoAcquisto);  */


            // 6.0 - VOTAZIONE TRENO - CHECKED
            System.out.println("6.0 - VOTAZIONE TRENO");
            Voto user1Voto = new Voto(5, user1, trenoKM);
            VotoDAO votoDAO = context.getBean(VotoDAO.class);
            votoDAO.salvaVoto(user1Voto);

            // 6.1 - VOTAZIONE TRENO DALLO STESSO UTENTE - CHECKED
          /*   System.out.println("6.1 - VOTAZIONE TRENO DALLO STESSO UTENTE");
            Voto user1Voto2 = new Voto(5, user1, trenoKM);
            votoDAO.salvaVoto(user1Voto2);  */

            // 6.2 - VOTAZIONE TRENO valore sbagliato (casi: x < 0 || x > 10) - CHECKED
           /* System.out.println("6.2 - VOTAZIONE TRENO valore sbagliato (casi: x < 0 || x > 10)");
            Voto user1Voto3 = new Voto(11, user1, trenoKM);
            votoDAO.salvaVoto(user1Voto3);

            Voto user1Voto4 = new Voto(-2, user1, trenoKM);
            votoDAO.salvaVoto(user1Voto4); */


            // RIMOZIONE DI UN UTENTE : CHECKED
            System.out.println("7.0 - RIMOZIONE DI UN UTENTE");
            User user2 = new User("User","Da rimuovere", "user.darimuovere@gmail.com", "Danzacudur0_04", 0.0);
            userDAO.salvaUser(user2);
            userDAO.eliminaUserById(user2.getId_User());


             

            // --- QUERY USER ---

            // user by name
            System.out.println("\n\n1. USER BY NAME");
            //System.out.println(userDAO.getUserByName("Salvino"));

            // --- QUERY USER --- 



            // --- QUERY TRENO --- 

            // treno by Marca
            System.out.println("\n\n1. TRENO BY MARCA");
            System.out.println(trenoDAO.getTrenoByMarca(1));

            // treno by name
            System.out.println("\n\n2. TRENO BY NAME");
            System.out.println(trenoDAO.getTrenoByName("Treno molto bello"));

            // treno by pesoTrasportabile
            System.out.println("\n\n3. TRENO BY PESO TRASPORTABILE");
            System.out.println(trenoDAO.getTreniByPesoTrasportabile(500));

            System.out.println(("\n\n4. GET MEDIA VOTI"));
            System.out.println(trenoDAO.getVotazioneMedia(trenoKM));

            // --- QUERY TRENO --- 



       
			context.close();
			
		}
		catch (SiglaTrenoException e)
		{
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}
        catch(SoldiNonSufficientiException e){
            System.out.println(e.getMessage()+e.getSuggerimento());       
        } catch (ServizioGiaPresenteException e) {
        	System.out.println(e.message());
		}







    }
}
