package fabbriche;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.classi_astratte.fabbrica_and_builder.FabbricaVagoni;
import entity.classi_astratte.vagoni_astratti.Locomotiva;
import entity.classi_astratte.vagoni_astratti.Vagone;

public class FabbricaXFurryFast extends FabbricaVagoni {

	AbstractApplicationContext contextFactoryxFurryFast = new ClassPathXmlApplicationContext("xFurryFast.xml");
	
	@Override
	public Locomotiva creaLocomotiva() {
		
		Locomotiva locomotiva = (Locomotiva) contextFactoryxFurryFast.getBean("locomotivaFFL15");
		
		return locomotiva;
	}

	@Override
	public Vagone creaVagoneCargo() {
		
		Vagone vagoneC = (Vagone) contextFactoryxFurryFast.getBean("vagoneFFC15");
		
		return vagoneC;
	}

	@Override
	public Vagone creaVagonePasseggeri() {
		
		Vagone vagoneP = (Vagone) contextFactoryxFurryFast.getBean("vagoneFFP15");
		
		return vagoneP;
		
	}

	@Override
	public Vagone creaVagoneRistorante() {
		
		Vagone vagoneR = (Vagone) contextFactoryxFurryFast.getBean("vagoneFFR15");
		
		return vagoneR;
	}

}
