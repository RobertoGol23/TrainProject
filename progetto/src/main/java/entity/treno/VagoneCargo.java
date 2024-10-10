<<<<<<<< HEAD:progetto/src/main/java/entity/treno/VagoneCargo.java
package entity.treno;
========
package entity.classi_astratte.vagoni_astratti;
>>>>>>>> main:progetto/src/main/java/entity/classi_astratte/vagoni_astratti/VagoneCargo.java

import entity.classi_astratte.Vagone;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
<<<<<<<< HEAD:progetto/src/main/java/entity/treno/VagoneCargo.java
public class VagoneCargo extends Vagone{ //pensare se si puo' levare abstract per il dao
========
@Table(name = "Vagoni Cargo")
public abstract class VagoneCargo extends Vagone{ //pensare se si puo' levare abstract per il dao
>>>>>>>> main:progetto/src/main/java/entity/classi_astratte/vagoni_astratti/VagoneCargo.java

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