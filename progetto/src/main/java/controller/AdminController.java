package controller;

import entity.dao.AcquistoDAO;
import entity.dao.UserDAO;
import entity.user.User;
import entity.acquisto.Acquisto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import configuration.JpaConfig;

import java.util.List;

@Controller
@RequestMapping("/dashboard/admin")
public class AdminController {

    @Autowired
    private AcquistoDAO acquistoDAO; // Servizio per gestire gli acquisti

    @Autowired
    private UserDAO userDAO; // Servizio per gestire gli utenti

    // Mostra la pagina con tutti gli acquisti di tutti gli utenti
    @GetMapping("/showPurchases")
    public String showPurchases(Model model) {
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	AcquistoDAO acquistoDAO = context.getBean(AcquistoDAO.class);
        List<Acquisto> purchases = acquistoDAO.getAllAcquisti();
        model.addAttribute("purchases", purchases);
        return "/dashboard/admin/showPurchases"; // Nome della JSP da visualizzare
    }

    // Mostra la pagina per gestire gli utenti (bloccare/sbloccare)
    @GetMapping("/manageUsers")
    public String manageUsers(Model model) {
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	UserDAO userDAO = context.getBean(UserDAO.class);
        List<User> users = userDAO.getAllUsers();
        model.addAttribute("users", users);
        return "/dashboard/admin/manageUsers"; // Nome della JSP da visualizzare
    }

    // Blocca o sblocca un utente tramite il pulsante
    @PostMapping("/toggleBlockUser")
    public String toggleBlockUser(@RequestParam("userId") Long userId) {
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	UserDAO userDAO = context.getBean(UserDAO.class);
        User user = userDAO.getUserById(userId);
        
        if (user != null) {
            boolean isCurrentlyBlocked = user.isBloccato();
            user.setBloccato(!isCurrentlyBlocked); // Inverte lo stato bloccato/non bloccato
            userDAO.updateUser(user); // Aggiorna lo stato nel database
        }

        return "redirect:/dashboard/admin/manageUsers"; // Torna alla pagina di gestione utenti
    }
}