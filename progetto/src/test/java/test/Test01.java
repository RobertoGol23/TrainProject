package test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import eccezioni.SiglaTrenoException;
import entity.classi_astratte.fabbrica_and_builder.FabbricaVagoni;
import entity.classi_astratte.fabbrica_and_builder.TrenoBuilder;
import entity.classi_astratte.vagoni_astratti.Vagone;
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

		FabbricaVagoni fabbricaXFF = new FabbricaXFurryFast();
		TrenoBuilder builderXFF = new Assemblatore(fabbricaXFF);


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


			Treno trenoKM = builderKM.costruisciTreno(sigla);
			vagoneInterfaceDAO.salvaVagone(trenoKM.getLocomotiva());
			for (Vagone vagone : trenoKM.getListaVagoni()){
				vagoneInterfaceDAO.salvaVagone(vagone);
			}

			Treno trenoRG = builderRG.costruisciTreno(sigla);
			vagoneInterfaceDAO.salvaVagone(trenoRG.getLocomotiva());
			for (Vagone vagone : trenoRG.getListaVagoni()){
				vagoneInterfaceDAO.salvaVagone(vagone);
			}

			Treno trenoXFF = builderXFF.costruisciTreno(sigla);
			vagoneInterfaceDAO.salvaVagone(trenoXFF.getLocomotiva());
			for (Vagone vagone : trenoXFF.getListaVagoni()){
				vagoneInterfaceDAO.salvaVagone(vagone);
			}
            
			sigla = "hcc";

			trenoKM = builderKM.costruisciTreno(sigla);
			vagoneInterfaceDAO.salvaVagone(trenoKM.getLocomotiva());
			for (Vagone vagone : trenoKM.getListaVagoni()){
				vagoneInterfaceDAO.salvaVagone(vagone);
			}

			trenoRG = builderRG.costruisciTreno(sigla);
			vagoneInterfaceDAO.salvaVagone(trenoRG.getLocomotiva());
			for (Vagone vagone : trenoRG.getListaVagoni()){
				vagoneInterfaceDAO.salvaVagone(vagone);
			}

			trenoXFF = builderXFF.costruisciTreno(sigla);
			vagoneInterfaceDAO.salvaVagone(trenoXFF.getLocomotiva());
			for (Vagone vagone : trenoXFF.getListaVagoni()){
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
