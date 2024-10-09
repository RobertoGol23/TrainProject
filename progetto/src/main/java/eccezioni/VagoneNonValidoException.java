package eccezioni;

@SuppressWarnings("serial")
public class VagoneNonValidoException extends SiglaTrenoException {
	private int posizione;
	public VagoneNonValidoException(String sigla, String messaggio, int posizione) {
		super(sigla, messaggio);
		this.posizione = posizione;
	}
	
	public String getSuggerimento() {
		String duplicato = this.getSigla().substring(0, posizione) + this.getSigla().substring(posizione+1, this.getSigla().length());
		return "Stringa utilizzata " + this.getSigla() + "\nStringa suggerita " +duplicato;
	}
}
