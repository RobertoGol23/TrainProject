package eccezioni.eccezioniGeneriche;

import jakarta.persistence.NoResultException;

@SuppressWarnings("serial")
public class UtenteNonTrovatoException extends NoResultException {
    
	
	// Errore se la stringa non contiene i caratteri validi (h, r, p, c)
    public UtenteNonTrovatoException() {
		super();
	}
    
    public String message()
    {
    	return "Utente non trovato";
    }
}
