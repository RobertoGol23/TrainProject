package fabbriche;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.Locomotiva;
import entity.classi_astratte.Vagone;

public class FabbricaRegionalGain extends FabbricaVagoni {

	AbstractApplicationContext contextFactoryRegionalGain = new ClassPathXmlApplicationContext("RegionalGain.xml");
	
	@Override
	public Locomotiva creaLocomotiva() {
		
		Locomotiva locomotiva = (Locomotiva) contextFactoryRegionalGain.getBean("locomotivaRGL96");
		
		return locomotiva;
	}

	@Override
	public Vagone creaVagoneCargo() {
		
		Vagone vagoneC = (Vagone) contextFactoryRegionalGain.getBean("vagoneRGC96");
		
		return vagoneC;
	}

	@Override
	public Vagone creaVagonePasseggeri() {
		
		Vagone vagoneP = (Vagone) contextFactoryRegionalGain.getBean("vagoneRGP96");
		
		return vagoneP;
		
	}

	@Override
	public Vagone creaVagoneRistorante() {
		
		Vagone vagoneR = (Vagone) contextFactoryRegionalGain.getBean("vagoneRGR96");
		
		return vagoneR;
	}

}
