package eccezioni;

@SuppressWarnings("serial")
public abstract class SiglaTrenoException extends Exception {

	private String sigla;
	
	public SiglaTrenoException(String sigla, String messaggio) {
		super(messaggio);
		this.sigla = sigla;
	}
	
	public  String getSigla() {
		return this.sigla;
	}
	
	public abstract String getSuggerimento();
}
