package Velo;

/**
 *  
 * "Bike" modelliert ein Fahrrad. 
 * Notwendig dafür sind:
 * Gangschaltung und Bereifung, Rahmenmaterial, Modellname, 
 * Zubehör und Preis.
 */
public class Bike {

	private Gears gears;
	private Wheels wheels;
	private Frame frameMaterial;
	private String modelName;
	private int price;
	private boolean ring;
	private boolean light;

	/**
	 * Konstruktor für "Bike".
	 * 
	 * @param gears
	 * @param wheels
	 * @param frameMaterial
	 * @param modelName
	 * @param price
	 * @param ring
	 * @param light
	 */
	public Bike(Gears gears, Wheels wheels, Frame frameMaterial,
			String modelName, int price, boolean ring, boolean light) {
		this.gears = gears;
		this.wheels = wheels;
		this.frameMaterial = frameMaterial;
		this.modelName = modelName;
		this.price = price;
		this.ring = ring;
		this.light = light;
	}

	/**
	 * Gibt den Gesamtpreis zurck. Returns
	 */
	public int getPrice() {
		return this.price + gears.getPrice() + wheels.getPrice();
	}

	/**
	 * Prüft ob ein Fahrrad für den Verkehr geeignet ist.
	 * 
	 * @return boolean
	 */
	public boolean isStreetLegal() {
		boolean b = false;
		if (this.light && this.ring) {
			b = true;
		}
		return b;
	}

}
