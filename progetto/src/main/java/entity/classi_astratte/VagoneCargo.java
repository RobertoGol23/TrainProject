package entity.classi_astratte;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;

@Entity
@Table(name = "Vagoni Cargo")
public abstract class VagoneCargo extends Vagone{ //pensare se si puo' levare abstract per il dao

	@Column(name="peso_massimo_trasportabile", nullable = false, unique = false)
	private Double pesoMassimoTrasportabile;
	

	public VagoneCargo() {} //costruttore vuoto
	
	public VagoneCargo(Double peso, Double prezzo, Double pesoMassimoTrasportabile) {
		super(peso,prezzo);
		this.pesoMassimoTrasportabile = pesoMassimoTrasportabile;
	}
	

	public Double getPesoMassimoTrasportabile() {
		return pesoMassimoTrasportabile;
	}

	public void setPesoMassimoTrasportabile(Double pesoMassimoTrasportabile) {
		this.pesoMassimoTrasportabile = pesoMassimoTrasportabile;
	}	
	
	@Override
	public String toString()
	{
		return super.toString() + "	Peso massimo trasportabile: " + getPesoMassimoTrasportabile() + "\n";
	}
}