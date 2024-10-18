package test.testUser;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import entity.dao.AdminDAO;
import entity.dao.UserDAO;
import entity.user.Admin;
import entity.user.User;

public class TestAdmin {
	

	/*				TEST Admin
	 * caricamento nel db di un admin
	 * bloccaggio di un utente
	 * 
     * 
     * 
	 * 
     * 
	 */

	public static void main(String[] args) {
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
		
		
		Admin boss = new Admin("Gucci", "Gang", "gang.gucci@gmail.com", "1234");
		AdminDAO adminDAO = context.getBean(AdminDAO.class);
		boss.setSuperAdmin(true);
		adminDAO.salvaAdmin(boss);
		
		
		User sasso = new User("sasso","frasso", "sasso.frasso@gmail.com", "1234", 0.0);
		UserDAO userDAO = context.getBean(UserDAO.class);
		userDAO.salvaUser(sasso);
		
		boss.bloccaUtente(sasso);
		
		userDAO.updateUser(sasso);
		
	}

}
