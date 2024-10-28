package test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import eccezioni.eccezioniGeneriche.GenericException;
import eccezioni.eccezioniGeneriche.SoldiNonSufficientiException;
import eccezioni.eccezioniSigla.SiglaTrenoException;
import entity.acquisto.Acquisto;
import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.TrenoBuilder;
import entity.dao.AcquistoDAO;
import entity.dao.AdminDAO;
import entity.dao.TrenoDAO;
import entity.dao.UserDAO;
import entity.dao.VotoDAO;
import entity.treno.Treno;
import entity.user.Admin;
import entity.user.User;
import entity.votazioni.Voto;
import fabbriche.FabbricaKargoModelz;
import fabbriche.FabbricaRegionalGain;
import utility.Assemblatore;

public class RiempiDatabase {
    public static void main(String[] args) throws GenericException {
        
        FabbricaVagoni fabbricaKM= new FabbricaKargoModelz();
		TrenoBuilder builderKM = new Assemblatore(fabbricaKM);
		
		FabbricaVagoni fabbricaRG= new FabbricaRegionalGain();
		TrenoBuilder builderRG = new Assemblatore(fabbricaRG);
		
		FabbricaVagoni fabbricaFF= new FabbricaKargoModelz();
		TrenoBuilder builderFF = new Assemblatore(fabbricaFF);
		
        try
		{
			AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
            UserDAO userDAO = context.getBean(UserDAO.class);

            // 1. - CREAZIONE USER
            Admin admin = new Admin("admin", "admin", "admin.admin@gmail.com", "admin");
            AdminDAO adminDAO = context.getBean(AdminDAO.class);
            admin.setSuperAdmin(true);
            adminDAO.salvaAdmin(admin);


            System.out.println("\n1.1 - CREAZIONE USER");
			User user1 = new User("Ugo","Foscolo", "ugo.foscolo@gmail.com", "1234", 1000000000.0);
            userDAO.salvaUser(user1);

            User user2 = new User("Luigi", "Pirandello", "luigi.pirandello@gmail.com", "1234", 10000000000.0);
            userDAO.salvaUser(user2);

            User user3 = new User("Gabriele", "D'Annunzio", "gabriele.dannunzio@gmail.com", "1234", 10000000000.0);
            userDAO.salvaUser(user3);

            User user4 = new User("Franz", "Kafka", "franz.kafka@gmail.com", "1234", 10000000000.0);
            userDAO.salvaUser(user4);

            User user5 = new User("Alexandre", "Dumas", "alexandre.dumas@gmail.com", "1234", 10000000000.0);
            userDAO.salvaUser(user5);

            User user6 = new User("Virginia", "Woolf", "virginia.woolf@gmail.com", "1234", 10000000000.0);
            userDAO.salvaUser(user6);

            User user7 = new User("James", "Joyce", "james.joyce@gmail.com", "1234", 10000000000.0);
            userDAO.salvaUser(user7);

            User user8 = new User("Ernest", "Hemingway", "ernest.hemingway@gmail.com", "1234", 10000000000.0);
            userDAO.salvaUser(user8);

            User user9 = new User("F. Scott", "Fitzgerald", "f.scott.fitzgerald@gmail.com", "1234", 10000000000.0);
            userDAO.salvaUser(user9);

            User user10 = new User("Gabriel", "Garcia Marquez", "gabriel.garcia.marquez@gmail.com", "1234", 1000000000.0);
            userDAO.salvaUser(user10);


            // 2 - CREAZIONE TRENI e VOTAZIONI
            TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
            VotoDAO votoDAO = context.getBean(VotoDAO.class);
            AcquistoDAO acquistoDAO = context.getBean(AcquistoDAO.class); 

			Treno treno = builderKM.costruisciTreno("Foscolo's train", "hrp", user1, 2);
            trenoDAO.salvaTreno(treno);
            Voto voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);

            
        	Acquisto acquisto = new Acquisto(user1, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            acquisto = new Acquisto(user2, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            acquisto = new Acquisto(user4, treno);
			acquistoDAO.salvaAcquisto(acquisto);


            treno = builderKM.costruisciTreno("Foscolo's Express", "hrp", user1, 2);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);

            acquisto = new Acquisto(user10, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            acquisto = new Acquisto(user7, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            acquisto = new Acquisto(user3, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            acquisto = new Acquisto(user6, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            
            treno = builderRG.costruisciTreno("Foscolo's Journey", "hrph", user1, 3);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);

            acquisto = new Acquisto(user9, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            acquisto = new Acquisto(user1, treno);
			acquistoDAO.salvaAcquisto(acquisto);
            
            treno = builderFF.costruisciTreno("Foscolo's Adventure", "hpprppp", user1, 1);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            acquisto = new Acquisto(user2, treno);
			acquistoDAO.salvaAcquisto(acquisto);



            treno = builderKM.costruisciTreno("Pirandello's Train", "hpprpp", user2, 2);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);

            acquisto = new Acquisto(user8, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            acquisto = new Acquisto(user5, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            treno = builderRG.costruisciTreno("Pirandello's Express", "hppph", user2, 3);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            acquisto = new Acquisto(user7, treno);
			acquistoDAO.salvaAcquisto(acquisto);


            treno = builderFF.costruisciTreno("Pirandello's Cargo", "hcccch", user2, 1);
            trenoDAO.salvaTreno(treno);

            voto = new Voto(1, user1, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);

            acquisto = new Acquisto(user9, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            acquisto = new Acquisto(user10, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            treno = builderKM.costruisciTreno("D'Annunzio's Express", "hrp", user3, 2);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            acquisto = new Acquisto(user4, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            treno = builderRG.costruisciTreno("D'Annunzio's Journey", "hpppp", user3, 3);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);

            acquisto = new Acquisto(user1, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            acquisto = new Acquisto(user5, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            acquisto = new Acquisto(user6, treno);
			acquistoDAO.salvaAcquisto(acquisto);



            treno = builderFF.costruisciTreno("D'Annunzio's Adventure", "hpprppp", user3, 1);
            trenoDAO.salvaTreno(treno);

            voto = new Voto(1, user1, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            acquisto = new Acquisto(user8, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            acquisto = new Acquisto(user9, treno);
			acquistoDAO.salvaAcquisto(acquisto);

        
            treno = builderKM.costruisciTreno("Kafka's Express", "hccc", user4, 2);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);

            acquisto = new Acquisto(user9, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            acquisto = new Acquisto(user10, treno);
			acquistoDAO.salvaAcquisto(acquisto);


            treno = builderRG.costruisciTreno("Kafka's Journey", "hpprpp", user4, 3);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);

            treno = builderFF.costruisciTreno("Kafka's Adventure", "hph", user4, 1);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);

            acquisto = new Acquisto(user1, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            acquisto = new Acquisto(user9, treno);
			acquistoDAO.salvaAcquisto(acquisto);


            treno = builderKM.costruisciTreno("Dumas's Express", "hrp", user5, 2);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);

            acquisto = new Acquisto(user10, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            acquisto = new Acquisto(user7, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            treno = builderRG.costruisciTreno("Dumas's Journey", "hpprpp", user5, 3);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);

            acquisto = new Acquisto(user6, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            acquisto = new Acquisto(user7, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            acquisto = new Acquisto(user8, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            acquisto = new Acquisto(user3, treno);
			acquistoDAO.salvaAcquisto(acquisto);


            treno = builderFF.costruisciTreno("Dumas's Adventure", "hprph", user5, 1);
            trenoDAO.salvaTreno(treno);

            voto = new Voto(1, user1, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);

            acquisto = new Acquisto(user3, treno);
			acquistoDAO.salvaAcquisto(acquisto);

            treno = builderKM.costruisciTreno("Woolf's Express", "hc", user6, 2);
            trenoDAO.salvaTreno(treno);
            treno = builderRG.costruisciTreno("Joyce's Journey", "hrp", user7, 3);
            trenoDAO.salvaTreno(treno);
            treno = builderFF.costruisciTreno("Hemingway's Train", "hcccc", user8, 1);
            trenoDAO.salvaTreno(treno);

            voto = new Voto(1, user1, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);

            treno = builderKM.costruisciTreno("Fitzgerald's Dream", "hprpp", user9, 2);
            trenoDAO.salvaTreno(treno);


            treno = builderRG.costruisciTreno("Garcia Marquez's Magic", "hpph", user10, 3);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);


            treno = builderFF.costruisciTreno("Woolf's Reflection", "hprph", user6, 1);
            trenoDAO.salvaTreno(treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            treno = builderKM.costruisciTreno("Joyce's Legacy", "hch", user7, 2);
            trenoDAO.salvaTreno(treno);


            treno = builderRG.costruisciTreno("Hemingway's Adventure", "hppph", user8, 3);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);


            treno = builderFF.costruisciTreno("Fitzgerald's Tale", "hrp", user9, 1);
            trenoDAO.salvaTreno(treno);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);


            treno = builderKM.costruisciTreno("Garcia Marquez's Train", "hrp", user10, 2);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);


            treno = builderRG.costruisciTreno("Woolf's Voyage", "hcccch", user6, 3);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);

            
            treno = builderFF.costruisciTreno("Joyce's Odyssey", "hrp", user7, 1);
            trenoDAO.salvaTreno(treno);

            voto = new Voto(1, user1, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);


            treno = builderKM.costruisciTreno("Foscolo's Twilight", "hcccch", user1, 2);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);


            treno = builderRG.costruisciTreno("Foscolo's Dream Train", "hrp", user1, 3);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);
            

            treno = builderFF.costruisciTreno("Foscolo's Heritage", "hpppph", user1, 1);
            trenoDAO.salvaTreno(treno);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);


            treno = builderKM.costruisciTreno("Pirandello's Enigma", "hpprpph", user2, 2);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);


            treno = builderRG.costruisciTreno("Pirandello's Horizon", "hppph", user2, 3);
            trenoDAO.salvaTreno(treno);


            treno = builderFF.costruisciTreno("Pirandello's Whisper", "hcccch", user2, 1);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);


            treno = builderKM.costruisciTreno("D'Annunzio's Voyage", "hcccch", user3, 2);
            trenoDAO.salvaTreno(treno);


            treno = builderRG.costruisciTreno("D'Annunzio's Flight", "hprph", user3, 3);
            trenoDAO.salvaTreno(treno);


            treno = builderFF.costruisciTreno("D'Annunzio's Serenade", "hppph", user3, 1);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);


            treno = builderKM.costruisciTreno("Kafka's Journey", "hppph", user4, 2);
            trenoDAO.salvaTreno(treno);


            treno = builderRG.costruisciTreno("Kafka's Dream", "hrp", user4, 3);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);


            treno = builderFF.costruisciTreno("Kafka's Legacy", "hpppph", user4, 1);
            trenoDAO.salvaTreno(treno);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);


            treno = builderKM.costruisciTreno("Dumas's Quest", "hrp", user5, 2);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);


            treno = builderRG.costruisciTreno("Dumas's Adventure", "hrp", user5, 3);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);


            treno = builderFF.costruisciTreno("Dumas's Epic", "hpprppp", user5, 1);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);


