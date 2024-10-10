<<<<<<<< HEAD:progetto/src/main/java/entity/treno/VagonePasseggeri.java
package entity.treno;
========
package entity.classi_astratte.vagoni_astratti;
>>>>>>>> main:progetto/src/main/java/entity/classi_astratte/vagoni_astratti/VagonePasseggeri.java

import entity.classi_astratte.Vagone;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
<<<<<<<< HEAD:progetto/src/main/java/entity/treno/VagonePasseggeri.java
public class VagonePasseggeri extends Vagone{ //pensare se si puo' levare abstract per il dao
========
@Table(name = "Vagoni Passeggeri")
public abstract class VagonePasseggeri extends Vagone{ //pensare se si puo' levare abstract per il dao
>>>>>>>> main:progetto/src/main/java/entity/classi_astratte/vagoni_astratti/VagonePasseggeri.java
	
	@Column(name="posti_a_sedere", nullable = false, unique = false)
	private int postiASedere;
	
	public VagonePasseggeri() {}
	
	public VagonePasseggeri(Double peso, Double prezzo, int postiASedere) {
		super(peso,prezzo);
		this.postiASedere = postiASedere;
	}

	public int getPostiASedere() {
		return postiASedere;
	}

	public void setPostiASedere(int postiASedere) {
		this.postiASedere = postiASedere;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "	Posti a sedere: " + getPostiASedere() + "\n";
	}
}