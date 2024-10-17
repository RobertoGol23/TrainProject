package controller;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import configuration.JpaConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import entity.classi_astratte.Vagone;
import entity.dao.UserDAO;
import entity.dao.VagoneDAO;
import entity.treno.Treno;
import entity.user.User;


@Controller
@RequestMapping("/dashboard/user")
public class UserController {
    
    // Mostra la dashboard dopo il login
    @GetMapping("/dashboard")
    public String showDashboard(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
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
        return "redirect:/login"; // Reindirizza al login
    }
      
    @GetMapping("/addFunds")
    public String showAddFundsForm(Model model, HttpSession session) {
        
        // Controlla se l'utente è autenticato
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // Se non è loggato, reindirizza alla pagina di login
            return "redirect:/login";  // Modifica l'URL secondo le tue necessità
        }
        
        // Se l'utente è loggato, aggiungi l'attributo al modello
        model.addAttribute("user", user);
        return "dashboard/user/addFunds"; // Nome della vista JSP per aggiungere fondi
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
            return "/dashboard/user/walletUpdated"; // Reindirizza alla pagina di conferma
        } else {
        	context.close();
            return "redirect:/login"; // Reindirizza al login se l'utente non è loggato
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
            return "redirect:/login"; // Reindirizza al login se non è loggato
        }
    }
    
 // Mostra il form per modificare il profilo
    @GetMapping("/editProfile")
    public String showEditProfileForm(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            return "redirect:/login"; // Se non è loggato, reindirizza al login
        }

        model.addAttribute("user", loggedInUser); // Popola il form con i dati dell'utente
        return "dashboard/user/editProfile"; // Nome della vista JSP per modificare il profilo
    }

    // Gestisce l'aggiornamento del profilo utente
    @Transactional
    @PostMapping("/editProfile")
    public String updateProfile(@RequestParam String nome, 
                                @RequestParam String cognome, 
                                @RequestParam String email, 
                                @RequestParam String password, 
                                HttpSession session) {

        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            return "redirect:/login"; // Se non è loggato, reindirizza al login
        }
        
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	UserDAO userDAO = context.getBean(UserDAO.class);
    	
        // Aggiorna i dati dell'utente
        loggedInUser.setNome(nome);
        loggedInUser.setCognome(cognome);
        loggedInUser.setEmail(email);
        loggedInUser.setPassword(password); //TODO: Assicurati che la password sia gestita correttamente (hashing)

        // Salva le modifiche nel database
        userDAO.updateUser(loggedInUser);

        context.close();
        
        // Aggiorna l'utente nella sessione
        session.setAttribute("user", loggedInUser);

        return "redirect:/dashboard/home"; // Reindirizza alla dashboard dopo l'aggiornamento
    }
    
    // Mostra la pagina di conferma cancellazione
    @GetMapping("/accountDeleted")
    public String showAccountDeletedPage() {
        return "dashboard/user/accountDeleted"; // Mostra la pagina JSP di conferma
    }
    
 // Gestisce la cancellazione dell'utente
    @Transactional
    @PostMapping("/deleteUser")
    public String deleteUser(HttpSession session, @RequestParam String password) {
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            return "redirect:/login"; // Se non è loggato, reindirizza al login
        }

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	UserDAO userDAO = context.getBean(UserDAO.class);
        
        context.close();

        if(loggedInUser.getPassword().equals(password)){
            // Cancella l'utente dal database
            userDAO.deleteUser(loggedInUser);

            // Termina la sessione
            session.invalidate();
            return "dashboard/user/accountDeleted"; // Reindirizza alla pagina di conferma
        }
        else{
            session.setAttribute("errorMessage", "Password non valida");
            return "/dashboard/user/editProfile";
        }
        
        
    }

 // Mostra i treni posseduti dall'utente
    @GetMapping("/viewTrains")
    public String viewUserTrains(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            return "redirect:/login"; // Se non è loggato, reindirizza al login
        }

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        UserDAO userDAO = context.getBean(UserDAO.class);
        VagoneDAO vagoneDAO = context.getBean(VagoneDAO.class);

        // Ottieni i treni dell'utente
        List<Treno> userTrains = userDAO.getTrenoByUserId(loggedInUser.getId_user());

        // Genera l'HTML per l'accordion
        StringBuilder accordionHtml = new StringBuilder();

        for (Treno train : userTrains) {
            accordionHtml.append("<button class=\"accordion\">")
                    .append(train.getNome())
                    .append(train.getPesoTotaleTreno())
                    .append(train.getPrezzoTotaleTreno())
                    .append("</button>");
            
            // Recupera i vagoni per il treno
            List<Vagone> vagoni = train.getListaVagoni(); // Supponendo tu abbia un metodo per recuperare i vagoni
            accordionHtml.append("<div class=\"panel\"><table><thead><tr>")
                    .append("<th>Vagone</th>")
                    .append("<th>Peso (ton)</th>")
                    .append("<th>Prezzo (€)</th>")
                    .append("</tr></thead><tbody>");

            for (Vagone v : vagoni) {
                accordionHtml.append("<tr>")
                        .append("<td>").append(v.getTipo()).append("</td>")
                        .append("<td>").append(v.getPeso()).append("</td>")
                        .append("<td>").append(v.getPrezzo()).append("</td>")
                        .append("</tr>");
            }
            accordionHtml.append("</tbody></table></div>"); // Chiude il div del pannello
        }

        // Aggiungi l'HTML dell'accordion al modello
        model.addAttribute("trainsTable", accordionHtml.toString());

        context.close();

        return "dashboard/user/viewTrains"; // Nome della vista JSP per mostrare i treni
    }
}