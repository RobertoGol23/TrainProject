package eccezioni.eccezioniGeneriche;

@SuppressWarnings("serial")
public abstract class GenericException extends RuntimeException {

	public GenericException(String message) {
		super(message);
	}

	public abstract String getErrorePerUtente();
	
}
