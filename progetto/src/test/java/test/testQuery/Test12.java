package test.testQuery;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import eccezioni.eccezioniGeneriche.GenericException;
import eccezioni.eccezioniSigla.SiglaTrenoException;
import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.TrenoBuilder;
import entity.dao.TrenoDAO;
import entity.dao.UserDAO;
import entity.treno.Treno;
import entity.user.User;
import fabbriche.FabbricaKargoModelz;
import fabbriche.FabbricaXFurryFast;
import utility.Assemblatore;

public class Test12 {
    public static void main(String[] args) throws GenericException {

		/*				TEST 12
		 * prova sul form del train market
         * 
		 */

        try{
            AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);			
			
            FabbricaVagoni fabbricaKM= new FabbricaKargoModelz();
            TrenoBuilder builderKM = new Assemblatore(fabbricaKM);

            FabbricaVagoni fabbricaXFF= new FabbricaXFurryFast();
            TrenoBuilder builderXFF = new Assemblatore(fabbricaXFF);

            String sigla="hpprppp";

            User user1 = new User("Ricky","One", "ricky.one@gtmail.com", "Danzacudur0_04", 200000.0);

            UserDAO userDAO = context.getBean(UserDAO.class);
            userDAO.salvaUser(user1);
                
            Treno trenoKM = builderKM.costruisciTreno("aaa Primo treno", sigla, user1, 2);
            TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
            trenoDAO.salvaTreno(trenoKM);
            
            sigla="hprpp";
            Treno trenoKM1  = builderKM.costruisciTreno("bbb Secondo treno", sigla, user1, 2);
            trenoDAO = context.getBean(TrenoDAO.class);
            trenoDAO.salvaTreno(trenoKM1);

            sigla="hppprpppp";
            Treno trenoxFF = builderXFF.costruisciTreno("aaaa Terzo treno", sigla,user1, 1);
            trenoDAO = context.getBean(TrenoDAO.class);
            trenoDAO.salvaTreno(trenoxFF);
            
            System.out.println("\n TRENI CREATI: \n" );
                System.out.println("Marca: " + trenoKM.getMarca() + "Nome: \"" + trenoKM.getNome() + "\" Peso: " + trenoKM.getPesoTotaleTreno() + " Lunghezza: " + trenoKM.getListaVagoni().size() + " Prezzo: " + trenoKM.getPrezzoTotaleTreno() + "\n" ); 
                System.out.println("Marca: " + trenoKM1.getMarca() + "Nome: \"" + trenoKM1.getNome() + "\" Peso: " + trenoKM1.getPesoTotaleTreno() + " Lunghezza: " + trenoKM1.getListaVagoni().size() + " Prezzo: " + trenoKM1.getPrezzoTotaleTreno() + "\n" );
                System.out.println("Marca: " + trenoxFF.getMarca() + "Nome: \"" + trenoxFF.getNome() + "\" Peso: " + trenoxFF.getPesoTotaleTreno() + " Lunghezza: " + trenoxFF.getListaVagoni().size() + " Prezzo: " + trenoxFF.getPrezzoTotaleTreno() + "\n" );
            
            
            
            // RISULTATI PER PESO:   MIN: 200 MAX: 300 -> mi aspetto Primo Treno e Secondo Treno
            List<Treno> listaTreniTrovati_PRIMA = trenoDAO.cercaTreni("ordinamento", 200.0, 300.0, 0,  100, 0.0, 99999999999.0 );

            System.out.println("\n RICERCA TRENI TROVATI (peso): " + listaTreniTrovati_PRIMA.size() + "\n" );
            for(Treno treno : listaTreniTrovati_PRIMA){
                System.out.println("Marca: " + treno.getMarca() + " Nome: \"" + treno.getNome() + "\" Peso: " + treno.getPesoTotaleTreno() + " Lunghezza: " + treno.getListaVagoni().size() + " Prezzo: " + treno.getPrezzoTotaleTreno() + "\n" ); 
            }

            // RISULTATI PER PESO: LUNGHEZZA   MIN: 7  MAX: 8 -> mi aspetto Primo Treno
            List<Treno> listaTreniTrovati_SECONDA = trenoDAO.cercaTreni("ordinamento", 0.0, 99999999999.0, 7,  8, 0.0, 99999999999.0 );

            System.out.println("\n RICERCA TRENI TROVATI (lunghezza): " + listaTreniTrovati_SECONDA.size() + "\n" );
            for(Treno treno : listaTreniTrovati_SECONDA){
                System.out.println("Marca: " + treno.getMarca() + " Nome: \"" + treno.getNome() + "\" Peso: " + treno.getPesoTotaleTreno() + " Lunghezza: " + treno.getListaVagoni().size() + " Prezzo: " + treno.getPrezzoTotaleTreno() + "\n" ); 
            }

            // RISULTATI PER PREZZO: MIN: 200   PESO MAX: 300 -> mi aspetto Terzo treno
            List<Treno> listaTreniTrovati_TERZA = trenoDAO.cercaTreni("ordinamento", 0.0, 99999999999.0, 0,  999999999, 0.0, 200000000.0 );

            System.out.println("\n RICERCA TRENI TROVATI (prezzo): " + listaTreniTrovati_TERZA.size() + "\n" );
            for(Treno treno : listaTreniTrovati_TERZA){
                System.out.println("Marca: " + treno.getMarca() + " Nome: \"" + treno.getNome() + "\" Peso: " + treno.getPesoTotaleTreno() + " Lunghezza: " + treno.getListaVagoni().size() + " Prezzo: " + treno.getPrezzoTotaleTreno() + "\n" ); 
            }




            // RISULTATI PER PESO: LUNGHEZZA   MIN: null  MAX: null -> mi aspetto TUTTI i treni
            List<Treno> listaTreniTrovati_PROVA_1 = trenoDAO.cercaTreni("ordinamento", null, null, 0,  999999999, 0.0, 99999999999.0 );

            System.out.println("\n RICERCA TRENI TROVATI (lunghezza): " + listaTreniTrovati_PROVA_1.size() + "\n" );
            for(Treno treno : listaTreniTrovati_PROVA_1){
                System.out.println("Marca: " + treno.getMarca() + " Nome: \"" + treno.getNome() + "\" Peso: " + treno.getPesoTotaleTreno() + " Lunghezza: " + treno.getListaVagoni().size() + " Prezzo: " + treno.getPrezzoTotaleTreno() + "\n" ); 
            }



            // RISULTATI PER PESO: LUNGHEZZA   MIN: null  MAX: null -> mi aspetto TUTTI i treni
            List<Treno> listaTreniTrovati_PROVA_2 = trenoDAO.cercaTreni("ordinamento", 0.0, 99999999999.0, null,  null, 0.0, 99999999999.0 );

            System.out.println("\n RICERCA TRENI TROVATI (lunghezza): " + listaTreniTrovati_PROVA_2.size() + "\n" );
            for(Treno treno : listaTreniTrovati_PROVA_2){
                System.out.println("Marca: " + treno.getMarca() + " Nome: \"" + treno.getNome() + "\" Peso: " + treno.getPesoTotaleTreno() + " Lunghezza: " + treno.getListaVagoni().size() + " Prezzo: " + treno.getPrezzoTotaleTreno() + "\n" ); 
            }


            // RISULTATI PER PREZZO: MIN: null   PESO MAX: null -> mi aspetto TUTTI i treni
            List<Treno> listaTreniTrovati_PROVA_3 = trenoDAO.cercaTreni("ordinamento", 0.0, 99999999999.0, 0,  999999999, null, null );

            System.out.println("\n RICERCA TRENI TROVATI (prezzo): " + listaTreniTrovati_PROVA_3.size() + "\n" );
            for(Treno treno : listaTreniTrovati_PROVA_3){
                System.out.println("Marca: " + treno.getMarca() + " Nome: \"" + treno.getNome() + "\" Peso: " + treno.getPesoTotaleTreno() + " Lunghezza: " + treno.getListaVagoni().size() + " Prezzo: " + treno.getPrezzoTotaleTreno() + "\n" ); 
            }

            
            // ORDINAMENTO PER VOTO
            List<Treno> listaTreniTrovati_ORDINAMENTO_1 = trenoDAO.cercaTreni("ordina_per_voto", null, null, null,  null, null, null );

            System.out.println("\n RICERCA TRENI TROVATI (prezzo): " + listaTreniTrovati_ORDINAMENTO_1.size() + "\n" );
            for(Treno treno : listaTreniTrovati_ORDINAMENTO_1){
                System.out.println("Marca: " + treno.getMarca() + " Nome: \"" + treno.getNome() + "\" Peso: " + treno.getPesoTotaleTreno() + " Lunghezza: " + treno.getListaVagoni().size() + " Prezzo: " + treno.getPrezzoTotaleTreno() + "\n" ); 
            } 

            // ORDINAMENTO PER PREZZO
            List<Treno> listaTreniTrovati_ORDINAMENTO_2 = trenoDAO.cercaTreni("ordina_per_prezzo", null, null, null,  null, null, null );

            System.out.println("\n RICERCA TRENI TROVATI (prezzo): " + listaTreniTrovati_ORDINAMENTO_2.size() + "\n" );
            for(Treno treno : listaTreniTrovati_ORDINAMENTO_2){
                System.out.println("Marca: " + treno.getMarca() + " Nome: \"" + treno.getNome() + "\" Peso: " + treno.getPesoTotaleTreno() + " Lunghezza: " + treno.getListaVagoni().size() + " Prezzo: " + treno.getPrezzoTotaleTreno() + "\n" ); 
            } 

            // ORDINAMENTO PER NOME
            List<Treno> listaTreniTrovati_ORDINAMENTO_3 = trenoDAO.cercaTreni("ordina_per_nome", null, null, null,  null, null, null );

            System.out.println("\n RICERCA TRENI TROVATI (prezzo): " + listaTreniTrovati_ORDINAMENTO_3.size() + "\n" );
            for(Treno treno : listaTreniTrovati_ORDINAMENTO_3){
                System.out.println("Marca: " + treno.getMarca() + " Nome: \"" + treno.getNome() + "\" Peso: " + treno.getPesoTotaleTreno() + " Lunghezza: " + treno.getListaVagoni().size() + " Prezzo: " + treno.getPrezzoTotaleTreno() + "\n" ); 
            } 

            // ORDINAMENTO PER PESO
            List<Treno> listaTreniTrovati_ORDINAMENTO_4 = trenoDAO.cercaTreni("ordina_per_peso", null, null, null,  null, null, null );

            System.out.println("\n RICERCA TRENI TROVATI (prezzo): " + listaTreniTrovati_ORDINAMENTO_4.size() + "\n" );
            for(Treno treno : listaTreniTrovati_ORDINAMENTO_4){
                System.out.println("Marca: " + treno.getMarca() + " Nome: \"" + treno.getNome() + "\" Peso: " + treno.getPesoTotaleTreno() + " Lunghezza: " + treno.getListaVagoni().size() + " Prezzo: " + treno.getPrezzoTotaleTreno() + "\n" ); 
            } 

            context.close();
        }catch(SiglaTrenoException e) {
        	String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
        }
		
	}

}
