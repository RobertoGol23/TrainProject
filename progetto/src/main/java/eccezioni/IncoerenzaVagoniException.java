package eccezioni;

public class IncoerenzaVagoniException extends SiglaTrenoException{
    
    public IncoerenzaVagoniException(String sigla, String message) {
		super(sigla, message);
	}

	public String getSuggerimento() {
		return "stringa utilizzata " + getSigla();
	}
}
