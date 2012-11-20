/**
 * A bike model in Java. 
 * This class models the bicycle's gears, restricted to derailleur gears.
 *
 * @author Markus Iser
 * @version 1.2
 */

class Gears {
	/**
	 * Indicates the number of front sprockets (range 1 to 3)
	 */
	int frontSprockets;
	/**
	 * Indicates the number of rear sprockets (range 1 to 9)
	 */
	int rearSprockets;
	
	/**
	 * Indicates the price of the gears
	 */
	int price;
	
	Gears(int frontSprockets, int rearSprockets, int price) {
		this.frontSprockets = frontSprockets;
		this.rearSprockets = rearSprockets;
		this.price = price;
	}
	
	/**
	 * @return the number of gears
	 */
	int getNumberOfGears() {
		return frontSprockets * rearSprockets;
	}
	
	/**
	 * @return the price of the gears
	 */
	int getPrice() {
		return price;
	}
}	
