/**
 * A bike model in Java. 
 * This class models the gears (restricted to derailleur gears)
 *
 * @author Markus Iser
 * @version 1.0
 */

class Gears {
	int sprockets = 3; //Kettenräder vorne, range 1 to 3
	int rearSprockets = 9; //Kettenräder hinten, range 1 to 10
	
	int price;
	
	Gears(int sprockets, int rearSprockets, int price) {
		this.sprockets = sprockets;
		this.rearSprockets = rearSprockets;
		this.price = price;
	}
	
	/**
	 * sets the sprocket numbers, uses default-values if consisteny criteria are not met
	 */
	void setSprockets(int sprockets, int rearSprockets) {
		this.sprockets = sprockets;
		this.rearSprockets = rearSprockets;
		if (this.sprockets < 1) {
			this.sprockets = 1;
		}
		else if (this.sprockets > 3) {
			this.sprockets = 3;
		}
		if (this.rearSprockets < 1 || this.rearSprockets > 9) {
			this.rearSprockets = this.sprockets * 3;
		}
		if (this.rearSprockets < this.sprockets) {
			this.rearSprockets = this.sprockets;
		} 
		else if (this.rearSprockets > 3 * this.sprockets) {
			this.rearSprockets = 3 * this.sprockets;
		}
	}
	
	/**
	 * @return the number of gears as the number of sprocket-combinations
	 */
	int getNumberOfGears() {
		return sprockets * rearSprockets;
	}
	
	/**
	 * @return the price of the gears
	 */
	int getPrice() {
		return price;
	}
}	
