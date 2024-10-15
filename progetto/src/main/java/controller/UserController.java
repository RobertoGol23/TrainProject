package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import configuration.JpaConfig;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import entity.dao.UserDAO;
import entity.user.User;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDAO userDAO;

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
    public String registerUser(@ModelAttribute("user") User user) {
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	UserDAO userDAO = context.getBean(UserDAO.class);
        userDAO.salvaUser(user);
        context.close();
        return "redirect:/users/login"; // Dopo la registrazione, reindirizza al login
    }
    
    // Mostra il form di login
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Nome della vista JSP per il login
    }

    // Esegue il login
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model/*, RedirectAttributes redirectAttributes*/, HttpSession session) {
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	UserDAO userDAO = context.getBean(UserDAO.class);
    	User user = userDAO.findUserByEmailAndPassword(email, password); // Non è più necessario creare il contesto qui
        
    	context.close();
    	if (user != null) {
            session.setAttribute("user", user); // Imposta l'utente nella sessione
            return "redirect:/users/dashboard"; // Reindirizza a una pagina protetta dopo il login
        } else {
        	//model.addAttribute("errorMessage", "Email o password non validi");
        	session.setAttribute("errorMessage", "Email o password non validi");
        	session.setAttribute("user", email);
        	//redirectAttributes.addFlashAttribute("errorMessage", "Email o password non validi");
            return "redirect:/users/login"; // Torna alla pagina di login
        }
    }
    
    // Mostra la dashboard dopo il login
    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            return "redirect:/login"; // Se non è loggato, reindirizza al login
        }

        model.addAttribute("user", loggedInUser);
        return "dashboard"; // Mostra la dashboard
    }
    
    // Logout e reindirizzamento al login
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Termina la sessione
        return "redirect:/users/login"; // Reindirizza al login
    }
    
    // Endpoint per ottenere tutti gli utenti
    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userDAO.getAllUsers();  // Supponendo che ci sia un metodo getAllUsers nel DAO
        model.addAttribute("users", users);
        return "users";  // Indica il nome della vista
    }

    // Endpoint per ottenere un utente per ID
    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        User user = userDAO.getUserById(id);
        model.addAttribute("user", user);
        return "userDetail";  // Indica la vista dei dettagli di un utente
    }

    // Endpoint per eliminare un utente
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userDAO.eliminaUserById(id);
        return "redirect:/users";
    }

    // Endpoint per mostrare il form di modifica utente
    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable("id") Long id, Model model) {
        User user = userDAO.getUserById(id);
        model.addAttribute("user", user);
        return "editUser";  // Mostra la pagina di modifica dell'utente
    }

    // Endpoint per salvare le modifiche a un utente
    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user) {
        userDAO.updateUser(user);
        return "redirect:/users";
    }
}