package Velo.test;

import Velo.Bike;
import Velo.BikeFactory;
import Velo.Gears;
import Velo.Wheels;

public class TestVelo {
	
	
	public static void main(String[] args) { 
		Bike cByke, mByke, rByke;
		
		BikeFactory factory = new BikeFactory();
		
		cByke = factory.createCityBike();
		mByke = factory.createMountainBike();
		rByke = factory.createRoadBike();
		
		Gears gears = new Gears(3, 1, 50);
		Wheels wheels = new Wheels(559, 50, 100);
	}


}
