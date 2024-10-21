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

public class Test11 {
    public static void main(String[] args) throws GenericException {

		/*				TEST 06
		 * prova sull'utilizzo delle criteria (UserService.java)
         * 
		 */

        try{
            AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);			
			
            FabbricaVagoni fabbricaKM= new FabbricaKargoModelz();
            TrenoBuilder builderKM = new Assemblatore(fabbricaKM);

            FabbricaVagoni fabbricaXFF= new FabbricaXFurryFast();
            TrenoBuilder builderXFF = new Assemblatore(fabbricaXFF);

            String sigla="hrp";

            User mazza = new User("Salvatore","Mazza", "salvatore.mazza@gmail.com", "Danzacudur0_04", 2000.0);

            UserDAO userDAO = context.getBean(UserDAO.class);
            userDAO.salvaUser(mazza);
                
            Treno trenoKM = builderKM.costruisciTreno("Treno Passeggeri KM",sigla, mazza, 2);
            TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
            trenoDAO.salvaTreno(trenoKM);
            
            trenoKM  = builderKM.costruisciTreno("Treno Passeggeri KM",sigla,mazza, 2);
            trenoDAO = context.getBean(TrenoDAO.class);
            trenoDAO.salvaTreno(trenoKM);

            Treno trenoxFF = builderXFF.costruisciTreno("Treno Passeggeri xFF",sigla,mazza, 1);
            trenoDAO = context.getBean(TrenoDAO.class);
            trenoDAO.salvaTreno(trenoxFF);
            


            // Query che ritorna una lista di treni con una determinata Marca
         //   String marca = "Treno KargoModelz";      
        
         //   List<Treno> listaTreniByMarca = trenoDAO.getTrenoByMarca(marca);

         //   System.out.println("TRENI TROVATI BY MARCA: " + marca + "\n" + listaTreniByMarca);




            // Query che ritorna una lista di treni tramite il suo Nome
          //  String nome = "Treno Passeggeri xFF";

          //  List<Treno> listaTreniByName2 = trenoDAO.getTrenoByName(nome);

          //  System.out.println("TRENI TROVATI BY NAME: " + nome + "\n" + listaTreniByName2);




            // Query che ritorna una lista di treni che possano trasportare ALMENO tot peso
            double pesoMin = 1000.0;

            List<Treno> listaTreniByPesoTrasportabile = trenoDAO.getTreniByPesoTrasportabile(pesoMin);

            System.out.println("TRENI TROVATI BY PESOTRASPORTABILE: " + pesoMin + "\n" + listaTreniByPesoTrasportabile);


            context.close();
        }catch(SiglaTrenoException e) {
        	String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
        }
		
	}

}
