package test.testUser;

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
	
		User pippo = new User("Pippo","Franco", "pippo.franco@gmail.com", "1234", 1000.0);
		User pipetta = new User("Pipetta", "Soffio", "pipetta.soffio@gmail.com", "uiop", 2000.0);
		User totò = new User("Totò", "Sorriso", "toto.sorriso@gmail.com", "lala", 3000.0);
		User gilda = new User("Gilda", "Piuma", "gilda.piuma@gmail.com", "nono", 1234.0);
		User lillo = new User("Lillo", "Zuppa", "lillo.zuppa@gmail.com", "4545", 1234.0);
		User patacca = new User("Patacca", "Alzata", "patacca.alzata@gmail.com", "uhuh", 1234.0);
		User carletto = new User("Carletto", "Biscotto", "carletto.biscotto@gmail.com", "3030", 1234.0);
		User ciambella = new User("Ciambella", "Rosa", "ciambella.rosa@gmail.com", "mumu", 1234.0);
		User zazà = new User("Zazà", "Pallino", "zaza.pallino@gmail.com", "2323", 1234.0);
		User puffo = new User("Puffo", "Blu", "puffo.blu@gmail.com", "5656", 1234.0);
		User strullo = new User("Strullo", "Matto", "strullo.matto@gmail.com", "trtr", 123422.0);
		User puzzola = new User("Puzzola", "Tosta", "puzzola.tosta@gmail.com", "2324", 1212134.0);
		User patè = new User("Patè", "DiFegato", "pate.fegato@gmail.com", "1212", 4441234.0);
		User torello = new User("Torello", "Sbronzo", "torello.sbronzo@gmail.com", "3636", 12676734.0);
		User bolla = new User("Bolla", "DiSapone", "bolla.sapone@gmail.com", "2928", 12331444.0);
		User zucca = new User("Zucca", "Vuota", "zucca.vuota@gmail.com", "pipi", 1211234.0);
		User caciotta = new User("Caciotta", "Gustosa", "caciotta.gustosa@gmail.com", "4444", 1888234.0);
		User fungo = new User("Fungo", "Porcino", "fungo.porcino@gmail.com", "1313", 991234.0);
		User cotoletta = new User("Cotoletta", "Dorata", "cotoletta.dorata@gmail.com", "nini", 123254.0);
		User pupazzo = new User("Pupazzo", "DiNeve", "pupazzo.dineve@gmail.com", "2323", 601234.0);

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
