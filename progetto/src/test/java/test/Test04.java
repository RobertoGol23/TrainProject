package test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import entity.dao.UserDAO;
import entity.user.User;

public class Test04 {
	public static void main(String[] args) {
		

		/*				TEST 04
		 * caricamento nel db di piu user
		 * 
		 * 
		 * 
		 */
		
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
	
		User pippo = new User("Pippo","Franco", "pippo.franco@gmail.com", "1234");
		User pipetta = new User("Pipetta", "Soffio", "pipetta.soffio@gmail.com", "uiop");
		User totò = new User("Totò", "Sorriso", "toto.sorriso@gmail.com", "lala");
		User gilda = new User("Gilda", "Piuma", "gilda.piuma@gmail.com", "nono");
		User lillo = new User("Lillo", "Zuppa", "lillo.zuppa@gmail.com", "4545");
		User patacca = new User("Patacca", "Alzata", "patacca.alzata@gmail.com", "uhuh");
		User carletto = new User("Carletto", "Biscotto", "carletto.biscotto@gmail.com", "3030");
		User ciambella = new User("Ciambella", "Rosa", "ciambella.rosa@gmail.com", "mumu");
		User zazà = new User("Zazà", "Pallino", "zaza.pallino@gmail.com", "2323");
		User puffo = new User("Puffo", "Blu", "puffo.blu@gmail.com", "5656");
		User strullo = new User("Strullo", "Matto", "strullo.matto@gmail.com", "trtr");
		User puzzola = new User("Puzzola", "Tosta", "puzzola.tosta@gmail.com", "2324");
		User patè = new User("Patè", "DiFegato", "pate.fegato@gmail.com", "1212");
		User torello = new User("Torello", "Sbronzo", "torello.sbronzo@gmail.com", "3636");
		User bolla = new User("Bolla", "DiSapone", "bolla.sapone@gmail.com", "2928");
		User zucca = new User("Zucca", "Vuota", "zucca.vuota@gmail.com", "pipi");
		User caciotta = new User("Caciotta", "Gustosa", "caciotta.gustosa@gmail.com", "4444");
		User fungo = new User("Fungo", "Porcino", "fungo.porcino@gmail.com", "1313");
		User cotoletta = new User("Cotoletta", "Dorata", "cotoletta.dorata@gmail.com", "nini");
		User pupazzo = new User("Pupazzo", "DiNeve", "pupazzo.dineve@gmail.com", "2323");

		UserDAO userDAO = context.getBean(UserDAO.class);
		
		
        userDAO.salvaUser(pippo);
        userDAO.salvaUser(pipetta);
        userDAO.salvaUser(totò);
        userDAO.salvaUser(gilda);
        userDAO.salvaUser(lillo);
        userDAO.salvaUser(patacca);
        userDAO.salvaUser(carletto);
        userDAO.salvaUser(ciambella);
        userDAO.salvaUser(zazà);
        userDAO.salvaUser(puffo);
        userDAO.salvaUser(strullo);
        userDAO.salvaUser(puzzola);
        userDAO.salvaUser(patè);
        userDAO.salvaUser(torello);
        userDAO.salvaUser(bolla);
        userDAO.salvaUser(zucca);
        userDAO.salvaUser(caciotta);
        userDAO.salvaUser(fungo);
        userDAO.salvaUser(cotoletta);
        userDAO.salvaUser(pupazzo);

		context.close();
	
	
	
	}
}
