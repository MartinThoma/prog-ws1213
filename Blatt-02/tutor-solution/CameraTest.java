import org.junit.Test;
import org.junit.runner.JUnitCore;
import static org.junit.Assert.assertTrue;

public class CameraTest {
	
	public CameraTest() {
	}
	
	@Test(timeout = 10000)
	public void testAllCombinations() {
		byte steps = 7;
		Objective obj;
		
		for (byte max = 0; max < steps; max++) {
			for (byte init = 0; init < steps; init++) {
				obj = new Objective(steps, max, init);
				Camera cam = new Camera(obj);
				cam.autofocus();
				assertTrue(obj.toString(), obj.gotMaximum());
			}
		}
	}
	
	public static void main(String[] args) {
		JUnitCore.main("CameraTest");
	}
}
