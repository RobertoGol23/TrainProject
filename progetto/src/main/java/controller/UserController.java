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
@RequestMapping("/users")
public class UserController {

    /* @Autowired
    private UserDAO userDAO;
 */
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
            return "redirect:/users/login";

    	}
    	else
    	{
    		session.setAttribute("email", user.getEmail());
    		session.setAttribute("nome", user.getNome());
    		session.setAttribute("cognome", user.getCognome());
    		session.setAttribute("errorMessage", "E-mail già utilizzata. <br>Cambia e-mail o fai l'accesso se sei tu!");
    		context.close();
    		return "redirect:/users/register";
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
    
    // Mostra il form per aggiungere fondi
    @GetMapping("/addFunds")
    public String showAddFundsForm(Model model) {
        model.addAttribute("user", new User());
        return "addFunds"; // Nome della vista JSP per aggiungere fondi
    }

    // Gestisce l'aggiunta di fondi al wallet
    @PostMapping("/addFunds")
    public String addFunds(@RequestParam double amount, HttpSession session, Model model) {
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	UserDAO userDAO = context.getBean(UserDAO.class);
        User user = (User) session.getAttribute("user");

        if (user != null) {
            user.setWallet(user.getWallet() + amount); // Aggiungi fondi al wallet

            // Salva l'utente aggiornato nel database
            userDAO.updateUser(user); // Assicurati di avere questo metodo nel tuo DAO
            context.close();
            return "redirect:/users/walletUpdated"; // Reindirizza alla pagina di conferma
        } else {
        	context.close();
            return "redirect:/users/login"; // Reindirizza al login se l'utente non è loggato
        }
    }

    // Pagina di conferma dopo l'aggiornamento del wallet
    @GetMapping("/walletUpdated")
    public String walletUpdated(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "walletUpdated"; // Nome della vista JSP per il wallet aggiornato
        } else {
            return "redirect:/users/login"; // Reindirizza al login se non è loggato
        }
    }
    
 // Mostra il form per modificare il profilo
    @GetMapping("/editProfile")
    public String showEditProfileForm(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            return "redirect:/users/login"; // Se non è loggato, reindirizza al login
        }

        model.addAttribute("user", loggedInUser); // Popola il form con i dati dell'utente
        return "editProfile"; // Nome della vista JSP per modificare il profilo
    }

    // Gestisce l'aggiornamento del profilo utente
    @Transactional
    @PostMapping("/updateProfile")
    public String updateProfile(@RequestParam String nome, 
                                @RequestParam String cognome, 
                                @RequestParam String email, 
                                @RequestParam String password, 
                                HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            return "redirect:/users/login"; // Se non è loggato, reindirizza al login
        }
        
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	UserDAO userDAO = context.getBean(UserDAO.class);
    	
        // Aggiorna i dati dell'utente
        loggedInUser.setNome(nome);
        loggedInUser.setCognome(cognome);
        loggedInUser.setEmail(email);
        loggedInUser.setPassword(password); // Assicurati che la password sia gestita correttamente (hashing)

        // Salva le modifiche nel database
        userDAO.updateUser(loggedInUser);

        
        context.close();
        
        
        // Aggiorna l'utente nella sessione
        session.setAttribute("user", loggedInUser);

        return "redirect:/users/dashboard"; // Reindirizza alla dashboard dopo l'aggiornamento
    }
    
 // Gestisce la cancellazione dell'utente
    @Transactional
    @PostMapping("/deleteUser")
    public String deleteUser(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            return "redirect:/users/login"; // Se non è loggato, reindirizza al login
        }

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	UserDAO userDAO = context.getBean(UserDAO.class);
        
        // Cancella l'utente dal database
        userDAO.deleteUser(loggedInUser);
        
        context.close();

        // Termina la sessione
        session.invalidate();

        return "redirect:/users/accountDeleted"; // Reindirizza alla pagina di conferma
    }

    // Mostra la pagina di conferma cancellazione
    @GetMapping("/accountDeleted")
    public String showAccountDeletedPage() {
        return "accountDeleted"; // Mostra la pagina JSP di conferma
    }
    

}