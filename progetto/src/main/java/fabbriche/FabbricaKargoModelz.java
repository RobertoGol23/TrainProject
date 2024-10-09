package fabbriche;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.Locomotiva;
import entity.classi_astratte.Vagone;

public class FabbricaKargoModelz extends FabbricaVagoni {

	// TODO: il context va chiuso in qualche modo, un'idea può essere quella di passarglielo come paramentro e poi chiudere il contex nel builder una volta creato il treno

	
	/* private AbstractApplicationContext contextFactoryKargoModelz;

	public FabbricaKargoModelz() {
		contextFactoryKargoModelz = new ClassPathXmlApplicationContext("applicationContextKargoModelz.xml");
	} */


	AbstractApplicationContext contextFactoryKargoModelz = new ClassPathXmlApplicationContext("KargoModelz.xml");
	
	@Override
	public Locomotiva creaLocomotiva() {
		
		Locomotiva locomotiva = (Locomotiva) contextFactoryKargoModelz.getBean("locomotivaKML2000");
		
		return locomotiva;
	}

	@Override
	public Vagone creaVagoneCargo() {
		
		Vagone vagoneC = (Vagone) contextFactoryKargoModelz.getBean("vagoneKMC2000");
		
		return vagoneC;
	}

	@Override
	public Vagone creaVagonePasseggeri() {
		
		Vagone vagoneP = (Vagone) contextFactoryKargoModelz.getBean("vagoneKMP2000");
		
		return vagoneP;
		
	}

	@Override
	public Vagone creaVagoneRistorante() {
		
		Vagone vagoneR = (Vagone) contextFactoryKargoModelz.getBean("vagoneKMR2000");
		
		return vagoneR;
	}

}
