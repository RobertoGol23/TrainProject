package entity.treno;


import entity.classi_astratte.Vagone;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "Locomotive")
public class Locomotiva extends Vagone{ //pensare se si puo' levare abstract per il dao

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
		return super.toString() + "	Peso trainabile: " + getPesoTrainabile() +"\n";
	}

	@Override
	public String getTipoStampa() {
		
		return "Locomotiva";
	}
}
