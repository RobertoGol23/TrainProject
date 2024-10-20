package eccezioni.eccezioniSigla;

@SuppressWarnings("serial")
public class TroppeLocomotiveException extends SiglaTrenoException{

    // Errore se ci sono più di 2 locomotive nel treno
    public TroppeLocomotiveException(String sigla, String message) {
		super(sigla, message);
	}

    @Override
    public String getSuggerimento() {
        return "ci possono essere al massimo 2 locomotive, stringa suggerita: h...h";
    }
    
    @Override
	public String getErrorePerUtente() {
		
		return "Sono state inserite troppe locomotive, il massimo e' 2!";
	}
}
