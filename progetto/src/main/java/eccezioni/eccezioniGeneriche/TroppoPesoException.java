package eccezioni.eccezioniGeneriche;

@SuppressWarnings("serial")
public class TroppoPesoException extends GenericException {
	private String sigla;

	// Errore se il peso dei vagoni è troppo alto
	public TroppoPesoException(String sigla, String messaggio) {
		super(messaggio);
		this.sigla = sigla;
	}
	
	public String getSuggerimento() {
		return "La sigla " + sigla + " contiene troppi vagoni, il peso trasportato supera quello trasportabile.";
	}
	
	@Override
	public String getErrorePerUtente() {
		
		return "Il treno contiene troppi vagoni! Assicurati che la locomotiva possa trasportarli";
	}

}
