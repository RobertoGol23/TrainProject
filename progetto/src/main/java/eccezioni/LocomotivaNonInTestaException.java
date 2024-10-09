package eccezioni;

@SuppressWarnings("serial")
public class LocomotivaNonInTestaException extends SiglaTrenoException {

	private String sigla;
	
	
	public LocomotivaNonInTestaException(String sigla, String messaggio)
	{	
		super(sigla,messaggio);
	}


	public String getSigla() {
		return this.sigla;
	}

//
//	public String getSuggerimento()
//	{
//		return "\nSigla utilizzata: " + this.getSigla() +
//				"\nSigla suggerita: H";
//	}
//	
	
		public String getSuggerimento() {
			return "stringa utilizzata " + getSigla() + " stringa suggerita " + "h" +getSigla();
		}
	}


