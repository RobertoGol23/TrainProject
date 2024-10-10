package utility;


import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.TrenoBuilder;
import entity.classi_astratte.Vagone;
import entity.treno.Locomotiva;

public class Assemblatore extends TrenoBuilder {

	private FabbricaVagoni fabbricaTreno;
	
	
	
	public Assemblatore(FabbricaVagoni fabbricaTreno)
	{
		this.fabbricaTreno = fabbricaTreno;
	}

	@Override
	protected Locomotiva getLocomotiva() {

		Locomotiva locomotiva = fabbricaTreno.creaLocomotiva();
		
		return locomotiva;
	}

	@Override
	protected Vagone getVagonePasseggeri() {

		Vagone vagoneP = fabbricaTreno.creaVagonePasseggeri();
	
		return vagoneP;
	}

	@Override
	protected Vagone getVagoneCargo() {

		Vagone vagoneC = fabbricaTreno.creaVagoneCargo();
		
		return vagoneC;
	}

	@Override
	protected Vagone getVagoneRistorante() {

		Vagone vagoneR = fabbricaTreno.creaVagoneRistorante();
		
		return vagoneR;
	}

}
