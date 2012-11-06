package Velo;

/**
 * Der Felgendurchmesser und die Reifenst�rke modellieren
 * die R�der eines Fahrrads. 
 * Der Felgendurchmesser betr�gt maximal 700mm
 * und die Reifenst�rke betr�gt maximal 60mm.
 * 
 */
public class Wheels {

	static final int MIN_RIMDIAMETER = 0;
	static final int MAX_RIMDIAMETER = 700;
	static final double MIN_TIREWIDTH = 0.0;
	static final double MAX_TIREWIDTH = 60.0;

	/**
	 * Konstruktor f�r "Wheels".
	 * 
	 * @param rimDiameter
	 * @param tireWidth
	 * @param price
	 */
	public Wheels(int rimDiameter, double tireWidth, int price) {
		this.rimDiameter = rimDiameter;
		this.tireWidth = tireWidth;
		this.price = price;
	}

	private int rimDiameter;
	private double tireWidth;
	private int price;

	public int getRimDiameter() {
		return rimDiameter;
	}

	public void setRimDiameter(int zB_rimDiameter) {
		if (zB_rimDiameter >= MIN_RIMDIAMETER
				&& zB_rimDiameter <= MAX_RIMDIAMETER) {
			this.rimDiameter = zB_rimDiameter;
		} else {
			System.out.println(zB_rimDiameter
		    + "als Wert ung�ltig. Maximaler Feldendurschmeeser " +
		      "betr�gt 700mm. Bitte Wert �ndern.");
		}
	}

	public double getTireWidth() {
		return tireWidth;
	}

	public void setTireWidth(double w) {
		if (w >= MIN_TIREWIDTH && w <= MAX_TIREWIDTH) {
			this.setTireWidth(w);
		} else {
			System.out.println(w
			+ " als Wert ung�ltig. Maximalee Reifenst�rke " +
			  "betr�gt 60mm. Bitte Wert �ndern.");
		}
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
