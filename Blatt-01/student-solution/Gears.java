package Velo;

/**
 * Die vorderen Kettenr�der und die Ritzel hinten
 * modellieren die Gangschaltung eines Fahrrads. 
 * Die Anzahl der vorederen Kettenr�der ist beschr�nkt von 1 bis 3 
 * und die Anzahl der Ritzel hinten ist beschr�nkt von 1 bis 10.
 *
 */
public class Gears {

	static final int MIN_CHAINWHEEL_NR = 1;
	static final int MAX_CHAINWHEEL_NR = 3;
	static final int MIN_RITZL_NR = 1;
	static final int MAX_RITZL_NR = 10;

	private int chainWheelNr;
	private int ritzNr;
	private int price;

	/**
	 * Konstruktor f�r "Gears".
	 * @param chainWheelNr
	 * @param ritzNr
	 * @param price
	 */
	public Gears(int chainWheelNr, int ritzNr, int price) {
		this.setChainWheelNr(chainWheelNr);
		this.setRitzNr(ritzNr);
		this.price = price;
	}

	public int getChainWheelNr() {
		return chainWheelNr;
	}

	public void setChainWheelNr(int chainWheelNr) {
		if (chainWheelNr >= MIN_CHAINWHEEL_NR
				&& chainWheelNr <= MAX_CHAINWHEEL_NR) {
			this.chainWheelNr = chainWheelNr;
		} else {
			System.out.println(chainWheelNr
			+ "als Anzahl der Kettenr�der ist nicht g�ltig." +
			" Die Anzahl muss min 1 und max 3 betragen. Bitte Wert �ndern.");
		}
	}

	public int getRitzNr() {
		return ritzNr;
	}

	public void setRitzNr(int ritzNr) {
		if (ritzNr >= MIN_RITZL_NR && ritzNr <= MAX_RITZL_NR) {
			this.ritzNr = ritzNr;
		} else {
			System.out.println(ritzNr
			+ " als Anzahl der Ritzl ist nicht g�ltig." +
			" Die Anzahl muss min 1 und max 10 betragen. Bitte Wert �ndern.");
		}
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNumberOfGears() {
		return this.getChainWheelNr() * this.getRitzNr();
	}

}
