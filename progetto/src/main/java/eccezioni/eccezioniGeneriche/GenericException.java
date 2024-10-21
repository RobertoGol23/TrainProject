package eccezioni.eccezioniGeneriche;

@SuppressWarnings("serial")
public abstract class GenericException extends Exception {

	public GenericException(String message) {
		super(message);
	}

	public abstract String getErrorePerUtente();
	
}
