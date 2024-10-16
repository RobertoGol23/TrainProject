package controller;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import configuration.JpaConfig;
import eccezioni.eccezioniSigla.SiglaTrenoException;
import entity.classi_astratte.FabbricaVagoni;
import entity.dao.TrenoDAO;
import entity.treno.Treno;
import entity.user.User;
import fabbriche.FabbricaKargoModelz;
import fabbriche.FabbricaRegionalGain;
import fabbriche.FabbricaXFurryFast;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utility.Assemblatore;

@Controller
@RequestMapping("/createTrain")
public class CreateTrainController {

    @GetMapping
    public String mostraCreazioneTreno(HttpServletRequest request, Model model) {
        // Qui puoi aggiungere eventuali attributi al modello se necessario
        return "createTrain"; // Nome della JSP da visualizzare
    }

    @PostMapping
    public String creaTreno(
            @RequestParam("nomeTreno") String nomeTreno,
            @RequestParam("sigla") String sigla,
            @RequestParam("fabbrica") String fabbricaId,
            HttpServletRequest request,
            HttpServletResponse response) {
    	
        // Ottieni l'utente dalla sessione
        HttpSession session = request.getSession();
        User utente = (User) session.getAttribute("user");

        if (utente == null) {
            return "redirect:login"; // Reindirizza alla pagina di login se l'utente non è autenticato
        }

        // Seleziona la fabbrica corretta in base al valore scelto dall'utente
        FabbricaVagoni fabbrica = null;
        switch (fabbricaId) {
            case "1":
                fabbrica = new FabbricaXFurryFast();
                break;
            case "2":
                fabbrica = new FabbricaKargoModelz();
                break;
            case "3":
                fabbrica = new FabbricaRegionalGain();
                break;
            default:
                request.setAttribute("error", "Fabbrica non valida.");
                return "createTrain"; // Ritorna alla pagina di creazione del treno
        }

        try {
            // Crea il treno usando il builder
            Assemblatore assemblatore = new Assemblatore(fabbrica);
            Treno nuovoTreno = assemblatore.costruisciTreno(nomeTreno, sigla, utente, Integer.parseInt(fabbricaId));
            AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        	TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
            
            // Se il treno è stato creato correttamente, reindirizza alla pagina di successo
            if (nuovoTreno != null) {
            	trenoDAO.salvaTreno(nuovoTreno);
        		context.close();
                request.setAttribute("message", "Treno creato con successo!");
                return "trainSuccess"; // Ritorna alla pagina di successo
            } else {
                request.setAttribute("error", "Errore durante la creazione del treno.");
                context.close();
                return "createTrain"; // Ritorna alla pagina di creazione del treno
            }
        } catch (SiglaTrenoException e) {
            request.setAttribute("error", "Sigla del treno non valida.");
            return "createTrain"; // Ritorna alla pagina di creazione del treno
        }
    }
}
