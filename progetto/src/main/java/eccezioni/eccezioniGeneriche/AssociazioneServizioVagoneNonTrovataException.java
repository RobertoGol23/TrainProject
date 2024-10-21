package eccezioni.eccezioniGeneriche;

@SuppressWarnings("serial")
public class AssociazioneServizioVagoneNonTrovataException extends GenericException {
 
	/**
	 * Eccezione lanciata quando viene ricercata nel database la coppia
	 * servizio/vagone e non viene trovato nulla
	 * @param message
	 */
    public AssociazioneServizioVagoneNonTrovataException(String message) {
		super(message);
	}
	
	@Override
	public String getErrorePerUtente() {
		
		return "La coppia di valori di Servizio e Vagone non è stata trovata nella tabella 'servizi_utilizzati'";
	}
}
