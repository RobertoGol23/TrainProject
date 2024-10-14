package entity.votazioni;

import entity.treno.Treno;
import entity.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "voti", uniqueConstraints = { //impedisce di avere nella tabella altri campi con chiavi uguali
	    @UniqueConstraint(columnNames = {"user_id", "treno_id"})
	    })
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pensare se necessario

    @Column(nullable = false)
    private int punteggio;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "treno_id", nullable = false)
    private Treno treno;

    // Costruttori, Getter e Setter
    public Voto() {}

    public Voto(int punteggio, User user, Treno treno) {
        this.punteggio = punteggio;
        this.user = user;
        this.treno = treno;
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Treno getTreno() {
        return treno;
    }

    public void setTreno(Treno treno) {
        this.treno = treno;
    }
}
