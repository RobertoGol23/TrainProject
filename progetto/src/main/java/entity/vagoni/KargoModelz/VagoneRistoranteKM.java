package entity.vagoni.KargoModelz;

import entity.classi_astratte.vagoni_astratti.VagoneRistorante;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Vagone_KargoModelz_ristorante") 
public class VagoneRistoranteKM extends VagoneRistorante{
	
    public VagoneRistoranteKM() {
		super();
	}

	public VagoneRistoranteKM(Double peso, Double prezzo, int coperti) {
		super(peso,prezzo,coperti);
	}
	
	@Override
	public String getTipo() {
		return "Vagone ristorante KargoModelz";
	}

}
