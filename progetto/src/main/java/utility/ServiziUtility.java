package utility;

import java.util.ArrayList;

import entity.dao.ServizioDAO;
import entity.servizi.Servizio;
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
