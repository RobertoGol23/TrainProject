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
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class MarketController {

    private static final int TRENTS_PER_PAGE = 12; // Numero di treni per pagina

    // GET per visualizzare il market dei treni
    @GetMapping("/trainMarket")
    public String showTrainMarket(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "searchQuery", required = false) String searchQuery,
            @RequestParam(value = "ordinamento", required = false) String ordinamento,
            @RequestParam(value = "peso-min", required = false) Double pesoMin,
            @RequestParam(value = "peso-max", required = false) Double pesoMax,
            @RequestParam(value = "lunghezza-min", required = false) Integer lunghezzaMin,
            @RequestParam(value = "lunghezza-max", required = false) Integer lunghezzaMax,
            @RequestParam(value = "prezzo-min", required = false) Double prezzoMin,
            @RequestParam(value = "prezzo-max", required = false) Double prezzoMax,
            @RequestParam(value = "versoOrdinamento", required = false) Boolean versoOrdinamento,
            HttpSession session) {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);

        List<Treno> treni;

        // Determina ordinamento di default per il primo accesso
        String ordinamento_2 = (ordinamento != null) ? ordinamento : (String) session.getAttribute("ordinamento");
        Double pesoMin_2 = (pesoMin != null) ? pesoMin : (Double) session.getAttribute("pesoMin");
        Double pesoMax_2 = (pesoMax != null) ? pesoMax : (Double) session.getAttribute("pesoMax");
        Integer lunghezzaMin_2 = (lunghezzaMin != null) ? lunghezzaMin : (Integer) session.getAttribute("lunghezzaMin");
        Integer lunghezzaMax_2 = (lunghezzaMax != null) ? lunghezzaMax : (Integer) session.getAttribute("lunghezzaMax");
        Double prezzoMin_2 = (prezzoMin != null) ? prezzoMin : (Double) session.getAttribute("prezzoMin");
        Double prezzoMax_2 = (prezzoMax != null) ? prezzoMax : (Double) session.getAttribute("prezzoMax");
        Boolean versoOrdinamento_2 = (versoOrdinamento != null) ? versoOrdinamento : (Boolean) session.getAttribute("versoOrdinamento");

         // Se i parametri sono null, imposta i valori di default
         if (ordinamento_2 == null) ordinamento_2 = "";
         if (pesoMin_2 == null) pesoMin_2 = 0.0;
         if (pesoMax_2 == null) pesoMax_2 = Double.MAX_VALUE;
         if (lunghezzaMin_2 == null) lunghezzaMin_2 = 0;
         if (lunghezzaMax_2 == null) lunghezzaMax_2 = Integer.MAX_VALUE;
         if (prezzoMin_2 == null) prezzoMin_2 = 0.0;
         if (prezzoMax_2 == null) prezzoMax_2 = Double.MAX_VALUE;
         if (versoOrdinamento_2 == null) versoOrdinamento_2 = true;

        if (searchQuery != null && !searchQuery.isEmpty()) {
            // Esegui solo la ricerca per ID, nome o sigla se searchQuery è presente
            treni = trenoDAO.findByQuery(searchQuery);
            session.setAttribute("versoOrdinamento", versoOrdinamento_2);
        } 
        else {
            treni = trenoDAO.cercaTreni(ordinamento_2, pesoMin_2, pesoMax_2, lunghezzaMin_2, lunghezzaMax_2, prezzoMin_2, prezzoMax_2, versoOrdinamento_2);

            // Salva i parametri di ricerca nella sessione per le future richieste
            session.setAttribute("ordinamento", ordinamento_2);
            session.setAttribute("pesoMin", pesoMin_2);
            session.setAttribute("pesoMax", pesoMax_2);
            session.setAttribute("lunghezzaMin", lunghezzaMin_2);
            session.setAttribute("lunghezzaMax", lunghezzaMax_2);
            session.setAttribute("prezzoMin", prezzoMin_2);
            session.setAttribute("prezzoMax", prezzoMax_2);
            session.setAttribute("versoOrdinamento", versoOrdinamento_2);
        }

        int totalTreni = treni.size();
        int totalPages = (int) Math.ceil((double) totalTreni / TRENTS_PER_PAGE);

        List<Treno> treniPaginati = treni.stream().skip((page - 1) * TRENTS_PER_PAGE).limit(TRENTS_PER_PAGE).collect(Collectors.toList());

        session.setAttribute("treni", treniPaginati);
        session.setAttribute("currentPage", page);
        session.setAttribute("totalPages", totalPages);

        context.close();
        return "market/trainMarket";
    }

    

    @PostMapping("/trainMarket")
    public String searchAndShowTrainMarket(
            @RequestParam("ordinamento") Optional<String> ordinamento,
            @RequestParam("peso-min") Optional<Double> pesoMin,
            @RequestParam("peso-max") Optional<Double> pesoMax,
            @RequestParam("lunghezza-min") Optional<Integer> lunghezzaMin,
            @RequestParam("lunghezza-max") Optional<Integer> lunghezzaMax,
            @RequestParam("prezzo-min") Optional<Double> prezzoMin,
            @RequestParam("prezzo-max") Optional<Double> prezzoMax,
            @RequestParam("versoOrdinamento") Boolean versoOrdinamento,
            HttpSession session) {
        

        // Save search parameters in the session
        session.setAttribute("ordinamento", ordinamento.orElse(""));
        session.setAttribute("pesoMin", pesoMin.orElse(0.0));
        session.setAttribute("pesoMax", pesoMax.orElse(Double.MAX_VALUE));
        session.setAttribute("lunghezzaMin", lunghezzaMin.orElse(0));
        session.setAttribute("lunghezzaMax", lunghezzaMax.orElse(Integer.MAX_VALUE));
        session.setAttribute("prezzoMin", prezzoMin.orElse(0.0));
        session.setAttribute("prezzoMax", prezzoMax.orElse(Double.MAX_VALUE));
        session.setAttribute("versoOrdinamento", versoOrdinamento);

        // Redirect to the market page with page 1
        return "redirect:/trainMarket?page=1";
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
    public String confirmPurchase(@RequestParam("trenoId") Long trenoId, HttpSession session, Model model) throws SoldiNonSufficientiException {
        
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
        		e.printStackTrace();
        		model.addAttribute("error", e.getErrorePerUtente());
        		System.out.println("errore: "+ e);
        		return "market/purchaseFail";
        	}

        }
        return "market/purchaseSuccess"; // Dopo l'acquisto, torna al market
    }
    
    @GetMapping("/trainDetails")
    public String viewTrain(@RequestParam("trenoId") Long trenoId, Model model, HttpSession session) {
        // Recupera il treno dall'ID

    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
        
        Treno treno = trenoDAO.getTrenoById(trenoId);
        
        if (treno == null) {
            context.close();
            return "market/trainDetails"; // Se il treno non esiste, reindirizza alla lista dei treni
        }

        // Aggiungi il treno al modello
        model.addAttribute("treno", treno);
        
        context.close();

        return "market/trainDetails"; // Nome della vista JSP
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

        // Recupera l'utente e il treno dalla sessione o dal database
        User user = (User) session.getAttribute("user");
        Treno treno = trenoDAO.getTrenoById(trenoId);

        if (user != null && treno != null) {
        	
        	if(treno.getUtente().getId_user() == user.getId_user()) {
           	 // Se stai votando un treno creato da te, reindirizza alla pagina di errore
               session.setAttribute("errorMessage", "Non puoi votare il tuo treno.");
               context.close();
               return "market/voteFailure"; // Pagina di errore
        	}
        	
            // Controlla se l'utente ha già votato questo treno
            Voto votoEsistente = votoDAO.trovaVotoPerUtenteETreno(user.getId_user(), treno.getId());

            if (votoEsistente != null) {
                // Se esiste già un voto, reindirizza alla pagina di errore
                session.setAttribute("errorMessage", "Hai già votato questo treno.");
                context.close();
                return "market/voteFailure"; // Pagina di errore 
            }

            try {
                // Se non esiste un voto, salva il nuovo voto
                Voto voto = new Voto(punteggio, user, treno);
                votoDAO.salvaVoto(voto);
            } catch (IllegalArgumentException e) {
                // Gestione eccezione se il punteggio è fuori dal range 0-5
                session.setAttribute("errorMessage", "Punteggio non valido, deve essere tra 0 e 5.");
                context.close();
                return "redirect:/voteTrain?trenoId=" + trenoId; // Torna alla pagina di voto
            }

            context.close();
            return "market/voteSuccess"; // Pagina di successo del voto 
        } else {
            context.close();
            return "redirect:/trainMarket"; // Se l'utente o il treno non esistono, torna al market
        }
    }

}