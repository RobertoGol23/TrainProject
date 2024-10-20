package eccezioni.eccezioniSigla;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class RistoranteNonInMezzoException extends SiglaTrenoException
{
	
	public RistoranteNonInMezzoException(String sigla, String messaggio)
	{	
		super(sigla,messaggio);
	}
	
	public String getSuggerimento() {
		
		int dim = getSigla().length();
		ArrayList<Character> siglaVecchia = new ArrayList<Character>();
		
		for(int j=0; j<dim;j++)
		{
			siglaVecchia.add(getSigla().charAt(j));
		}
				
		if(siglaVecchia.contains('r'))
		{
			int index = siglaVecchia.indexOf('r');
			siglaVecchia.remove(index);
		}

		
		if(((siglaVecchia.size()+1)%2)==0) // h c _ c c
		{
			int posizione = siglaVecchia.size()/2;
			//System.out.println("posizione pari: "+posizione);
			posizione = posizione+1;
			siglaVecchia.add(posizione,'r');
		}
		else
		{
			int posizione = (siglaVecchia.size())/2;
			//System.out.println("posizione dispari: "+posizione);
			siglaVecchia.add(posizione,'r');
		}

		String siglaNuova = "";
		
		for(int i=0;i<siglaVecchia.size();i++)
		{
			siglaNuova += siglaVecchia.get(i).toString();
		}
		
		return "Stringa utilizzata: " + getSigla() + "\nStringa suggerita " + siglaNuova;
	}

	@Override
	public String getErrorePerUtente() {
		
		return "Il ristorante deve trovarsi in posizione centrale";
	}
	
	
}


