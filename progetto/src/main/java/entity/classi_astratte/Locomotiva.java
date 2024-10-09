package entity.classi_astratte;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Locomotiva extends Vagone{ //pensare se si puo' levare abstract per il dao

	@Column(name = "peso_trainabile", nullable = false, unique = false)
	private Double pesoTrainabile;	
	
	public Locomotiva() {} // costruttore vuoto
	
	public Locomotiva(Double peso, Double prezzo, Double pesoTrainabile) {
		super(peso,prezzo);
		this.pesoTrainabile = pesoTrainabile;
	}
	
	public Double getPesoTrainabile() {
		return pesoTrainabile;
	}
	
	public void setPesoTrainabile(Double pesoTrainabile) {
		this.pesoTrainabile = pesoTrainabile;
	}	
	
	@Override
	public String toString()
	{
		return super.toString() + "\nPeso trainabile: " + getPesoTrainabile();
	}
}
