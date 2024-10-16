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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import utility.Assemblatore;
import utility.ServiziUtility;
import utility.TrenoUtility;

@Controller
@RequestMapping("/dashboard/train")
public class TrainController {
	
	@GetMapping("/creaTrenoProva")
    public String mostraCreazioneTrenoProva(HttpServletRequest request, Model model) {

        if ((User) request.getSession().getAttribute("user") == null) {
            return "redirect:/login"; // Reindirizza alla pagina di login se l'utente non è autenticato
        }

        return "dashboard/train/creaTrenoProva"; // Nome della JSP da visualizzare
    }
	
	@PostMapping("/creaTrenoProva")
	public String creaTreno(@RequestParam("wagons[]") List<String> vagoni, Model model, HttpServletRequest request) {
	    
		String stringFabbricaId = "1"; //arriva come parametro
		int fabbricaId = Integer.parseInt(stringFabbricaId);
		
		String sigla = "";
		for(String v: vagoni)
		{
			sigla += v;
		}
		
		HttpSession session = request.getSession();
        User utente = (User) session.getAttribute("user");

        if (utente == null) {
            return "redirect:/login"; // Reindirizza alla pagina di login se l'utente non è autenticato
        }
        
        TrenoUtility tu = new TrenoUtility();
        FabbricaVagoni fabbrica = tu.getFabbricaById(1/*fabbricaId*/);
        
        System.out.println("Sigla dal sito: " + sigla);
        
        try {
            // Crea il treno usando il builder
            Assemblatore assemblatore = new Assemblatore(fabbrica);
            Treno nuovoTreno = assemblatore.costruisciTreno("Nome treno", sigla, utente, fabbricaId);
   
            
            if (nuovoTreno != null) {
                // Salva il treno nel database
                AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
                TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
                trenoDAO.salvaTreno(nuovoTreno);  // Salva il treno
                
                
                ServizioDAO servizioDAO = context.getBean(ServizioDAO.class);
        		ServiziUtility su = new ServiziUtility();
        		su.aggiungiServiziAlDB(servizioDAO);
        		
        		context.close();
                request.setAttribute("idTreno", nuovoTreno.getId());
                
                // Reindirizza alla pagina di modifica vagoni con l'ID del treno
                return "redirect:/dashboard/train/modifyWagons?idTreno=" + nuovoTreno.getId();
                //return "dashboard/train/trainSuccess"; // Ritorna alla pagina di successo
            } else {
                request.setAttribute("error", "Errore durante la creazione del treno.");
                System.out.println("Errore generico creazione treno");
                return "dashboard/train/creaTrenoProva"; // Ritorna alla pagina di creazione del treno
            }

        } catch (SiglaTrenoException e) {
            request.setAttribute("error", "Sigla del treno non valida.");
            System.out.println("Errore sigla");
            return "dashboard/train/creaTrenoProva"; // Ritorna alla pagina di creazione del treno
        }
        catch (Exception e) {
        	System.out.println(e.getMessage());
        	System.out.println("Errore generico");
        	request.setAttribute("error", "Si è verificato un errore, riprova.");
        	return "dashboard/train/modifyWagons";
		}
	}
	
	
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

        TrenoUtility tu = new TrenoUtility();
        FabbricaVagoni fabbrica = tu.getFabbricaById(Integer.parseInt(fabbricaId));


        try {
            // Crea il treno usando il builder
            Assemblatore assemblatore = new Assemblatore(fabbrica);
            Treno nuovoTreno = assemblatore.costruisciTreno(nomeTreno, sigla, utente, Integer.parseInt(fabbricaId));
   
            
            if (nuovoTreno != null) {
                // Salva il treno nel database
                AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
                TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
                trenoDAO.salvaTreno(nuovoTreno);  // Salva il treno
                
                
                ServizioDAO servizioDAO = context.getBean(ServizioDAO.class);
        		ServiziUtility su = new ServiziUtility();
        		su.aggiungiServiziAlDB(servizioDAO);
        		
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
        catch (Exception e) {
        	System.out.println(e.getMessage());
        	request.setAttribute("error", "Si è verificato un errore, riprova.");
        	return "dashboard/train/modifyWagons";
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
    
    @GetMapping("/viewTrain")
    public String viewTrain(@RequestParam("trenoId") Long trenoId, Model model) {
        // Recupera il treno dall'ID
    	
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        TrenoDAO trenoDAO = context.getBean(TrenoDAO.class);
        
        Treno treno = trenoDAO.getTrenoById(trenoId);
        
        if (treno == null) {
            context.close();;
            return "redirect:/dashboard/user/viewTrains"; // Se il treno non esiste, reindirizza alla lista dei treni
        }

        // Aggiungi il treno al modello
        model.addAttribute("treno", treno);
        
        context.close();

        return "dashboard/train/viewTrain"; // Nome della vista JSP
    }
}
