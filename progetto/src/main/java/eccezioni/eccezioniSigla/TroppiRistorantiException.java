package eccezioni.eccezioniSigla;

@SuppressWarnings("serial")
public class TroppiRistorantiException extends SiglaTrenoException {
	
	private int posizione;
	
	//Errore se c'è più di un ristorante'
	public TroppiRistorantiException(String sigla, String messaggio, int posizione)
	{	
		super(sigla,messaggio);
		this.posizione = posizione;
	}

	
		public String getSuggerimento() {
			String duplicato = this.getSigla().substring(0, posizione) + this.getSigla().substring(posizione+1, this.getSigla().length());
			return "Stringa utilizzata: " + this.getSigla() + "\nStringa suggerita " +duplicato;
		}
	}


