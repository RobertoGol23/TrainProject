package entity.vagoni.KargoModelz;

import entity.classi_astratte.Locomotiva;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Locomovita_KargoModelz") 
public class LocomotivaKM extends Locomotiva{

	public LocomotivaKM() {
		super();
	}

	public LocomotivaKM(Double peso, Double prezzo, Double pesoTrainabile) {
		super(peso,prezzo,pesoTrainabile);
	}

	@Override
	public String getTipo() {
		return "Locomotiva KargoModelz";
	}
	
	public String toString()
	{
		return getTipo() + "\nPeso: " + getPeso()
						+ "\nPrezzo: " + getPrezzo()
						+ "\nPeso trainabile: " + getPesoTrainabile();
	}

}