package utility;


// TODO creare un "seeder" che inserisce i dati dei servizi direttamente nel database
// TODO mappare i colori??

// design pattern Wrapper
public class Colore {
	private java.awt.Color colore;

	public Colore (int R, int G, int B) {
		this.colore = new java.awt.Color(R,G,B);
	}
	
	public int getR() {return this.colore.getRed();}
	public int getG() {return this.colore.getGreen();}
	public int getB() {return this.colore.getBlue();}
	

	public String toString() {
		return "Colore [R=" + getR() + ", G=" + getG() + ", B=" + getB() + "]";
	}
	
	public void setColore(Colore colore) {
		this.colore = new java.awt.Color(colore.getR(), colore.getG(), colore.getB());
	}
	
}

