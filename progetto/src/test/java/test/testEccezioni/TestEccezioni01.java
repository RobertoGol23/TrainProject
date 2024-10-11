package test.testEccezioni;

import eccezioni.eccezioniSigla.SiglaTrenoException;
import entity.classi_astratte.FabbricaVagoni;
import entity.classi_astratte.TrenoBuilder;
import entity.dao.UserDAO;
import entity.treno.Treno;
import entity.user.User;
import fabbriche.FabbricaKargoModelz;
import utility.Assemblatore;


public class TestEccezioni01 {
	public static void main(String[] args) {
		

		/*				TEST 04
		 * test sulle eccezioni:
		 * 		-1 IncoerenzaVagoniException: vagone cargo & altro vagone || vagone risorante senza vagoni passeggeri
		 * 				- 2
		 * 		-2 LocomotivaNonInTestaException: locomotiva non in testa
		 * 		-3 LocomotivaInMezzoException: locomotiva in mezzo al vagone
		 * 		-4 RistoranteNonInMezzoException: ristorante non in posizione centrale
		 * 		-5 StringaNonValidaException: stringa non valida (con caratteri non validi, IN QUALSIASI PUNTO)
		 * 		-6 TroppeLocomotivaException: troppe locomotive
		 * 		-7 TroppiRistorantiException: troppi ristoranti
		 * 		-8 TroppoPesoException: peso maggiore del peso trainabile della locomotiva
		 */
		
		
			//AbstractApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);

			String sigla1_IncoerenzaVagoni = "hcpp";
			String sigla1_IncoerenzaVagoni2 = "hrr";
			String sigla2_LocomotivaNonInTesta = "phh";
			String sigla3_LocomotivaInMezzo = "hphrp";
			String sigla4_RistoranteNonInMezzo = "hppprp";
			String sigla5_StringaNonValida = "hprPp";
			String sigla6_TroppeLocomotive = "hprphh";
			String sigla7_TroppiRistoranti = "hprrp"; //troppi ristoranti con ristorante in mezzo
			String sigla8_TroppoPeso = "hprppppppppp";

			FabbricaVagoni fabbricaKM= new FabbricaKargoModelz();
			TrenoBuilder builderKM = new Assemblatore(fabbricaKM);

			
			User userTest = new User("Test","Test", "test.mazza@gmail.com", "Test123");
			

		try {
			System.out.println("TEST 1: \n");
			builderKM.costruisciTreno("Test 1",sigla1_IncoerenzaVagoni,userTest, 3);
		}
		catch(SiglaTrenoException e){
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}

		try {
			System.out.println("TEST 1.2: \n");
			builderKM.costruisciTreno("Test 1.2",sigla1_IncoerenzaVagoni,userTest, 3);
		}
		catch(SiglaTrenoException e){
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}

		try {
			System.out.println("TEST 2: \n");
			builderKM.costruisciTreno("Test 2",sigla1_IncoerenzaVagoni2,userTest, 3);
		}
		catch(SiglaTrenoException e){
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}
		
		try {
			System.out.println("TEST 3: \n");
			builderKM.costruisciTreno("Test 3",sigla2_LocomotivaNonInTesta,userTest, 3);
		}
		catch(SiglaTrenoException e){
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}

		try {
			System.out.println("TEST 4: \n");
			builderKM.costruisciTreno("Test 4",sigla3_LocomotivaInMezzo,userTest, 3);
		}
		catch(SiglaTrenoException e){
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}

		try {
			System.out.println("TEST 5: \n");
			builderKM.costruisciTreno("Test 5",sigla4_RistoranteNonInMezzo,userTest, 3);
		}
		catch(SiglaTrenoException e){
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}
			
		try {
			System.out.println("TEST 6: \n");
			builderKM.costruisciTreno("Test 6",sigla5_StringaNonValida,userTest, 3);
		}
		catch(SiglaTrenoException e){
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}

		try {
			System.out.println("TEST 7: \n");
			builderKM.costruisciTreno("Test 7",sigla6_TroppeLocomotive,userTest, 3);
		}
		catch(SiglaTrenoException e){
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}

		try {
			System.out.println("TEST 8: \n");
			builderKM.costruisciTreno("Test 8",sigla7_TroppiRistoranti,userTest, 3);
		}
		catch(SiglaTrenoException e){
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}	
			
		try {
			System.out.println("TEST 9: \n");
			builderKM.costruisciTreno("Test 9",sigla8_TroppoPeso,userTest, 3);
		}
		catch(SiglaTrenoException e){
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}		

		System.out.println("\nALTRI TEST: \n");

		try {
			System.out.println("\nTEST SPAZIO NEL MEZZO: \n");
			Treno treno = builderKM.costruisciTreno("Test","h p",userTest, 3); // SPAZIO nel mezzo
			System.out.println("numero vagoni: " + treno.getListaVagoni().size());
		}
		catch(SiglaTrenoException e){
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}	

		try {
			System.out.println("\nTEST SPAZIO ALLA FINE: \n");
			Treno treno = builderKM.costruisciTreno("Test","hp ",userTest, 3); //SPAZIO alla fine
			System.out.println("numero vagoni: " + treno.getListaVagoni().size());
		}
		catch(SiglaTrenoException e){
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}	

		try {
			System.out.println("\nTEST CARATTERE SPECIALE: \n");
			Treno treno = builderKM.costruisciTreno("Test","hp*",userTest, 3); //caratteri speciali
			System.out.println("numero vagoni: " + treno.getListaVagoni().size());
		}
		catch(SiglaTrenoException e){
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}	

		try {
			System.out.println("\nTEST STRINAGA CON SPAZIO: \n");
			Treno treno = builderKM.costruisciTreno("Test"," ",userTest, 3); //stringa con spazio
			System.out.println("numero vagoni: " + treno.getListaVagoni().size());
		}
		catch(SiglaTrenoException e){
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}

		try {
			System.out.println("\nTEST VUOTA: \n");
			Treno treno = builderKM.costruisciTreno("Test"," ",userTest, 3); //stringa vuota
			System.out.println("numero vagoni: " + treno.getListaVagoni().size());
		}
		catch(SiglaTrenoException e){
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}

		try {
			System.out.println("\nCARATTERE MAIUSCOLO: \n");
			Treno treno = builderKM.costruisciTreno("Test","Hp",userTest, 3); //stringa vuota
			System.out.println("numero vagoni: " + treno.getListaVagoni().size());
		}
		catch(SiglaTrenoException e){
			String message = e.getMessage();
			System.out.println(message);
			System.out.println(e.getSuggerimento());
		}


	}
}
