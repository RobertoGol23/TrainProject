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
