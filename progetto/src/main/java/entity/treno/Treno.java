package entity.treno;

import java.util.ArrayList;
import java.util.List;

import entity.classi_astratte.Vagone;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;


@Entity
//@Table(name="Treni")
public class Treno {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_treno;
	
	@Column(name="nomeTreno", nullable = false, unique = false)
	private String nomeTreno;
	
	@Column(name="marca", nullable = false, unique = false)
	private String marca;
	
   // @OneToMany(mappedBy = "treno", cascade = CascadeType.ALL, orphanRemoval = true)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "treno_vagoni", // Nome della tabella di join
			joinColumns = @JoinColumn(name = "id_treno"), // Colonna della chiave esterna per Treno
			inverseJoinColumns = @JoinColumn(name = "id_vagone") // Colonna della chiave esterna per Vagone
		)
	private List<Vagone> listaVagoni;

	@Transient //non viene aggiunto alla tabella 
	private Vagone locomotiva;
	
	
	private Treno() {} //costruttore vuoto
	
	// Costruttore privato della classe Treno, richiamato soltanto dal suo metodo statico
	private Treno(String nomeTreno, Vagone locomotiva, ArrayList<Vagone> listaVagoni, String marca)
	{
		this.setNomeTreno(nomeTreno);
		this.setLocomotiva(locomotiva);
		this.setListaVagoni(listaVagoni);
		this.setMarca(marca);
		listaVagoni = new ArrayList<Vagone>();
	}
	
	
	// Metodo statico per creare l'istanza treno a partire dalla locomotiva e lista vagoni

	public static Treno creaTreno(String nomeTreno,Vagone locomotiva,ArrayList<Vagone> listaVagoni, String marca)
	{
		Treno treno = new Treno(nomeTreno,locomotiva, listaVagoni, marca);
		return treno;
	}
	
	public String getNomeTreno() {
		return nomeTreno;
	}

	public void setNomeTreno(String nomeTreno) {
		this.nomeTreno = nomeTreno;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getMarca(){
		return marca;
	}
	
	public Locomotiva getLocomotiva() {
		return (Locomotiva)listaVagoni.get(0);
	}

	public Locomotiva getLocomotivaInCoda(){
		Vagone locomotivaInCoda = listaVagoni.get(listaVagoni.size());
		if(locomotivaInCoda.getClass() == Locomotiva.class){
			return (Locomotiva)locomotivaInCoda;
		}
		return null;
	}

	public void setLocomotiva(Vagone locomotiva) {
		this.locomotiva = locomotiva;
	}

	public Vagone getVagone(int index){
		return getListaVagoni().get(index);
	}

	public void setVagone(int index, Vagone vagone){
		listaVagoni.set(index, vagone);
	}

	public List<Vagone> getListaVagoni() {
		return listaVagoni;
	}

	public void setListaVagoni(ArrayList<Vagone> listaVagoni) {
		this.listaVagoni = listaVagoni;
	}

	@Override
	public String toString() {
		
		String vagoni = "";
		
		for(Vagone vagone : getListaVagoni())
		{
			vagoni += vagone.toString();
		}
		
		return "Locomotiva: " + getLocomotiva() + "\nListaVagoni:\n" + vagoni;
	}
	
	
	
}
