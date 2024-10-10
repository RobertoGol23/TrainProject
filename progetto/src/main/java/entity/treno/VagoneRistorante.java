<<<<<<<< HEAD:progetto/src/main/java/entity/treno/VagoneRistorante.java
package entity.treno;
========
package entity.classi_astratte.vagoni_astratti;
>>>>>>>> main:progetto/src/main/java/entity/classi_astratte/vagoni_astratti/VagoneRistorante.java

import entity.classi_astratte.Vagone;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
<<<<<<<< HEAD:progetto/src/main/java/entity/treno/VagoneRistorante.java
public class VagoneRistorante extends Vagone{ //pensare se si puo' levare abstract per il dao
========
@Table(name = "Vagoni Ristorante")
public abstract class VagoneRistorante extends Vagone{ //pensare se si puo' levare abstract per il dao
>>>>>>>> main:progetto/src/main/java/entity/classi_astratte/vagoni_astratti/VagoneRistorante.java

	@Column(name="coperti", nullable = false, unique = false)
	private int coperti;
	
	public VagoneRistorante() {} //costruttore vuoto
	
	public VagoneRistorante(Double peso, Double prezzo, int coperti) {
		super(peso,prezzo);
		this.coperti = coperti;
	}
	
	public void setCoperti(int coperti) {
		this.coperti = coperti;
	}

	@Override
	public String toString()
	{
		return super.toString() + "	Coperti: " + getCoperti() +"\n";
	}

	public int getCoperti() {
		return coperti;
	}

	
}
