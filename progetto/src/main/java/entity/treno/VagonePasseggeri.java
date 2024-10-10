package entity.treno;

import entity.classi_astratte.Vagone;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class VagonePasseggeri extends Vagone{ //pensare se si puo' levare abstract per il dao

	
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