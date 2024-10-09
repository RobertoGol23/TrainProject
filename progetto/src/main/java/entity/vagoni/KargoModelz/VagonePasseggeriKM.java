package entity.vagoni.KargoModelz;

import entity.classi_astratte.VagonePasseggeri;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Vagone_KargoModelz_passeggeri") 
public class VagonePasseggeriKM extends VagonePasseggeri{
	
	public VagonePasseggeriKM() {
			super();
		}

	public VagonePasseggeriKM(Double peso, Double prezzo, int postiASedere) {
		super(peso,prezzo,postiASedere);
	}

	@Override
	public String getTipo() {
		return "Vagone passeggeri KargoModelz";
	}

}
