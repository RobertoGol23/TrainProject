package entity.vagoni.KargoModelz;

import entity.classi_astratte.VagoneCargo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Vagone_KargoModelz_cargo") 
public class VagoneCargoKM extends VagoneCargo{

	public VagoneCargoKM() {
		super();
	}

	public VagoneCargoKM(Double peso, Double prezzo, Double pesoMassimoTrasportabile) {
		super(peso,prezzo,pesoMassimoTrasportabile);
	}

	@Override
	public String getTipo() {
		return "Vagone cargo KargoModel";
	}

	public Double getPesoTotale() {
		
		return this.getPeso() + this.getPesoMassimoTrasportabile();
	}
}
