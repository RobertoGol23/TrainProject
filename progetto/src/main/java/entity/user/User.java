package entity.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

import entity.treno.Treno;
import entity.votazioni.Voto;


@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_user;
	
	@Column(name = "nome", nullable = false, unique = false)
	private String nome;
	
	@Column(name = "cognome", nullable = false, unique = false)
	private String cognome;
	
	@Column(name = "email", nullable = false, unique = false)
	private String email;
	
	@Column(name = "password", nullable = false, unique = false)
	private String password;
	
	// One-to-Many: una persona può avere molti treni
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Treno> treni;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Voto> voti;
	
	public User() {}
	
	public User(String nome, String cognome, String email, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
	}
	
	//public List<Treno> getTreni() {
	//	return treni;
	//}

	//public void setTreni(List<Treno> treni) {
	//	this.treni = treni;
	//}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId_user() {
		return id_user;
	}

	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + "]";
	}
	
}
