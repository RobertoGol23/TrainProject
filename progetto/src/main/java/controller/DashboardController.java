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
@RequestMapping("/dashboard")
public class DashboardController {

    // Mostra la dashboard dopo il login
    @GetMapping("/home")
    public String showDashboard(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            return "redirect:/login"; // Se non è loggato, reindirizza al login
        }

        model.addAttribute("user", loggedInUser);
        return "dashboard/home"; // Mostra la dashboard
    }
}