package entity.classi_astratte.fabbrica_and_builder;

import entity.classi_astratte.vagoni_astratti.Locomotiva;
import entity.classi_astratte.vagoni_astratti.Vagone;

import entity.treno.Locomotiva;

public abstract class FabbricaVagoni {

	public abstract Locomotiva creaLocomotiva();
	public abstract Vagone creaVagoneCargo();
	public abstract Vagone creaVagonePasseggeri();
	public abstract Vagone creaVagoneRistorante();
	
}
