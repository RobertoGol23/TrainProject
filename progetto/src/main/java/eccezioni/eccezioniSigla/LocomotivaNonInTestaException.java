package eccezioni.eccezioniSigla;

@SuppressWarnings("serial")
public class LocomotivaNonInTestaException extends SiglaTrenoException {
	
	// Errore se la locomotiva non è in testa
	public LocomotivaNonInTestaException(String sigla, String messaggio)
	{	
		super(sigla,messaggio);
	}
	
	public String getSuggerimento() {
			return "Stringa utilizzata " + getSigla() + "\nstringa suggerita " + "h" + getSigla();
		}
	}


