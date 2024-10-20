package eccezioni.eccezioniGeneriche;

@SuppressWarnings("serial")
public class MarcaNonValidaException extends GenericException {
    
	private String marca;
	
	// Errore se la stringa non contiene i caratteri validi (h, r, p, c)
    public MarcaNonValidaException(String marca, String message) {
		super(message);
		this.marca = marca;
	}

	public String getSuggerimento() {
		return "Numero marca inserita: " + marca + ". Inserisci una marca con numero esistente";
	}
	
	@Override
	public String getErrorePerUtente() {
		
		return "La marca non è valida!";
	}
}
