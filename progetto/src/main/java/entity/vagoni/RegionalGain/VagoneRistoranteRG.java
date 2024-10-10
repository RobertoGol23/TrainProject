package entity.vagoni.RegionalGain;

import entity.classi_astratte.vagoni_astratti.VagoneRistorante;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Vagone_RegionalGain_ristorante")
public class VagoneRistoranteRG extends VagoneRistorante{
	
	public VagoneRistoranteRG() {
		super();
	}

	public VagoneRistoranteRG(Double peso, Double prezzo, int coperti) {
		super(peso,prezzo,coperti);
	}
	
	@Override
	public String getTipo() {
		return "Vagone ristorante RegionalGain";
	}

}
