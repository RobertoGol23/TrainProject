package entity.vagoni.RegionalGain;

import entity.classi_astratte.vagoni_astratti.VagoneCargo;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Vagone_RegionalGain_cargo")
public class VagoneCargoRG extends VagoneCargo{

	public VagoneCargoRG() {
		super();
	}

	public VagoneCargoRG(Double peso, Double prezzo, Double pesoMassimoTrasportabile) {
		super(peso,prezzo,pesoMassimoTrasportabile);
	}

	@Override
	public String getTipo() {
		return "Vagone cargo RegionalGain";
	}

	public Double getPesoTotale() {
		
		return this.getPeso() + this.getPesoMassimoTrasportabile();
	}

}
