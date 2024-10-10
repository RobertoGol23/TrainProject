package entity.vagoni.RegionalGain;

import entity.classi_astratte.vagoni_astratti.Locomotiva;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Locomotiva_RegionalGain")
public class LocomotivaRG extends Locomotiva{

	public LocomotivaRG() {
		super();
	}

	public LocomotivaRG(Double peso, Double prezzo, Double pesoTrainabile) {
		super(peso,prezzo,pesoTrainabile);
	}

	@Override
	public String getTipo() {
		return "Locomotiva RegionalGain";
	}
	
	public String toString()
	{
		return getTipo() + "\nPeso: " + getPeso()
						+ "\nPrezzo: " + getPrezzo()
						+ "\nPeso trainabile: " + getPesoTrainabile();
	}
	
}
