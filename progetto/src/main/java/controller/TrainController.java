package controller;


import java.util.List;

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
import entity.classi_astratte.Vagone;
import entity.dao.ServizioDAO;
import entity.dao.TrenoDAO;
import entity.dao.VagoneDAO;
import entity.servizi.Servizio;
import entity.treno.Treno;
import entity.user.User;
import fabbriche.FabbricaKargoModelz;
import fabbriche.FabbricaRegionalGain;
import fabbriche.FabbricaXFurryFast;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import utility.Assemblatore;

@Controller
@RequestMapping("/dashboard/train")
public class TrainController {
	
    @GetMapping("/createTrain")
    public String mostraCreazioneTreno(HttpServletRequest request, Model model) {

        if ((User) request.getSession().getAttribute("user") == null) {
            return "redirect:/login"; // Reindirizza alla pagina di login se l'utente non è autenticato
        }

        // Qui puoi aggiungere eventuali attributi al modello se necessario
        return "dashboard/train/createTrain"; // Nome della JSP da visualizzare
    }

    @PostMapping("/createTrain")
    public String creaTreno(
            @RequestParam("nomeTreno") String nomeTreno,
            @RequestParam("sigla") String sigla,
            @RequestParam("fabbrica") String fabbricaId,
            HttpServletRequest request) {

        HttpSession session = request.getSession();
        User utente = (User) session.getAttribute("user");

        if (utente == null) {
            return "redirect:/login"; // Reindirizza alla pagina di login se l'utente non è autenticato
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
                return "/createTrain"; // Ritorna alla pagina di creazione del treno
        }

        try {
            // Crea il treno usando il builder
            Assemblatore assemblatore = new Assemblatore(fabbrica);
            Treno nuovoTreno = assemblatore.costruisciTreno(nomeTreno, sigla, utente, Integer.parseInt(fabbricaId));

            if (nuovoTreno != null) {
                // Salva il treno nel database
                AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
                TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
                trenoDAO.salvaTreno(nuovoTreno);  // Salva il treno
                context.close();
                

                request.setAttribute("idTreno", nuovoTreno.getId());
                
                // Reindirizza alla pagina di modifica vagoni con l'ID del treno
                return "redirect:/dashboard/train/modifyWagons?idTreno=" + nuovoTreno.getId();
                //return "dashboard/train/trainSuccess"; // Ritorna alla pagina di successo
            } else {
                request.setAttribute("error", "Errore durante la creazione del treno.");
                return "dashboard/train/createTrain"; // Ritorna alla pagina di creazione del treno
            }

        } catch (SiglaTrenoException e) {
            request.setAttribute("error", "Sigla del treno non valida.");
            return "dashboard/train/createTrain"; // Ritorna alla pagina di creazione del treno
        }
    }

    @GetMapping("/modifyWagons")
    public String mostraModificaVagoni(@RequestParam("idTreno") Long idTreno, Model model) {
        // Recupera il treno dal database
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
        Treno treno = trenoDAO.getTrenoById(idTreno);
        ServizioDAO servizioDAO = context.getBean(ServizioDAO.class);

        if (treno == null) {
            model.addAttribute("errorMessage", "Nessun treno trovato con l'ID specificato.");
            context.close();
            return "dashboard/train/modifyWagons";
        }

        // Aggiungi il treno e i servizi al modello
        model.addAttribute("treno", treno);

        // Recupera i servizi disponibili
        List<Servizio> servizi = servizioDAO.trovaServiziDisponibili();
        model.addAttribute("servizi", servizi);

        context.close();
        return "dashboard/train/modifyWagons"; // Nome della JSP da visualizzare
    }

    @PostMapping("/addService")
    public String aggiungiServizio(
            @RequestParam("vagoneId") Long vagoneId,
            @RequestParam("servizio") String servizio,
            @RequestParam("idTreno") Long idTreno, // Aggiungi anche l'ID del treno qui
            @RequestParam("vagoneIndex") int vagoneIndex, // Recupera l'indice del vagone
            HttpServletRequest request) {

        // Recupera il treno e il vagone dal database
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
       // TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
        VagoneDAO vagoneDAO = context.getBean(VagoneDAO.class);
        ServizioDAO servizioDAO = context.getBean(ServizioDAO.class);

    //    Treno treno = trenoDAO.getTrenoById(idTreno);
        Vagone vagone = vagoneDAO.getVagoneById(vagoneId);
        Servizio s = servizioDAO.getServizioByName(servizio);
        // Aggiungi il servizio al vagone selezionato
        vagone.addServizio(s);      //il prezzo viene modificato in vagone
        //treno.setVagone(vagoneIndex, vagone);
        // Salva le modifiche nel database
        vagoneDAO.updateVagone(vagone);
        servizioDAO.updateServizio(s);
        
        
        context.close();

        request.setAttribute("message", "Servizio aggiunto con successo!");
        return "dashboard/train/addServiceComplete";
    }
}
