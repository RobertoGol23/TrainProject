package eccezioni.eccezioniSigla;

@SuppressWarnings("serial")
public class MarcaNonValidaException extends Exception {
    
	private String marca;
	
	// Errore se la stringa non contiene i caratteri validi (h, r, p, c)
    public MarcaNonValidaException(String marca, String message) {
		super(message);
		this.marca = marca;
	}

	public String getSuggerimento() {
		return "Stringa utilizzata " + marca + "\nCaratteri validi: h (locomotiva), r (ristorante), p (passeggeri), c (cargo)";
	}
}
