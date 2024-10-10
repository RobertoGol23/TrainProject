package entity.vagoni.xFurryFast;

import entity.classi_astratte.vagoni_astratti.Locomotiva;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Locomotiva_xFurryFast")
public class LocomotivaFF extends Locomotiva{

	public LocomotivaFF() {
		super();
	}

	public LocomotivaFF(Double peso, Double prezzo, Double pesoTrainabile) {
		super(peso,prezzo,pesoTrainabile);
	}

	@Override
	public String getTipo() {
		return "Locomotiva xFurryFast";
	}
	
	public String toString()
	{
		return getTipo() + "\nPeso: " + getPeso()
						+ "\nPrezzo: " + getPrezzo()
						+ "\nPeso trainabile: " + getPesoTrainabile();
	}
	
}
