package controller;

import entity.dao.AcquistoDAO;
import entity.dao.UserDAO;
import entity.user.User;
import entity.acquisto.Acquisto;

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

    // Mostra la pagina con tutti gli acquisti di tutti gli utenti
    @GetMapping("/showPurchases")
    public String showPurchases(Model model) {
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	AcquistoDAO acquistoDAO = context.getBean(AcquistoDAO.class);
        List<Acquisto> purchases = acquistoDAO.getAllAcquisti();
        model.addAttribute("purchases", purchases);
        context.close();
        return "/dashboard/admin/showPurchases"; // Nome della JSP da visualizzare
    }

    // Mostra la pagina per gestire gli utenti (bloccare/sbloccare)
    @GetMapping("/manageUsers")
    public String manageUsers(
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "userIdSearch", required = false) Long userIdSearch,
        Model model) {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        UserDAO userDAO = context.getBean(UserDAO.class);

        List<User> users;
        int totalPages = 1;
        int currentPage = 1;

        if (userIdSearch != null) {
            User user = userDAO.getUserById(userIdSearch);
            users = (user != null) ? List.of(user) : List.of();
        } else {
            int pageSize = 10;
            users = userDAO.getUsersByPage(page, pageSize);
            int totalUsers = userDAO.getTotalUsers();
            totalPages = (int) Math.ceil((double) totalUsers / pageSize);
            currentPage = page;
        }

        model.addAttribute("users", users);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        
        context.close();
        return "/dashboard/admin/manageUsers";
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
        context.close();
        return "redirect:/dashboard/admin/manageUsers"; // Torna alla pagina di gestione utenti
    }
}