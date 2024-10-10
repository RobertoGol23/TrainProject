package eccezioni.eccezioniSigla;

@SuppressWarnings("serial")
public class LocomotivaInMezzoException extends SiglaTrenoException{

	// Controlla se ci sono locomotive in mezzo al vagone
	private int posizione;
	public LocomotivaInMezzoException(String sigla, String messaggio, int posizione) {
		super(sigla, messaggio);
		this.posizione = posizione;
	}
	
	public String getSuggerimento() {
		String duplicato = this.getSigla().substring(0, posizione) + this.getSigla().substring(posizione+1, this.getSigla().length());
		return "Stringa utilizzata " + this.getSigla() + "\nStringa suggerita " +duplicato;
	}
}
