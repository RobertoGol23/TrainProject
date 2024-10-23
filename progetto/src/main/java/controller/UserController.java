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
import entity.acquisto.Acquisto;
import entity.dao.AcquistoDAO;
import entity.dao.UserDAO;
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


    @GetMapping("/ricarica")
    public String showAddFundsForm(Model model, HttpSession session) {
        
        // Controlla se l'utente è autenticato
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // Se non è loggato, reindirizza alla pagina di login
            return "redirect:/login";  // Modifica l'URL secondo le tue necessità
        }
        
        // Se l'utente è loggato, aggiungi l'attributo al modello
        model.addAttribute("user", user);
        return "dashboard/user/ricarica"; // Nome della vista JSP per aggiungere fondi
    }

    // Gestisce l'aggiunta di fondi al wallet
    @PostMapping("/ricarica")
    public String addFunds(@RequestParam double amount, HttpSession session, Model model) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        UserDAO userDAO = context.getBean(UserDAO.class);
        User user = (User) session.getAttribute("user");

        if (user != null) {
            user.setWallet(user.getWallet() + amount); // Aggiungi fondi al wallet

            // Salva l'utente aggiornato nel database
            userDAO.updateUser(user);

            // Chiudi il contesto di Spring
            context.close();

            // Imposta l'utente nella sessione
            session.setAttribute("user", user);

            // Reindirizza alla pagina di conferma
            return "redirect:/dashboard/user/ricaricaConfirmed";
        } else {
            context.close();
            return "redirect:/login"; // Reindirizza al login se l'utente non è loggato
        }
    }


    // Pagina di conferma dopo l'aggiornamento del wallet
    @GetMapping("/ricaricaConfirmed")
    public String ricaricaConfirmed(HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            return "dashboard/user/ricaricaConfirmed"; // Nome della vista JSP per il wallet aggiornato
        } else {
            return "redirect:/login"; // Reindirizza al login se non è loggato
        }
    }


    // Mostra la pagina del wallet
    @GetMapping("/wallet")
    public String showWallet(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            return "redirect:/login"; // Se non è loggato, reindirizza al login
        }

        model.addAttribute("user", loggedInUser);
        return "dashboard/user/wallet"; // Mostra la dashboard
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
                                @RequestParam String passwordConfirm, 
                                HttpSession session, Model model) {

        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
        	System.out.println("Errore loggedInUser null.\nReindirizzamento al login...");
            return "redirect:/login"; // Se non è loggato, reindirizza al login
        }
        
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	UserDAO userDAO = context.getBean(UserDAO.class);

    	
    	if(loggedInUser.getPassword().equals(passwordConfirm)){
    		// Aggiorna i dati dell'utente
            loggedInUser.setNome(nome);
            loggedInUser.setCognome(cognome);
            loggedInUser.setEmail(email);
            loggedInUser.setPassword(password);

            // Salva le modifiche nel database
            userDAO.updateUser(loggedInUser);
            context.close();
            // Aggiorna l'utente nella sessione
            session.setAttribute("user", loggedInUser);

            return "redirect:/dashboard/home"; // Reindirizza alla dashboard dopo l'aggiornamento
        }
        else{
        	model.addAttribute("errorMessage", "Password non valida");
            context.close();
            System.out.println("Errore, le password non coincidono.\nReindirizzamento al login...");
            return "/dashboard/user/editProfile";
        }   
    }
    
    // Mostra la pagina di conferma cancellazione
    @GetMapping("/accountDeleted")
    public String showAccountDeletedPage() {
        return "dashboard/user/accountDeleted"; // Mostra la pagina JSP di conferma
    }
    
 // Gestisce la cancellazione dell'utente
    @Transactional
    @GetMapping("/deleteUser")
    public String controllaPasswordRimuoviUtente(HttpSession session, @RequestParam("password") String password, Model model) {
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            return "redirect:/login"; // Se non è loggato, reindirizza al login
        }

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	UserDAO userDAO = context.getBean(UserDAO.class);
        
        

        if(loggedInUser.getPassword().equals(password)){
            // Cancella l'utente dal database
            userDAO.deleteUser(loggedInUser);
            context.close();
            // Termina la sessione
            session.invalidate();
            return "dashboard/user/accountDeleted"; // Reindirizza alla pagina di conferma
        }
        else{
            model.addAttribute("errorMessage", "Password non valida");
            context.close();
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

        // Ottieni i treni dell'utente
        List<Treno> userTrains = userDAO.getTrenoByUserId(loggedInUser.getId_user());

        // Genera l'HTML per la tabella
        StringBuilder tableHtml = new StringBuilder();

        tableHtml.append("<table><thead><tr>")
                .append("<th>Nome Treno</th>")
                .append("<th>Peso Totale (ton)</th>")
                .append("<th>Prezzo Totale (€)</th>")
                .append("<th>Dettagli</th>") // Colonna per i pulsanti
                .append("</tr></thead><tbody>");

        for (Treno train : userTrains) {
            tableHtml.append("<tr>")
                    .append("<td text-align='center'>").append(train.getNome()).append("</td>")
                    .append("<td>").append(train.getPesoTotaleTreno()).append("</td>")
                    .append("<td>").append(train.getPrezzoTotaleTreno()).append("</td>")
                    .append("<td>")
                    .append("<form action='/train-bazaar/dashboard/train/viewTrain' method='get'>") // Modulo con pulsante
                    .append("<input type='hidden' name='idTreno' value='").append(train.getId()).append("' />")
                    .append("<button type='submit' style='margin: 0px'>Visualizza</button>")
                    .append("</form>")
                    .append("</td>")
                    .append("</tr>");
        }

        tableHtml.append("</tbody></table>");

        // Aggiungi l'HTML della tabella al modello
        model.addAttribute("trainsTable", tableHtml.toString());

        context.close();

        return "dashboard/user/viewTrains"; // Nome della vista JSP per mostrare i treni
    }
    
 // Aggiungi questo metodo nel tuo UserController
    @GetMapping("/viewPurchasedTrains")
    public String viewPurchasedTrains(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            return "redirect:/login"; // Se non è loggato, reindirizza al login
        }

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        AcquistoDAO acquistoDAO = context.getBean(AcquistoDAO.class);
        
        // Ottieni la lista degli acquisti per l'utente
        List<Acquisto> acquisti = acquistoDAO.getAcquistiByUserId(loggedInUser.getId_user());

        // Aggiungi la lista degli acquisti al modello
        model.addAttribute("acquisti", acquisti);

        context.close();

        return "dashboard/user/viewPurchasedTrains"; // Nome della vista JSP per mostrare i treni acquistati
    }

}