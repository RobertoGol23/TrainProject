package eccezioni;

public class StringaNonValidaException extends SiglaTrenoException {
    
    public StringaNonValidaException(String sigla, String message) {
		super(sigla, message);
	}

	public String getSuggerimento() {
		return "stringa utilizzata " + getSigla() + ". Caratteri validi: h (locomotiva), r (ristorante), p (passeggeri), c (cargo)";
	}
}
