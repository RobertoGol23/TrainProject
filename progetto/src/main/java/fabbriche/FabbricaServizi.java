package fabbriche;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.servizi.Servizio;


public class FabbricaServizi {

	AbstractApplicationContext contextServizi = new ClassPathXmlApplicationContext("Servizi.xml");	
	
	public Servizio creaBagno() {
		
		Servizio s = (Servizio) contextServizi.getBean("bagno");
		
		return s;
	}
	
	public Servizio creaCinema() {
		
		Servizio s = (Servizio) contextServizi.getBean("cinema");
		
		return s;
	}

	public Servizio creaColore() {
		
		Servizio s = (Servizio) contextServizi.getBean("colore");
		
		return s;
	}
	
	public Servizio creaMenu() {
		
		Servizio s = (Servizio) contextServizi.getBean("menu");
		
		return s;
	}

	public Servizio creaSicurezza() {
	
		Servizio s = (Servizio) contextServizi.getBean("sicurezza");
		
		return s;
	}

	public Servizio creaTemperatura() {
	
		Servizio s = (Servizio) contextServizi.getBean("temperatura");
		
		return s;
	}

	public Servizio creaWifi() {
	
		Servizio s = (Servizio) contextServizi.getBean("wifi");
		
		return s;
	}
}
