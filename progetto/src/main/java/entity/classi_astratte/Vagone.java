package entity.classi_astratte;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import utility.TrenoUtility;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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

	@ManyToMany(mappedBy = "listaVagoniS",  cascade = CascadeType.ALL, fetch = FetchType.EAGER) // Indica che la relazione è gestita dall'altra entità (Servizio)
    private List<Servizio> listaServizi = new ArrayList<Servizio>();

	@ManyToMany(mappedBy = "listaVagoni", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // Indica che la relazione è gestita dall'altra entità (Treno)
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

	public Long getId() {
		return id_vagone;
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
	
	public void setPeso(Double peso)
	{
		this.peso = peso;
	}
	
	public void setPrezzo(Double prezzo)
	{
		this.prezzo = prezzo;
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
		
		if(!listaServizi.contains(s))
		{
			listaServizi.add(s);
			s.aggiungiVagone(this);
			setPeso(getPeso()); // Ricalcola il peso e aggiorna
	        setPrezzo(getPrezzo()); // Ricalcola il prezzo e aggiorna
		}
	}
	
	public void deleteServizio(Servizio s) {
		listaServizi.remove(s);
	}
	
	/**
	 * Metodo che rimuove un servizio dalla lista di un vagone
	 * @param nomeServizio
	 * @return boolean con l'esito dell'operazione
	 */
	public boolean deleteServizioByName(String nomeServizio)
	{
		for(int i=0; i<listaServizi.size();i++)
		{
			if(listaServizi.get(i).getNome().equalsIgnoreCase(nomeServizio))
			{
				listaServizi.remove(i);
				return true;
			}
					
		}
		return false;
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
	
	public void setListaServizi(List<Servizio> listaServizi) {
		this.listaServizi = listaServizi;
	}

	public String getDettagli()
	{
		TrenoUtility tu = new TrenoUtility();
		return tu.getDettagli(this);
	}
	
	
}

