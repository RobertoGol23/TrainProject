package test.testTrenieServizi;

import java.util.ArrayList;

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

public class Test07 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		/*				TEST 07 [test di Claudio]
         *  Test rimozione e aggiunta di uno o più vagoni da un treno
         *
         *
         */
		
		String sigla = "hp";
		try
		{
			FabbricaVagoni fabbricaKM= new FabbricaKargoModelz();
			TrenoBuilder builderKM = new Assemblatore(fabbricaKM);

			TrenoUtility trenoUtility = new TrenoUtility();
		
			AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
		
            // 1 -  CREAZIONE e SALVATAGGIO USERf
			User user1 = new User("Salvo","Mano", "sa0d7s.mffffdkfuaekgdfhdffgddndfohu@gmail.com", "Danzacudur0_04", 0.0);
			UserDAO userDAO = context.getBean(UserDAO.class);
			userDAO.salvaUser(user1);

            // 2 - CREAZIONE e SALVATAGGIO TRENO
			Treno trenoKM = builderKM.costruisciTreno("DAJE ROMA DAJEEE4", sigla, user1, 2);
			TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
			trenoDAO.salvaTreno(trenoKM);

			System.out.println("\n\nTRENO SIGLA: " + trenoUtility.getSigla(trenoDAO.getTrenoById(trenoKM.getId())) + "\n\n"); 
 
			try{
				// 3 - AGGIUNTA DI VAGONI : CHECKED
				//lista posizionale dei vagoni da rimuovere
				//l'intero all'interno della lista è la posizione del vagone nella lista dei vagoni del treno
				ArrayList<Integer> listaVagoniDaAggiungere = new ArrayList<Integer>(); 
				listaVagoniDaAggiungere.add(2);
				listaVagoniDaAggiungere.add(3); 
				listaVagoniDaAggiungere.add(4);
				listaVagoniDaAggiungere.add(5);
				listaVagoniDaAggiungere.add(6);
				listaVagoniDaAggiungere.add(7);
				listaVagoniDaAggiungere.add(8);
				listaVagoniDaAggiungere.add(9);
				listaVagoniDaAggiungere.add(10);

				//Il metodo controlla la nuovaSigla modificata e se è valida aggiunge il vagone al treno
				boolean result = trenoDAO.aggiungiVagoni(trenoKM.getId(), listaVagoniDaAggiungere, "hppppppppph");

				System.out.println("\n\nTRENO DOPO L'AGGIUNTA: " + trenoUtility.getSigla(trenoDAO.getTrenoById(trenoKM.getId())) + "\n\n"); 
			}
			catch(Exception e)
			{
				System.out.println("\n\nErrore " + e + "\n\n");
			} 
			

			try{
				// 4 - RIMOZIONE DI VAGONI : CHECKED	
				ArrayList<Integer> listaVagoniDaRimuovere = new ArrayList<Integer>(); 
				listaVagoniDaRimuovere.add(4); 
				listaVagoniDaRimuovere.add(5);
				listaVagoniDaRimuovere.add(6);
				listaVagoniDaRimuovere.add(7);
				listaVagoniDaRimuovere.add(8);
				listaVagoniDaRimuovere.add(9);
				listaVagoniDaRimuovere.add(10);


				trenoDAO.eliminaVagoni(trenoKM.getId(), listaVagoniDaRimuovere); 

				trenoKM = trenoDAO.getTrenoById(trenoKM.getId());

				System.out.println("\n\nTRENO DOPO LA RIMOZIONE: " + trenoUtility.getSigla(trenoDAO.getTrenoById(trenoKM.getId())) + "\n\n"); 
				
				context.close();
			}
			catch(Exception e)
			{
				System.out.println("Errore " + e);
			}
				

			return;
		}
		catch(Exception e)
		{
			System.out.println("Errore " + e);
			return;
		}
			
	}

}
