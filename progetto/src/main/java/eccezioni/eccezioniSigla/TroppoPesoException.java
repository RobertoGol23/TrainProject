package eccezioni.eccezioniSigla;

public class TroppoPesoException extends SiglaTrenoException {


	// Errore se il peso dei vagoni è troppo alto
	public TroppoPesoException(String sigla, String messaggio) {
		super(sigla, messaggio);
	}
	
	@Override
	public String getSuggerimento() {
		return "prova ad inserire meno vagoni";
	}

}
