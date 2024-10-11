package test;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import entity.dao.UserDAO;
import entity.user.User;

public class Test09 {
    public static void main(String[] args) {

		/*				TEST 06
		 * prova sull'utilizzo delle criteria (UserService.java)
         * 
		 */

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);			
			
			
		User mazza = new User("Salvatore","Mazza", "salvatore.mazza@gmail.com", "Danzacudur0_04");
		UserDAO userDAO = context.getBean(UserDAO.class);
		userDAO.salvaUser(mazza);

		User mazza2 = new User("Salvatore","Mazza", "salvatore.mazza@gmail.com", "Danzacudur0_04");
		userDAO = context.getBean(UserDAO.class);
		userDAO.salvaUser(mazza2);
		
			
		userDAO = context.getBean(UserDAO.class);
		List<User> listUser = userDAO.findUserByName("Salvatore");

		System.out.println("UTENTI TROVATI: \n" + listUser);

		context.close();
				
	}

}
