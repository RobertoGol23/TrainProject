package entity.user;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {


    public Admin() {
        super();
    }

    public Admin(String nome, String cognome, String email, String password, Double wallet) {
        super(nome, cognome, email, password, wallet);
    }

    public Admin(String nome, String cognome, String email, String password) {
        super(nome, cognome, email, password);
    }

    // Metodo per bloccare un utente
    public void bloccaUtente(User utente) {
        // Logica per bloccare l'utente (ad es. impostando uno stato "bloccato" nell'entità User)
        utente.setBloccato(true);
    }

    // Metodo per riattivare un utente
    public void riattivaUtente(User utente) {
        // Logica per riattivare l'utente
        utente.setBloccato(false);
    }

    @Override
    public String toString() {
        return "Admin [id_user=" + getId_user() + ", nome=" + getNome() + ", cognome=" + getCognome() + ", email=" 
                + getEmail() + "]";
    }
}