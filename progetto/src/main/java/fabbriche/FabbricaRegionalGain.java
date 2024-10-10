package fabbriche;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.Vagone;
import entity.treno.Locomotiva;

public class FabbricaRegionalGain extends FabbricaVagoni {

	AbstractApplicationContext contextFactoryKargoModelz;
	
	@Override
	public Locomotiva creaLocomotiva() {
		
		contextFactoryKargoModelz = new ClassPathXmlApplicationContext("RegionalGain.xml");
		
		Locomotiva locomotiva = (Locomotiva) contextFactoryKargoModelz.getBean("locomotivaRGL96");
		
		contextFactoryKargoModelz.close();
		
		return locomotiva;
	}

	@Override
	public Vagone creaVagoneCargo() {
		
		contextFactoryKargoModelz = new ClassPathXmlApplicationContext("RegionalGain.xml");
		
		Vagone vagoneC = (Vagone) contextFactoryKargoModelz.getBean("vagoneRGC96");
		
		contextFactoryKargoModelz.close();
		
		return vagoneC;
	}

	@Override
	public Vagone creaVagonePasseggeri() {
		
		contextFactoryKargoModelz = new ClassPathXmlApplicationContext("RegionalGain.xml");
		
		Vagone vagoneP = (Vagone) contextFactoryKargoModelz.getBean("vagoneRGP96");
		
		contextFactoryKargoModelz.close();
		
		return vagoneP;
		
	}

	@Override
	public Vagone creaVagoneRistorante() {
		
		contextFactoryKargoModelz = new ClassPathXmlApplicationContext("RegionalGain.xml");
		
		Vagone vagoneR = (Vagone) contextFactoryKargoModelz.getBean("vagoneRGR96");
		
		contextFactoryKargoModelz.close();
		
		return vagoneR;
	}

}
