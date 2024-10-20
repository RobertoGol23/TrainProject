package eccezioni.eccezioniSigla;

@SuppressWarnings("serial")
public class IncoerenzaVagoniException extends SiglaTrenoException{
    
	// Errore qualora:
	// insieme ad un vagone cargo ci siano altri tipi di vagoni
	// insieme ad un vagone ristorante non ci siano vagoni passeggeri
    public IncoerenzaVagoniException(String sigla, String message) {
		super(sigla, message);
	}

	public String getSuggerimento() {
		return "Stringa utilizzata " + getSigla();
	}
	
	@Override
	public String getErrorePerUtente() {
		
		return "Assicurati di non mischiare i vagoni cargo con quelli passeggeri";
	}
}
