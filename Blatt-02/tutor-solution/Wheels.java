/**
 * A bike model in Java. This class models the wheels. 
 *
 * @author Markus Iser
 * @version 1.0
 */

class Wheels {
	int diameter = 559; // range 150 to 700
	double wheelsSize = 50; // range 20 to 60
	
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
