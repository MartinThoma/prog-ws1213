package Velo;

/**
 * @author anonym Hilfsklasse um bestimmte Objekte der Klasse "Bike" zu
 *         erzeugen.
 */
public class BikeFactory {

	public Bike createCityBike() {
		Gears gears = new Gears(3, 1, 50);
		Wheels wheels = new Wheels(559, 50, 100);
		Bike bike = new Bike(gears, wheels, Frame.Stahl, "CB105", 300, true, true);
		return bike;
	}

	public Bike createRoadBike() {
		Gears gears = new Gears(3, 9, 150);
		Wheels wheels = new Wheels(622, 23, 100);
		Bike bike = new Bike(gears, wheels, Frame.Titan, "RB87", 400, false, false);
		return bike;
	}

	public Bike createMountainBike() {
		Gears gears = new Gears(3, 9, 150);
		Wheels wheels = new Wheels(559, 50, 100);
	    Bike bike = new Bike(gears, wheels, Frame.Alu, "MTBX13b", 200, false, false);
		return bike;
	}
	
	public static void main(String[] args) {
		
	}
	
}

