package test.testTrenieServizi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import entity.classi_astratte.Vagone;
import entity.dao.VagoneDAO;
import jakarta.transaction.Transactional;

public class Test10 {

	@Transactional
	public static void main(String[] args) {
	    Long idServizio = (long)1;
	    Long idVagone = (long) 303;
	    
	    /*
	     * Test rimozione servizio da un vagone
	     * 
	     * 
	     * 
	     * 
	     */
	    
	    AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
	    VagoneDAO vagoneDAO = context.getBean(VagoneDAO.class);
	    Vagone vagone = vagoneDAO.getVagoneById(idVagone);
	   
	    System.out.println("lista servizi prima della modifica: " + vagone.getListaServizi());
	    vagoneDAO.removeServizioFromVagone(idVagone, idServizio);
	    System.out.println("lista servizi: " + vagone.getListaServizi());
	    context.close();
	}



}
