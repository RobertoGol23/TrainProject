package fabbriche;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.Vagone;
import entity.treno.Locomotiva;

public class FabbricaKargoModelz extends FabbricaVagoni {

	AbstractApplicationContext contextFactoryKargoModelz;
	
	@Override
	public Locomotiva creaLocomotiva() {
		
		contextFactoryKargoModelz = new ClassPathXmlApplicationContext("KargoModelz.xml");
		
		Locomotiva locomotiva = (Locomotiva) contextFactoryKargoModelz.getBean("locomotivaKML2000");
		
		contextFactoryKargoModelz.close();
		
		return locomotiva;
	}

	@Override
	public Vagone creaVagoneCargo() {
		
		contextFactoryKargoModelz = new ClassPathXmlApplicationContext("KargoModelz.xml");
		
		Vagone vagoneC = (Vagone) contextFactoryKargoModelz.getBean("vagoneKMC2000");
		
		contextFactoryKargoModelz.close();
		
		return vagoneC;
	}

	@Override
	public Vagone creaVagonePasseggeri() {
		
		contextFactoryKargoModelz = new ClassPathXmlApplicationContext("KargoModelz.xml");
		
		Vagone vagoneP = (Vagone) contextFactoryKargoModelz.getBean("vagoneKMP2000");
		
		contextFactoryKargoModelz.close();
		
		return vagoneP;
		
	}

	@Override
	public Vagone creaVagoneRistorante() {
		
		contextFactoryKargoModelz = new ClassPathXmlApplicationContext("KargoModelz.xml");
		
		Vagone vagoneR = (Vagone) contextFactoryKargoModelz.getBean("vagoneKMR2000");
		
		contextFactoryKargoModelz.close();
		
		return vagoneR;
	}

}
