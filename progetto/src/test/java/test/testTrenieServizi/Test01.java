package test.testTrenieServizi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import eccezioni.eccezioniGeneriche.GenericException;
import eccezioni.eccezioniSigla.SiglaTrenoException;
import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.TrenoBuilder;
import entity.classi_astratte.Vagone;
import entity.dao.UserDAO;
import entity.dao.VagoneDAO;
import entity.treno.Treno;
import entity.user.User;
import fabbriche.FabbricaKargoModelz;
import fabbriche.FabbricaRegionalGain;
import fabbriche.FabbricaXFurryFast;
import utility.Assemblatore;

public class Test01 {
    public static void main(String[] args) throws GenericException {
        
        FabbricaVagoni fabbricaKM= new FabbricaKargoModelz();
		TrenoBuilder builderKM = new Assemblatore(fabbricaKM);

		FabbricaVagoni fabbricaRG = new FabbricaRegionalGain();
		TrenoBuilder builderRG = new Assemblatore(fabbricaRG);

		FabbricaVagoni fabbricaFF = new FabbricaXFurryFast();
		TrenoBuilder builderFF = new Assemblatore(fabbricaFF);


		/*				TEST 01
		 * caricamento nel db di ogni tipologia di elemento del treno
		 * 
		 * 
		 * 
		 */


        String sigla = "hprpp";
		try
		{
			AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
			
			VagoneDAO vagoneInterfaceDAO = context.getBean(VagoneDAO.class);
			
			User mano = new User("Salvo","Mano", "salvo.mano@gmail.com", "Danzacudur0_04", 0.0);
			UserDAO userDAO = context.getBean(UserDAO.class);
			userDAO.salvaUser(mano);


			Treno trenoKM = builderKM.costruisciTreno("Treno Passeggeri KM",sigla,mano, 3);

			for (Vagone vagone : trenoKM.getListaVagoni()){
				vagoneInterfaceDAO.salvaVagone(vagone);
			}


			Treno trenoRG = builderRG.costruisciTreno("Treno Passeggeri RG",sigla,mano, 2);

			for (Vagone vagone : trenoRG.getListaVagoni()){
				vagoneInterfaceDAO.salvaVagone(vagone);
			}


			Treno trenoFF = builderFF.costruisciTreno("Treno Passeggeri FF",sigla,mano, 1);
			for (Vagone vagone : trenoFF.getListaVagoni()){

				vagoneInterfaceDAO.salvaVagone(vagone);
			}
            
			sigla = "hcc";

			trenoKM = builderKM.costruisciTreno("Treno Cargo KM",sigla,mano, 3);

			for (Vagone vagone : trenoKM.getListaVagoni()){
				vagoneInterfaceDAO.salvaVagone(vagone);
			}

			trenoRG = builderRG.costruisciTreno("Treno Cargo RG",sigla,mano, 2);

			for (Vagone vagone : trenoRG.getListaVagoni()){
				vagoneInterfaceDAO.salvaVagone(vagone);
			}

			trenoFF = builderFF.costruisciTreno("Treno Cargo F",sigla,mano, 1);
			for (Vagone vagone : trenoFF.getListaVagoni()){
				vagoneInterfaceDAO.salvaVagone(vagone);
			}
			
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
