package eccezioni.eccezioniGeneriche;

@SuppressWarnings("serial")
public class MarcaNonValidaException extends Exception {
    
	private String marca;
	
	// Errore se la stringa non contiene i caratteri validi (h, r, p, c)
    public MarcaNonValidaException(String marca, String message) {
		super(message);
		this.marca = marca;
	}

	public String getSuggerimento() {
		return "Numero marca inserita: " + marca + ". Inserisci una marca con numero esistente";
	}
}
