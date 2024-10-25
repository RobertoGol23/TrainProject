package utility;

import java.util.ArrayList;

import entity.classi_astratte.Vagone;
import entity.dao.ServizioDAO;
import entity.servizi.Servizio;
import entity.treno.Treno;
import fabbriche.FabbricaServizi;

public class ServiziUtility {

	public ServiziUtility() {
		
	}

	public ArrayList<Servizio> creaListaServizi()
	{
		ArrayList<Servizio> servizi = new ArrayList<Servizio>();
		FabbricaServizi fabbricaServizi = new FabbricaServizi();
		
		servizi.add(fabbricaServizi.creaTemperatura());
		servizi.add(fabbricaServizi.creaWifi());
		servizi.add(fabbricaServizi.creaBagno());
		servizi.add(fabbricaServizi.creaCinema());
		servizi.add(fabbricaServizi.creaColore());
		servizi.add(fabbricaServizi.creaMenu());
		servizi.add(fabbricaServizi.creaSicurezza());
		
		return servizi;
	}

	/**
	 * Metodo che crea un servizio in base al nome passato
	 * @param fabbrica
	 * @param nomeServizio
	 * @return servizio se il nome coincide con un servizio vero o null se non coincide
	 */
	public Servizio creaServizioByNome(FabbricaServizi fabbrica, String nomeServizio)
	{
		Servizio servizio;
		switch(nomeServizio)
		{
			case("bagno"):
			{
				servizio = fabbrica.creaBagno();
				break;
			}
			case("cinema"):
			{
				servizio = fabbrica.creaCinema();
				break;
			}
			case("colore"):
			{
				servizio = fabbrica.creaColore();
				break;
			}
			case("menu"):
			{
				servizio = fabbrica.creaMenu();
				break;
			}
			case("sicurezza"):
			{
				servizio = fabbrica.creaSicurezza();
				break;
			}
			case("temperatura"):
			{
				servizio = fabbrica.creaTemperatura();
				break;
			}
			case("wifi"):
			{
				servizio = fabbrica.creaWifi();
				break;
			}
			default:
			{
				servizio = null;
			}
		}
		return servizio;
	}
	
	
	
	/**
	 * Metodo che cerca un servizio dentro il treno, utilizzato per
	 * aggiungere servizi già utilizzati ad un vagone
	 * @return servizio se trovato, se n
	 */
	public Servizio cercaServizioInTreno(Treno treno, String nomeServizio)
	{
		for(Vagone v:treno.getListaVagoni())
		{
			for(Servizio s:v.getListaServizi())
			{
				if(s.getNome().equalsIgnoreCase(nomeServizio))
				{
					return s;
				}
			}
		}
		return null;
	}
	
	
	/**
	 * Metodo che verifica la presenza di un servizio nel vagone
	 * @param vagone
	 * @param servizio
	 * @return true se è presente, false se non lo è
	 */
	public boolean isServicePresent(Vagone vagone, Servizio servizio)
	{
		for(Servizio s: vagone.getListaServizi())
		{
			if(s.getNome().equalsIgnoreCase(servizio.getNome()))
			{
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Controlla se un servizio in input è adatto ad essere inserito in un vagone cargo
	 * @param servizio
	 * @return boolean in base all'esito dell'operazione
	 */
	public boolean checkServiziCargo(Servizio servizio)
	{
		ArrayList<String> serviziCargo = new ArrayList<String>();
		serviziCargo.add("colore");
		serviziCargo.add("temperatura");
		serviziCargo.add("sicurezza");
		
		for(String s:serviziCargo)
		{
			if(s.equalsIgnoreCase(servizio.getNome()))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Aggiunger servizi al database solo se non sono presenti
	 * @param servizioDAO
	 * @throws Exception
	 */
	public void aggiungiServiziAlDB(ServizioDAO servizioDAO) throws Exception
	{
		ArrayList<Servizio> servizi = creaListaServizi();
		
		for(Servizio servizio: servizi)
		{
			try {
				servizioDAO.salvaServizio(servizio);
			} catch (Exception e) {
				
				if(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException)
				{
		            System.out.println("Servizio duplicato non inserito: " + servizio.getNome());
		        }
				else
				{
		            e.printStackTrace();
		            throw e;
		        }
			}
		}
		
		
		
	}
	
}
