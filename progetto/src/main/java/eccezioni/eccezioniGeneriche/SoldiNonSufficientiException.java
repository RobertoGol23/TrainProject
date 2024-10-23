package eccezioni.eccezioniGeneriche;

@SuppressWarnings("serial")
public class SoldiNonSufficientiException extends GenericException {
    
	private Double soldiWallet;
	private Double costoTreno;
	
	// Errore se la stringa non contiene i caratteri validi (h, r, p, c)
    public SoldiNonSufficientiException(Double soldiWallet, Double costoTreno, String message) {
		super(message);
		
		this.setSoldiWallet(soldiWallet);
		this.setCostoTreno(costoTreno);
	}

	public String getSuggerimento() {
		return "Soldi non sufficienti per l'acquisto" +"\nCosto del treno: " + getCostoTreno()+
				"\nSoldi nel wallet: " + getSoldiWallet()
				+ "\nHai bisogno di altri " + (getCostoTreno()-getSoldiWallet()) + " euro per comprare il treno";
	}

	public Double getSoldiWallet() {
		return soldiWallet;
	}

	public void setSoldiWallet(Double soldiWallet) {
		this.soldiWallet = soldiWallet;
	}

	public Double getCostoTreno() {
		return costoTreno;
	}

	public void setCostoTreno(Double costoTreno) {
		this.costoTreno = costoTreno;
	}

	@Override
	public String getErrorePerUtente() {
		
		return "Soldi non sufficienti per l'acquisto" + "<br>" + "Costo del treno: " + getCostoTreno()
				+"<br>"+ "Soldi nel wallet: " + getSoldiWallet()
				 + "<br>" + "Hai bisogno di altri " + (getCostoTreno()-getSoldiWallet()) + " euro per comprare il treno";
	}
	
	
}
