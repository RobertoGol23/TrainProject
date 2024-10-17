package entity.treno;

import entity.classi_astratte.Vagone;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class VagoneRistorante extends Vagone{ //pensare se si puo' levare abstract per il dao

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
