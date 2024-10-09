package entity.vagoni.xFurryFast;

import entity.classi_astratte.VagonePasseggeri;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Vagone_xFurryFast_passseggeri")
public class VagonePasseggeriFF extends VagonePasseggeri{
	
	public VagonePasseggeriFF() {
		super();
	}

	public VagonePasseggeriFF(Double peso, Double prezzo, int postiASedere) {
		super(peso,prezzo,postiASedere);
	}

	@Override
	public String getTipo() {
		return "Vagone passeggeri xFurryFast";
	}

}
