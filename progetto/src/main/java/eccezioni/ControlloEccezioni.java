package eccezioni;

public class ControlloEccezioni {

	public ControlloEccezioni() {
	}

	public static Boolean controllaRistoranteCentrale(String sigla)
	{
		Boolean flag = false;
		
		if((sigla.length()%2)==0) //pari,  A A A R(3) R(4) A A A
		{
			int posizione1 = sigla.length()/2;
			int posizione2 = posizione1-1;
			if(sigla.charAt(posizione1) == 'r' || sigla.charAt(posizione1) == 'R'
				|| sigla.charAt(posizione2) == 'r' || sigla.charAt(posizione2) == 'R')
			{
				flag = true;
			}
		}
		else // A A A A R(4) A A A A
		{
			int posizione = (sigla.length()-1)/2;
			if(sigla.charAt(posizione) == 'r' || sigla.charAt(posizione) == 'R')
			{
				flag = true;
			}
		}
		
		return flag;
	}
	
	
}
