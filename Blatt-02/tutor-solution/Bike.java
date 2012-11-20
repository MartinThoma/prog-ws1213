/**
 * A bike model in Java
 *
 * @author Markus Iser
 * @version 1.0
 */

class Bike {
	Gears gears;
	Wheels wheels; 
	
	enum Material { ALU, STEEL, TITAN }; 
	Material material;
	
	String modelId;
	
	boolean bell;
	boolean lights;
	
	/*
	 * Der Preis ist in Cents modelliert, um eine Ganzzahldarstellung zu ermöglichen.
	 * Dadurch vermeidet man die Präzisionsprobleme der Fließkommadarstellung. 
	 */
	int price;
		
	Bike(Gears gears, Wheels wheels, Material material, String modelId, boolean bell, boolean lights) {
		this.gears = gears;
		this.wheels = wheels;
		this.material = material;
		switch (material) {
			case ALU: price = 20000; break;
			case STEEL: price = 30000; break;
			case TITAN: price = 40000; break;
		}
		this.modelId = modelId;
		this.bell = bell;
		this.lights = lights;
		this.price = price;
	}
	
	/**
	 * @return true if the bike has a bell and lights
	 */
	boolean isStreetLegal() {
		return bell && lights;
	}
	
	/**
	 * @return the sum of the bike's base-price and the price of the wheels and gears
	 */
	int getPrice() {
		return price + gears.getPrice() + wheels.getPrice();
	}
}
