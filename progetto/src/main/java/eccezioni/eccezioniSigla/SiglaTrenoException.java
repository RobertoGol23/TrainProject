package eccezioni.eccezioniSigla;

@SuppressWarnings("serial")
public abstract class SiglaTrenoException extends Exception {

	private String sigla;
	
	// Super classe di tutte le eccezioni per la sigla del treno
	public SiglaTrenoException(String sigla, String messaggio) {
		super(messaggio);
		this.sigla = sigla;
	}
	
	public  String getSigla() {
		return this.sigla;
	}
	
	public abstract String getSuggerimento();
	
	public abstract String getErrorePerUtente();
	
}
