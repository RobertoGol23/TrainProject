package entity.classi_astratte.vagoni_astratti;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import entity.servizi.Servizio;
import entity.treno.Treno;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vagone { //pensare se si puo' levare abstract per il dao

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)    
	@Column(name = "id_vagone")
	private Long id_vagone;
	
	@Column(name = "peso", nullable = false, unique = false)
	private Double peso;
	
	@Column(name = "prezzo", nullable = false, unique = false)
	private Double prezzo;

	@ManyToMany(mappedBy = "listaVagoniS", fetch = FetchType.EAGER) // Indica che la relazione è gestita dall'altra entità (Servizio)
    private List<Servizio> listaServizi;

	@ManyToMany(mappedBy = "listaVagoni") // Indica che la relazione è gestita dall'altra entità (Treno)
	private List<Treno> listaTreni;
	
	

	public Vagone() {} //costruttore vuoto
	
	//Costruttore senza la lista dei servizi
	public Vagone(Double peso, Double prezzo) {
		this.peso = peso;
		this.prezzo = prezzo;
		this.listaServizi = new ArrayList<Servizio>();
		listaTreni = new ArrayList<Treno>();
	}
	
	// Costruttore con la lista dei servizi
	public Vagone(String id, Double peso, Double prezzo, ArrayList<Servizio> listaServizi) {
		this.peso = peso;
		this.prezzo = prezzo;
		this.listaServizi = listaServizi; //da inizializzare
	}

	public Double getPeso() {
		Double pesoTot = 0.0;
		for(Servizio s: listaServizi) {
			pesoTot = pesoTot + s.getPesoS();
		}
		if(pesoTot != 0.0) {
			return peso + pesoTot; 
		}else {
			return peso;
		}
	}
	
	public Double getPrezzo() {
		Double prezzoTot = 0.0; 
		for(Servizio s: listaServizi) {
			prezzoTot = prezzoTot + s.getPrezzoS();
		}
		if(prezzoTot != 0.0) {
			return prezzo + prezzoTot; 
		}else {
			return prezzo;
		}
	}
	
	public void addServizio(Servizio s) {
		if(!listaServizi.contains(s)) {
			listaServizi.add(s);
			s.aggiungiVagone(this);
		}
	}
	
	public void deleteServizio(Servizio s) {
		listaServizi.remove(s);
	}

	public String getTipo()
	{
		return this.getClass().getSimpleName();
	}

	@Override
	public String toString()
	{
		return 
		"\n	" + getTipo()
		+ "\n	peso: " + getPeso()
		+ "\n	prezzo: "+ getPrezzo() + "\n";
	}
	

	public List<Servizio> getListaServizi()
	{
		return listaServizi;
	}
	
}

