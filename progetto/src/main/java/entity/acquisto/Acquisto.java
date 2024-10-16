package entity.acquisto;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import configuration.JpaConfig;
import eccezioni.eccezioniGeneriche.SoldiNonSufficientiException;
import entity.dao.UserDAO;
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

@Entity
@Table(name = "acquisti")
public class Acquisto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "treno_id", nullable = false)
    private Treno treno;

    
    @Column(name = "data_acquisto", nullable = false)
    private String dataAcquisto;


    // Costruttori, Getter e Setter
    public Acquisto() {}

    // TODO: togliere String data e mettere timestamp
    public Acquisto(User user, Treno treno, String data) throws SoldiNonSufficientiException {
    	
    	Double costoTreno = treno.getPrezzoTotaleTreno();
    	Double wallet = user.getWallet();
    	
    	if(wallet < costoTreno) {
    		throw new SoldiNonSufficientiException(wallet, costoTreno, "Errore: soldi non sufficienti");
    	}
    	
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
    	
    	user.setWallet(wallet-costoTreno);
    	UserDAO userDao = context.getBean(UserDAO.class);
    	
    	userDao.updateUser(user);
    	context.close();
    	
        this.user = user;
        this.treno = treno;
        this.dataAcquisto = data;
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(String dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

}