package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import entity.user.User;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    // Mostra la dashboard dopo il login
    @GetMapping("/home")
    public String showDashboard(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");
        
        if (loggedInUser == null) {
            return "redirect:/login"; // Se non è loggato, reindirizza al login
        }

        model.addAttribute("user", loggedInUser);
        return "dashboard/home"; // Mostra la dashboard
    }


}
