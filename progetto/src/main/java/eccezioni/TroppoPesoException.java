package eccezioni;

public class TroppoPesoException extends SiglaTrenoException {

	public TroppoPesoException(String sigla, String messaggio) {
		super(sigla, messaggio);
	}

	
	@Override
	public String getSuggerimento() {
		return "prova ad inserire meno vagoni";
	}

}
