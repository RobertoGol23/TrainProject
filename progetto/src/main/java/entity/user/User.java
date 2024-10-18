package entity.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import entity.acquisto.Acquisto;
import entity.treno.Treno;
import entity.votazioni.Voto;
import jakarta.validation.constraints.Email;


@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_user;
	
	@Column(name = "nome", nullable = false, unique = false)
	private String nome;
	
	@Column(name = "cognome", nullable = false, unique = false)
	private String cognome;
	
	@Column(name = "email", nullable = false, unique = true)
	@Email(message = "Email non valida")
	private String email;
	
	@Column(name = "password", nullable = false, unique = false)
	private String password;
	
	@Column(name = "wallet", nullable = true, unique = false)
	private Double wallet = 0.0;
	
	@Column(name = "bloccato", nullable = false)
	private boolean bloccato = false;
	
	@Column(name = "is_super_admin", nullable = false)
    private boolean isSuperAdmin = false;

	
	
	//TODO: Modificare in many-to-many ???
	// One-to-Many: una persona può avere molti treni
    @OneToMany(mappedBy = "user")
    private List<Treno> treni;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) 
    private List<Voto> voti;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Acquisto> acquisti = new ArrayList<Acquisto>();
	
	public User() {}
	
	public User(String nome, String cognome, String email, String password, Double wallet) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.wallet = 0.0;
		}
	
	public User(String nome, String cognome, String email, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.wallet = 0.0;
	}

	public Long getId_User() {
		return id_user;
	}

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

	public Double getWallet() {
		return wallet;
	}

	public void setWallet(Double wallet) {
		this.wallet = wallet;
	}
	
	public boolean isBloccato() {
	    return bloccato;
	}

	public void setBloccato(boolean bloccato) {
	    this.bloccato = bloccato;
	}
	
	public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }
	
	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email
				+ ", password=" + password + ", wallet=" + wallet + "]";
	}

	
	
}
