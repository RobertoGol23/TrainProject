package eccezioni;

public class TroppeLocomotiveException extends SiglaTrenoException{

    public TroppeLocomotiveException(String sigla, String message) {
		super(sigla, message);
	}

    @Override
    public String getSuggerimento() {
        return "ci possono essere al massimo 2 locomotive, stringa suggerita: h...h";
    }
    
}
