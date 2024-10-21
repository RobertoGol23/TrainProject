package eccezioni.eccezioniGeneriche;

@SuppressWarnings("serial")
public class VagoneNonRiconosciutoException extends GenericException {
    
	
	// Errore se la stringa non contiene i caratteri validi (h, r, p, c)
    public VagoneNonRiconosciutoException(String messaggio) {
		super(messaggio);
	}
    
    public String message()
    {
    	return "Nessun dettaglio, tipo del vagone non definito";
    }

	@Override
	public String getErrorePerUtente() {

		return "Nessun dettaglio, tipo del vagone non definito";
	}
}
