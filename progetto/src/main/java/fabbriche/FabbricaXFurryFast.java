package fabbriche;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.Vagone;
import entity.treno.Locomotiva;

public class FabbricaXFurryFast extends FabbricaVagoni {

	AbstractApplicationContext contextFactoryKargoModelz;
	
	@Override
	public Locomotiva creaLocomotiva() {
		
		contextFactoryKargoModelz = new ClassPathXmlApplicationContext("xFurryFast.xml");
		
		Locomotiva locomotiva = (Locomotiva) contextFactoryKargoModelz.getBean("locomotivaFFL15");
		
		contextFactoryKargoModelz.close();
		
		return locomotiva;
	}

	@Override
	public Vagone creaVagoneCargo() {
		
		contextFactoryKargoModelz = new ClassPathXmlApplicationContext("xFurryFast.xml");
		
		Vagone vagoneC = (Vagone) contextFactoryKargoModelz.getBean("vagoneFFC15");
		
		contextFactoryKargoModelz.close();
		
		return vagoneC;
	}

	@Override
	public Vagone creaVagonePasseggeri() {
		
		contextFactoryKargoModelz = new ClassPathXmlApplicationContext("xFurryFast.xml");
		
		Vagone vagoneP = (Vagone) contextFactoryKargoModelz.getBean("vagoneFFP15");
		
		contextFactoryKargoModelz.close();
		
		return vagoneP;
		
	}

	@Override
	public Vagone creaVagoneRistorante() {
		
		contextFactoryKargoModelz = new ClassPathXmlApplicationContext("xFurryFast.xml");
		
		Vagone vagoneR = (Vagone) contextFactoryKargoModelz.getBean("vagoneFFR15");
		
		contextFactoryKargoModelz.close();
		
		return vagoneR;
	}

}
