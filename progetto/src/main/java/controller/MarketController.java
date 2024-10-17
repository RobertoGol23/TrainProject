package controller;

import entity.treno.Treno;
import entity.dao.TrenoDAO;
import entity.dao.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import configuration.JpaConfig;

import java.util.List;

@Controller
public class MarketController {

    @Autowired
    private TrenoDAO trenoDAO; // DAO per recuperare i dati sui treni

    private static final int TRENTS_PER_PAGE = 10; // Numero di treni per pagina

    @GetMapping("/trainMarket")
    public String showTrainMarket(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        
    	 AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
         TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
    	// Calcola l'inizio e la fine dei treni da mostrare
        int totalTreni = trenoDAO.getAllTrain().size(); // Metodo per ottenere il numero totale di treni
        int totalPages = (int) Math.ceil((double) totalTreni / TRENTS_PER_PAGE); // Calcola il numero totale di pagine

        // Recupera la lista di treni per la pagina corrente
        List<Treno> treni = trenoDAO.getTreniPaginated((page - 1) * TRENTS_PER_PAGE, TRENTS_PER_PAGE);

        model.addAttribute("treni", treni);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "/market/trainMarket"; // Ritorna la JSP
    }
}