package entity.vagoni.xFurryFast;

import entity.classi_astratte.VagoneCargo;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Vagone_xFurryFast_cargo")
public class VagoneCargoFF extends VagoneCargo{

	public VagoneCargoFF() {
		super();
	}

	public VagoneCargoFF(Double peso, Double prezzo, Double pesoMassimoTrasportabile) {
		super(peso,prezzo,pesoMassimoTrasportabile);
	}
	

	@Override
	public String getTipo() {
		return "Vagone cargo xFurryFast";
	}

	public Double getPesoTotale() {
		
		return this.getPeso() + this.getPesoMassimoTrasportabile();
	}

}
