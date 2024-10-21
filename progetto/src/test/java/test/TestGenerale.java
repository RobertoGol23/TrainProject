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
import utility.TrenoUtility;

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
            System.out.println("\n1.1 - CREAZIONE USER");
			User user1 = new User("Salvo","Mano", "salvo3.mano@gmail.com", "Danzacudur0_04", 0.0);
			UserDAO userDAO = context.getBean(UserDAO.class);

            // 1.2 - SALVATAGGIO USER
            System.out.println("\n1.2 - SALVATAGGIO USER");
			userDAO.salvaUser(user1);

            	
            // 2.1 - CREAZIONE TRENI
            System.out.println("\n2.1 - CREAZIONE TRENI");
			Treno trenoKM = builderKM.costruisciTreno("Treno Cargo KM Passeggeri", sigla, user1, 2);
			Treno trenoRG = builderRG.costruisciTreno("Treno Cargo RG Passeggeri", sigla, user1, 3);
			Treno trenoFF = builderFF.costruisciTreno("Treno Cargo FF Passeggeri", sigla, user1, 1);


            // 1.3 - UPDATE DI UN UTENTE : CHECKED
            System.out.println("\n1.3 - UPDATE DI UN UTENTE");
            user1.setNome("Salvino");
            userDAO.updateUser(user1);


            //2.2 - SALVATAGGIO TRENI
            System.out.println("\n2.2 - SALVATAGGIO TRENI");
			TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
            trenoDAO.salvaTreno(trenoKM);
            trenoDAO.salvaTreno(trenoRG);
            trenoDAO.salvaTreno(trenoFF);

            // 2.3 - UPDATE DI UN TRENO : CHECKED
            System.out.println("\n2.3 - UPDATE TRENO: KM");
            trenoKM.setNomeTreno("Treno molto bello");
            trenoDAO.updateTreno(trenoKM); //controlla sul db il nome

            // 2.4 - RIMOZIONE DI UN TRENO : CHECKED
            System.out.println(("\n2.4 - RIMOZIONE TRENO: FF"));
            trenoDAO.eliminaTrenoById(trenoFF.getId());


            TrenoUtility trenoUtility = new TrenoUtility();

            // 3.1 - AGGIUNTA DI VAGONI (testTrenieServizi -> test07)
                    //Sigla del treno: "hprp";
            System.out.println(("\n3.1 - AGGIUNTA DI UN VAGONE"));

            ArrayList<Integer> listaPosAggiunta = new ArrayList<Integer>();
            listaPosAggiunta.add(4); // aggiunta dell'ultimo vagone passeggeri

            try{
                trenoDAO.aggiungiVagoni(trenoKM.getId(), listaPosAggiunta,  "hprpp");
                System.out.println("\n\nSigla prevista: hprpp, sigla attuale: " + trenoUtility.getSigla(trenoDAO.getTrenoById(trenoKM.getId())) + "\n\n");
            }
            catch(Exception e){
                System.out.println("Errore: " + e.getMessage());
            }

            

            // 3.2 - RIMOZIONE DI VAGONI : CHECKED 
                    //Sigla del treno: "hprpp";
            System.out.println(("\n\n3.2 - RIMOZIONE VAGONE"));

            ArrayList<Integer> listaPosRimozione = new ArrayList<Integer>();
            listaPosRimozione.add(2); // rimozione del vagone ristorante
            
            try{
                trenoDAO.eliminaVagoni(trenoKM.getId(), listaPosRimozione); 
                System.out.println("\n\nSigla prevista: hppp, sigla attuale: " + trenoUtility.getSigla(trenoDAO.getTrenoById(trenoKM.getId())) + "\n\n");
            }
            catch(Exception e){
                System.out.println("Errore: " + e.getMessage());
            }




            // 3.3 - AGGIUNTA DI VAGONE, TRENO IN SOVRAPPESO : CHECKED
                    //Sigla del treno: "hppp";
            System.out.println(("\n\n\n3.3 - AGGIUNTA DI VAGONE, TRENO IN SOVRAPPESO"));

            System.out.println("TRENO ATTUALE SIGLA: " + trenoUtility.getSigla(trenoDAO.getTrenoById(trenoKM.getId())) + "\n\n");

            ArrayList<Integer> listaPosAggiunta2 = new ArrayList<Integer>();
            listaPosAggiunta2.add(4); // aggiunta di vagoni passeggeri in modo da andare sovrappeso
            listaPosAggiunta2.add(5);
            listaPosAggiunta2.add(6);
            listaPosAggiunta2.add(7);
            listaPosAggiunta2.add(8);
            listaPosAggiunta2.add(9);
            listaPosAggiunta2.add(10);
            listaPosAggiunta2.add(11);
    

            try{
                boolean result = trenoDAO.aggiungiVagoni(trenoKM.getId(), listaPosAggiunta2,  "hpppppppppph");
                if(result == true){
                    System.out.println("\n\nOperazione di aggiunta effettuata correttamente!");
                }
                else{
                    System.out.println("\n\nOperazione di aggiunta non effettuata!");
                }
                System.out.println("Sigla prevista: hpppppppppph, sigla attuale: " + trenoUtility.getSigla(trenoDAO.getTrenoById(trenoKM.getId())) + "\n\n");
            }
            catch(Exception e){
                System.out.println("Errore: " + e.getMessage());
            }



            // TODO: 3.3 MODIFICA DI UN VAGONE



           
                         
            // 4.0 - RIMOZIONE SERVIZIO 
            System.out.println("\n\n\n4.0 - RIMOZIONE SERVIZIO");
            ServizioDAO servizioDAO0= context.getBean(ServizioDAO.class);
            servizioDAO0.eliminaServizioByName("bagno");

            // 4.1 - CREAZIONE SERVIZIO : CHECKED
            System.out.println("\n4.1 - CREAZIONE SERVIZIO");
            FabbricaServizi fabbricaServizi = new FabbricaServizi();
            ServizioDAO servizioDAO = context.getBean(ServizioDAO.class);

            //4.2 - SALVATAGGIO DI UN SERVIZIO :  CHECKED 
            System.out.println("\n4.2 - SALVATAGGIO SERVIZIO");
            servizioDAO.salvaServizio(fabbricaServizi.creaBagno());

            // 4.3 - ASSEGNAZIONE SERVIZIO (ad un vagone) :  DA ERRORE
            System.out.println("\n4.3 - ASSEGNAZIONE SERVIZIO");
            trenoDAO.addServizio((long)1, 1, "bagno");
             



            // 5.0 - ACQUISTO DI UN TRENO - CHECKED
            System.out.println("\n\n\n5.0 - ACQUISTO E SALVATAGGIO DI UN TRENO");
            AcquistoDAO acquistoDAO = context.getBean(AcquistoDAO.class);
            user1.setWallet(20000000000.0);

            System.out.println("\nSalvataggio primo acquisto");
            Acquisto primoAcquisto = new Acquisto(user1, trenoKM);
            acquistoDAO.salvaAcquisto(primoAcquisto);

            /* System.out.println("Salvataggio secondo acquisto SENZA SOLDI");
        	Acquisto secondoAcquisto = new Acquisto(user1, trenoKM, "21/09/2001");
            acquistoDAO.salvaAcquisto(secondoAcquisto);  */


            // 6.0 - VOTAZIONE TRENO - CHECKED
            System.out.println("\n\n\n6.0 - VOTAZIONE TRENO");
            Voto user1Voto = new Voto(5, user1, trenoKM);
            VotoDAO votoDAO = context.getBean(VotoDAO.class);
            votoDAO.salvaVoto(user1Voto);

            // 6.1 - VOTAZIONE TRENO DALLO STESSO UTENTE - CHECKED
          /*   System.out.println("\n6.1 - VOTAZIONE TRENO DALLO STESSO UTENTE");
            Voto user1Voto2 = new Voto(5, user1, trenoKM);
            votoDAO.salvaVoto(user1Voto2);  */

            // 6.2 - VOTAZIONE TRENO valore sbagliato (casi: x < 0 || x > 10) - CHECKED
           /* System.out.println("\n6.2 - VOTAZIONE TRENO valore sbagliato (casi: x < 0 || x > 10)");
            System.out.println("6.2 - VOTAZIONE TRENO valore sbagliato (casi: x < 0 || x > 10)");
            Voto user1Voto3 = new Voto(11, user1, trenoKM);
            votoDAO.salvaVoto(user1Voto3);

            Voto user1Voto4 = new Voto(-2, user1, trenoKM);
            votoDAO.salvaVoto(user1Voto4); */


            // RIMOZIONE DI UN UTENTE : CHECKED
            System.out.println("\n\n\n7.0 - RIMOZIONE DI UN UTENTE");
            User user2 = new User("User","Da rimuovere", "user.darimuovere@gmail.com", "Danzacudur0_04", 0.0);
            userDAO.salvaUser(user2);
            userDAO.eliminaUserById(user2.getId_user());
             

            // --- QUERY USER ---

            // user by name
            System.out.println("\n\n\n\n0. USER BY NAME");
            User user0 = new User("UserDatrovare","Rossi", "user.dartrovare@gmail.com", "Danzacudur0_04", 0.0);
            userDAO.salvaUser(user0);
            System.out.println(userDAO.getUserByName("UserDatrovare"));

            // --- QUERY USER --- 



            // --- QUERY TRENO --- 

            // treno by Marca
            System.out.println("\n\n\n\n1. TRENO BY MARCA");
            System.out.println(trenoDAO.getTrenoByMarca(1));

            // treno by name
            System.out.println("\n\n\n\n2. TRENO BY NAME");
            System.out.println(trenoDAO.getTrenoByName("Treno molto bello"));

            // treno by pesoTrasportabile
            System.out.println("\n\n\n\n3. TRENO BY PESO TRASPORTABILE");
            System.out.println(trenoDAO.getTreniByPesoTrasportabile(500));

            System.out.println(("\n\n\n\n4. GET MEDIA VOTI"));
            /* System.out.println(trenoDAO.getVotazioneMedia(trenoKM)); */

            // treno by user_id
            System.out.println("\n\n\n\n5. TRENO BY USER");
            System.out.println(trenoDAO.getTrenoByUserId(user1.getId_user()));

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
