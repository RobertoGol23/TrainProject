package entity.vagoni.xFurryFast;

import entity.classi_astratte.VagoneRistorante;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Vagone_xFurryFast_ristorante")
public class VagoneRistoranteFF extends VagoneRistorante{
	
	public VagoneRistoranteFF() {
		super();
	}

	public VagoneRistoranteFF(Double peso, Double prezzo, int coperti) {
		super(peso,prezzo,coperti);
	}
	
	@Override
	public String getTipo() {
		return "Vagone ristorante xFurryFast";
	}

}
