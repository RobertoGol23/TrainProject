package eccezioni.eccezioniSigla;

@SuppressWarnings("serial")
public class StringaNonValidaException extends SiglaTrenoException {
    
	// Errore se la stringa non contiene i caratteri validi (h, r, p, c)
    public StringaNonValidaException(String sigla, String message) {
		super(sigla, message);
	}

	public String getSuggerimento() {
		return "Stringa utilizzata " + getSigla() + "\nCaratteri validi: h (locomotiva), r (ristorante), p (passeggeri), c (cargo)";
	}
}
