package test.testTrenieServizi;

import java.util.ArrayList;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import entity.dao.TrenoDAO;

public class Test07 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		/*				TEST 07 [test di Claudio]
         *  Test rimozione e aggiunta di uno o più vagoni da un treno
         *
         *
         *
         *
         */
		
		try
		{
			AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
			TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
			
			int id_treno = 2; //id treno in uso per i test

			System.out.println(trenoDAO.getTrenoById((long)id_treno).toString());
			
			
			ArrayList<Integer> listaId = new ArrayList<Integer>();
	       
			
			
			// 3.2 - AGGIUNTA DI VAGONI
	        System.out.println(("3.2 - AGGIUNTA DI VAGONE"));
			
	        //la sigla passata "hpp" dovrebbe indicare la sigla nuova modificata con i vagoni nuovi inseriti
	        //la listaId indica dove si trovano i vagoni nuovi
	        //l'unione dei due parametri ci permette di capire dove il vagone è stato messo e di che tipo è
			trenoDAO.aggiungiVagoni((long)id_treno, listaId, "hpp");
			System.out.println(trenoDAO.getTrenoById((long)id_treno).toString());
	        
			if(true)
			{
				// 3.1 - RIMOZIONE DI VAGONI : CHECKED
		        System.out.println(("3.1 - RIMOZIONE VAGONE"));
		        
		        listaId.add(1);
//		        listaId.add(2);
//		        listaId.add(3);
//		        listaId.add(4);

		        trenoDAO.eliminaVagoni((long)id_treno, listaId); //con 1-2- va , con 0-3 non deve andare

		        System.out.println(trenoDAO.getTrenoById((long)id_treno).toString());
			}
	         
	        
	        context.close();
		}
		catch(Exception e)
		{
			System.out.println("Errore " + e);
		}
		
		
		
		
		
		
		
	}

}
