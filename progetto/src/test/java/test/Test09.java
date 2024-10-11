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
			
			
		User mano = new User("Salvo","Mano", "salvo.mano@gmail.com", "Danzacudur0_04", 0.0);
		UserDAO userDAO = context.getBean(UserDAO.class);
		userDAO.salvaUser(mano);

		User fungo = new User("Fungo", "Porcino", "fungo.porcino@gmail.com", "1313", 991234.0);
		userDAO = context.getBean(UserDAO.class);
		userDAO.salvaUser(fungo);
		
			
		userDAO = context.getBean(UserDAO.class);
		List<User> listUser = userDAO.findUserByName("Salvatore");

		System.out.println("UTENTI TROVATI: \n" + listUser);

		context.close();
				
	}

}
