package controller;

import entity.treno.Treno;
import entity.user.User;
import entity.votazioni.Voto;
import entity.acquisto.Acquisto;
import entity.dao.AcquistoDAO;
import entity.dao.TrenoDAO;
import entity.dao.VotoDAO;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import configuration.JpaConfig;
import eccezioni.eccezioniGeneriche.SoldiNonSufficientiException;

import jakarta.servlet.http.HttpSession; // Importa HttpSession
import java.util.List;

@Controller
public class MarketController {

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

        context.close();
        return "/market/trainMarket"; // Ritorna la JSP
    }
    
    

    // GET per visualizzare la pagina di conferma acquisto
    @GetMapping("/purchaseTrain")
    public String purchaseTrain(@RequestParam("trenoId") Long trenoId, HttpSession session) {

        User utente = (User) session.getAttribute("user");

        if (utente == null) {
            return "redirect:/login"; // Reindirizza alla pagina di login se l'utente non è autenticato
        }

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);

        Treno treno = trenoDAO.getTrenoById(trenoId); // Ottieni i dettagli del treno

        if (treno != null) {
            // Salva il treno selezionato nella sessione
            session.setAttribute("treno", treno);
            context.close();
            return "market/purchaseTrain"; // Visualizza la pagina JSP per conferma acquisto
        } else {
            context.close();
            return "redirect:/trainMarket"; // Se il treno non esiste, torna al market
        }
    }

    // POST per confermare l'acquisto
    @PostMapping("/confirmPurchase")
    public String confirmPurchase(@RequestParam("trenoId") Long trenoId, HttpSession session) throws SoldiNonSufficientiException {
        
        User utente = (User) session.getAttribute("user");

        if (utente == null) {
            return "redirect:/login"; // Reindirizza alla pagina di login se l'utente non è autenticato
        }

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
        AcquistoDAO acquistoDAO = context.getBean(AcquistoDAO.class); 

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
    @GetMapping("/trainDetails")
    public String viewTrain(@RequestParam("trenoId") Long trenoId, Model model, HttpSession session) {
        // Recupera il treno dall'ID

        User utente = (User) session.getAttribute("user");

        if (utente == null) {
            return "redirect:/login"; // Reindirizza alla pagina di login se l'utente non è autenticato
        }

    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
        
        Treno treno = trenoDAO.getTrenoById(trenoId);
        
        if (treno == null) {
            context.close();
            return "redirect:/dashboard/user/viewTrains"; // Se il treno non esiste, reindirizza alla lista dei treni
        }

        // Aggiungi il treno al modello
        model.addAttribute("treno", treno);
        
        context.close();

        return "dashboard/train/viewTrain"; // Nome della vista JSP
    }
    
 // GET per visualizzare la pagina di voto
    @GetMapping("/voteTrain")
    public String showVoteTrain(@RequestParam("trenoId") Long trenoId, HttpSession session) {

        User utente = (User) session.getAttribute("user");

        if (utente == null) {
            return "redirect:/login"; // Reindirizza alla pagina di login se l'utente non è autenticato
        }

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);

        // Ottieni il treno per ID
        Treno treno = trenoDAO.getTrenoById(trenoId);
        if (treno != null) {
            // Salva il treno nella sessione
            session.setAttribute("treno", treno);
            context.close();
            return "market/voteTrain"; // Pagina JSP per votare
        } else {
            context.close();
            return "redirect:/trainMarket"; // Se il treno non esiste, torna al market
        }
    }

    // POST per salvare il voto
    @PostMapping("/submitVote")
    public String submitVote(@RequestParam("trenoId") Long trenoId, @RequestParam("userId") Long userId, 
                             @RequestParam("punteggio") int punteggio, HttpSession session) {

        User utente = (User) session.getAttribute("user");

        if (utente == null) {
            return "redirect:/login"; // Reindirizza alla pagina di login se l'utente non è autenticato
        }
        
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        VotoDAO votoDAO = context.getBean(VotoDAO.class);
        TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);

        // Recupera l'utente e il treno dalla sessione
        User user = (User) session.getAttribute("user");
        Treno treno = trenoDAO.getTrenoById(trenoId);

        if (user != null && treno != null) {
            try {
                // Crea un nuovo voto
                Voto voto = new Voto(punteggio, user, treno);
                votoDAO.salvaVoto(voto);
            } catch (IllegalArgumentException e) {
                // Gestione eccezione se il punteggio è fuori dal range 0-5
                session.setAttribute("errorMessage", "Punteggio non valido, deve essere tra 0 e 5.");
                context.close();
                return "redirect:/voteTrain?trenoId=" + trenoId; // Torna alla pagina di voto
            }

            context.close();
            return "market/voteSuccess"; // Pagina di successo del voto (da creare)
        } else {
            context.close();
            return "redirect:/trainMarket"; // Se l'utente o il treno non esistono, torna al market
        }
    }
}