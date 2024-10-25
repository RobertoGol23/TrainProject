package entity.servizi;

import java.util.ArrayList;
import java.util.List;

import entity.classi_astratte.Vagone;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


// TODO creare un "seeder" che inserisce i dati dei servizi direttamente nel database
// TODO: creare il bean relativo!!!1

@Entity
@Table(name = "Servizi")
public class Servizio { //da creare xml per inserire i dati dei servizi direttamente
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",nullable = false, unique = true)
	private Long id;
	
	@Column(name="nome",nullable = false, unique = false)
	private String nome;
	
	@Column(name = "pesoS", nullable = false, unique = false)
	private Double pesoS;
	
	@Column(name = "prezzoS", nullable = false, unique = false)
	private Double prezzoS;


	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "servizi_utilizzati", // Nome della tabella di join
        joinColumns = @JoinColumn(name = "nome"), // Colonna della chiave esterna per Servizio 
        inverseJoinColumns = @JoinColumn(name = "id_vagone") // Colonna della chiave esterna per Vagone
    )
    private List<Vagone> listaVagoniS = new ArrayList<>();

	
	public Servizio(String nome, Double pesoS, Double prezzoS)
	{
		this.nome = nome;
		this.pesoS = pesoS;
		this.prezzoS = prezzoS;
	}
	
	public Servizio()	{}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPesoS() {
		return pesoS;
	}

	public void setPesoS(Double pesoS) {
		this.pesoS = pesoS;
	}

	public Double getPrezzoS() {
		return prezzoS;
	}

	public void setPrezzoS(Double prezzoS) {
		this.prezzoS = prezzoS;
	}
	
	public void aggiungiVagone(Vagone v) {
		if(!listaVagoniS.contains(v)) {
			listaVagoniS.add(v);
			v.addServizio(this);
		}
	}
	
}
