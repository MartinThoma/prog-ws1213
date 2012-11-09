/**
 * A bike model in Java. 
 * This class models the bicycle-wheels. 
 *
 * @author Markus Iser
 * @version 1.2
 */

class Wheels {
	/**
	 * Indicates the diameter of the rims (range 150 to 700)
	 */
	int diameter;
	/**
	 * Indicates the size of the wheels (range 20 to 60)
	 */
	double wheelsSize;
	
	/**
	 * Indicates the price of the wheels
	 */
	int price;
	
	Wheels(int diameter, double wheelSize, int price) {
		this.diameter = diameter;
		this.wheelsSize = wheelSize;
		this.price = price;
	}
	
	/**
	 * @return the price of the wheels
	 */
	int getPrice() {
		return price;
	}
}
