package eccezioni.eccezioniSigla;

public class TroppeLocomotiveException extends SiglaTrenoException{

    // Errore se ci sono più di 2 locomotive nel treno
    public TroppeLocomotiveException(String sigla, String message) {
		super(sigla, message);
	}

    @Override
    public String getSuggerimento() {
        return "ci possono essere al massimo 2 locomotive, stringa suggerita: h...h";
    }
    
}
