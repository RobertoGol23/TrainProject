package entity.classi_astratte.vagoni_astratti;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;

@Entity
@Table(name = "Vagoni Passeggeri")
public abstract class VagonePasseggeri extends Vagone{ //pensare se si puo' levare abstract per il dao
	
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