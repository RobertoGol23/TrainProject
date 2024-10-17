package controller;

import entity.treno.Treno;
import entity.user.User;
import entity.acquisto.Acquisto;
import entity.dao.AcquistoDAO;
import entity.dao.TrenoDAO;
import entity.dao.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import configuration.JpaConfig;
import eccezioni.eccezioniGeneriche.SoldiNonSufficientiException;

import jakarta.servlet.http.HttpSession; // Importa HttpSession
import java.util.List;

@Controller
public class MarketController {

    @Autowired
    private TrenoDAO trenoDAO; // DAO per recuperare i dati sui treni

    private static final int TRENTS_PER_PAGE = 10; // Numero di treni per pagina

    // GET per visualizzare il market dei treni
    @GetMapping("/trainMarket")
    public String showTrainMarket(@RequestParam(value = "page", defaultValue = "1") int page, HttpSession session) {
        
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);

        // Calcola il numero totale di treni e pagine
        int totalTreni = trenoDAO.getAllTrain().size();
        int totalPages = (int) Math.ceil((double) totalTreni / TRENTS_PER_PAGE);

        // Recupera la lista di treni per la pagina corrente
        List<Treno> treni = trenoDAO.getTreniPaginated((page - 1) * TRENTS_PER_PAGE, TRENTS_PER_PAGE);

        // Salva i dati nella sessione
        session.setAttribute("treni", treni);
        session.setAttribute("currentPage", page);
        session.setAttribute("totalPages", totalPages);

        return "/market/trainMarket"; // Ritorna la JSP
    }
    
    

    // GET per visualizzare la pagina di conferma acquisto
    @GetMapping("/purchaseTrain")
    public String purchaseTrain(@RequestParam("trenoId") Long trenoId, HttpSession session) {
        
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);

        Treno treno = trenoDAO.getTrenoById(trenoId); // Ottieni i dettagli del treno

        if (treno != null) {
            // Salva il treno selezionato nella sessione
            session.setAttribute("treno", treno);
            return "market/purchaseTrain"; // Visualizza la pagina JSP per conferma acquisto
        } else {
            return "redirect:/trainMarket"; // Se il treno non esiste, torna al market
        }
    }

    // POST per confermare l'acquisto
    @PostMapping("/confirmPurchase")
    public String confirmPurchase(@RequestParam("trenoId") Long trenoId, HttpSession session) throws SoldiNonSufficientiException {
        
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
        AcquistoDAO acquistoDAO = context.getBean(AcquistoDAO.class); 
        User utente = (User) session.getAttribute("user");

        Treno treno = trenoDAO.getTrenoById(trenoId);
        
        if (treno != null) {
        	try
        	{
        		// Esegui la logica dell'acquisto (ad esempio, aggiorna lo stato del treno)
        		Acquisto acquisto = new Acquisto(utente,treno);
            
        		acquistoDAO.salvaAcquisto(acquisto);
            
        		context.close();
        		
        	}
        	catch(SoldiNonSufficientiException e)
        	{
        		System.out.println(e.getMessage()+e.getSuggerimento());
        	}

        }
        return "market/purchaseSuccess"; // Dopo l'acquisto, torna al market
    }
}