            treno = builderKM.costruisciTreno("Woolf's Insight", "hppph", user6, 2);
            trenoDAO.salvaTreno(treno);


            treno = builderRG.costruisciTreno("Woolf's Dream", "hrp", user6, 3);
            trenoDAO.salvaTreno(treno);


            treno = builderFF.costruisciTreno("Woolf's Reflection", "hprph", user6, 1);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);


            treno = builderKM.costruisciTreno("Joyce's Voyage", "hch", user7, 2);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);


            treno = builderRG.costruisciTreno("Joyce's Flight", "hrp", user7, 3);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);


            treno = builderFF.costruisciTreno("Joyce's Journey", "hrp", user7, 1);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);


            treno = builderKM.costruisciTreno("Hemingway's Adventure", "hpppph", user8, 2);
            trenoDAO.salvaTreno(treno);


            treno = builderRG.costruisciTreno("Hemingway's Legacy", "hcccc", user8, 3);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);


            treno = builderFF.costruisciTreno("Hemingway's Journey", "hrp", user8, 1);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);


            treno = builderKM.costruisciTreno("Fitzgerald's Vision", "hprpp", user9, 2);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);


            treno = builderRG.costruisciTreno("Fitzgerald's Dream", "hrp", user9, 3);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);


            treno = builderFF.costruisciTreno("Fitzgerald's Tale", "hccch", user9, 1);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);


            treno = builderKM.costruisciTreno("Garcia Marquez's Journey", "hrp", user10, 2);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            treno = builderRG.costruisciTreno("Garcia Marquez's Dream", "hpph", user10, 3);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(1, user1, treno);

            voto = new Voto(1, user6, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);


            treno = builderFF.costruisciTreno("Garcia Marquez's Reflection", "hprph", user10, 1);
            trenoDAO.salvaTreno(treno);
            voto = new Voto(2, user2, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user3, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user4, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user5, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(2, user7, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(3, user8, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(4, user9, treno);
            votoDAO.salvaVoto(voto);

            voto = new Voto(5, user10, treno);
            votoDAO.salvaVoto(voto);


       
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
        }







    }
}
