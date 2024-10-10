package entity.classi_astratte;

import entity.treno.Locomotiva;

public abstract class FabbricaVagoni {

	public abstract Locomotiva creaLocomotiva();
	public abstract Vagone creaVagoneCargo();
	public abstract Vagone creaVagonePasseggeri();
	public abstract Vagone creaVagoneRistorante();
	
}
