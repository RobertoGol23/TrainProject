package eccezioni.eccezioniGeneriche;

import java.sql.SQLIntegrityConstraintViolationException;

@SuppressWarnings("serial")
public class ServizioGiaPresenteException extends SQLIntegrityConstraintViolationException {
    
	
	// Errore se la stringa non contiene i caratteri validi (h, r, p, c)
    public ServizioGiaPresenteException() {
		super();
	}
    
    public String message()
    {
    	return "Servizio già presente nel database";
    }
}
