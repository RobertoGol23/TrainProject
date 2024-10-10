package test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import eccezioni.SiglaTrenoException;
import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.TrenoBuilder;
import entity.classi_astratte.Vagone;
import entity.dao.VagoneDAO;
import entity.treno.Treno;
import fabbriche.FabbricaKargoModelz;
import fabbriche.FabbricaRegionalGain;
import fabbriche.FabbricaXFurryFast;
import utility.Assemblatore;

public class Test01 {
    public static void main(String[] args) {
        
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




        String sigla = "hprp";
		try
		{
			AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
			
			VagoneDAO vagoneInterfaceDAO = context.getBean(VagoneDAO.class);


			Treno trenoKM = builderKM.costruisciTreno("Treno Passeggeri KM",sigla);
			vagoneInterfaceDAO.salvaVagone(trenoKM.getLocomotiva());
			for (Vagone vagone : trenoKM.getListaVagoni()){
				vagoneInterfaceDAO.salvaVagone(vagone);
			}

			Treno trenoRG = builderRG.costruisciTreno("Treno Passeggeri RG",sigla);
			vagoneInterfaceDAO.salvaVagone(trenoRG.getLocomotiva());
			for (Vagone vagone : trenoRG.getListaVagoni()){
				vagoneInterfaceDAO.salvaVagone(vagone);
			}

			Treno trenoFF = builderFF.costruisciTreno("Treno Passeggeri FF",sigla);
			vagoneInterfaceDAO.salvaVagone(trenoFF.getLocomotiva());
			for (Vagone vagone : trenoFF.getListaVagoni()){
				vagoneInterfaceDAO.salvaVagone(vagone);
			}
            
			sigla = "hcc";

			trenoKM = builderKM.costruisciTreno("Treno Cargo KM",sigla);
			vagoneInterfaceDAO.salvaVagone(trenoKM.getLocomotiva());
			for (Vagone vagone : trenoKM.getListaVagoni()){
				vagoneInterfaceDAO.salvaVagone(vagone);
			}

			trenoRG = builderRG.costruisciTreno("Treno Cargo RG",sigla);
			vagoneInterfaceDAO.salvaVagone(trenoRG.getLocomotiva());
			for (Vagone vagone : trenoRG.getListaVagoni()){
				vagoneInterfaceDAO.salvaVagone(vagone);
			}

			trenoFF = builderFF.costruisciTreno("Treno Cargo F",sigla);
			vagoneInterfaceDAO.salvaVagone(trenoFF.getLocomotiva());
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
