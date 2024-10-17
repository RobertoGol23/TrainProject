package controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import configuration.JpaConfig;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import entity.dao.UserDAO;
import entity.user.User;


@Controller
public class GenericController {

    
    // Mostra il form di registrazione
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("formAction", "/register");
        return "register"; // Nome della vista JSP
    }
    
    // Salva un nuovo utente registrato
    @Transactional
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, HttpSession session) {
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	UserDAO userDAO = context.getBean(UserDAO.class);
    	
    	if(userDAO.getUserByEmail(user.getEmail())==null)
    	{
    		userDAO.salvaUser(user);
    		context.close();
            return "redirect:/login";
    	}
    	else
    	{
    		session.setAttribute("email", user.getEmail());
    		session.setAttribute("nome", user.getNome());
    		session.setAttribute("cognome", user.getCognome());
    		session.setAttribute("errorMessage", "E-mail già utilizzata. <br>Cambia e-mail o fai l'accesso se sei tu!");
    		context.close();
    		return "redirect:/register";
    	}
    }
    
 // Mostra il form di login
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Nome della vista JSP per il login
    }

    // Esegue il login
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	UserDAO userDAO = context.getBean(UserDAO.class);
    	//User user = userDAO.findUserByEmailAndPassword(email, password); // Non è più necessario creare il contesto qui

    	
    	
    	if(userDAO.getUserByEmail(email)!=null)
    	{
    		User user = userDAO.getUserByEmail(email);
    		session.setAttribute("user", user); // Imposta l'utente nella sessione
    		context.close();
            return "redirect:/dashboard/home"; // Reindirizza a una pagina protetta dopo il login
        }
    	else
        {
        	session.setAttribute("errorMessage", "Email o password non validi");
        	session.setAttribute("user", email);
        	context.close();
            return "redirect:/login"; // Torna alla pagina di login
        }
    }
    
    

}