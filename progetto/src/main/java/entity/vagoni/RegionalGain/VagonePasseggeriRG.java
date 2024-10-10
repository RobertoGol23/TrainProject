package entity.vagoni.RegionalGain;

import entity.classi_astratte.vagoni_astratti.VagonePasseggeri;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Vagone_RegionalGain_passeggeri")
public class VagonePasseggeriRG extends VagonePasseggeri{
	
	public VagonePasseggeriRG() {
		super();
	}

	public VagonePasseggeriRG(Double peso, Double prezzo, int postiASedere) {
		super(peso,prezzo,postiASedere);
	}
	@Override
	public String getTipo() {
		return "Vagone passeggeri RegionalGain";
	}

}
