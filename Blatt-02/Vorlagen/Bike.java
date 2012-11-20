/**
 * A bike model in Java
 *
 * @author Markus Iser
 * @version 1.2
 */

class Bike {
	/**
	 * The gears of the bike
	 */
	Gears gears;
	/**
	 * The wheels of the bike
	 */
	Wheels wheels; 
	
	/**
	 * local enum provides the possible frame materials
	 */
	enum Material { ALU, STEEL, TITAN }; 
	
	/**
	 * Indicates the material of the frame
	 */
	Material material;
	
	/**
	 * Indicates the model id
	 */
	String modelId;
	
	/**
	 * Indicates whether or not the bike has a bell
	 */
	boolean bell;
	/**
	 * Indicates whether or not the bike has lights
	 */
	boolean lights;
	
	/**
	 * Indicates the price of the Bike
	 */
	int price;
		
	Bike(Gears gears, Wheels wheels, Material material, String modelId, boolean bell, boolean lights, int price) {
		this.gears = gears;
		this.wheels = wheels;
		this.material = material;
		this.modelId = modelId;
		this.bell = bell;
		this.lights = lights;
		this.price = price;
	}
	
	/**
	 * @return true if the bike has bell and lights, false otherwise
	 */
	boolean isStreetLegal() {
		return bell && lights;
	}
	
	/**
	 * @return the total price of the bike and its components
	 */
	int getPrice() {
		return price + gears.getPrice() + wheels.getPrice();
	}
}
