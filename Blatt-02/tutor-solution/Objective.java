/**
 * A model of a camera dedicated to the autofocus-exercise.
 * The camera has a fixed number of focus-steps. 
 * The constructor initializes each focus-step with a fixed contrast value (the test data). 
 * The class simulates the fact that moving the lens has an effect on the contrast. 
 * 
 * @author Markus Iser
 * @version 1.2
 */ 

public class Objective {
	private byte numberOfSteps;
	private byte contrastMaxPos;
	private byte initPos;
	
	/**
	 * The current position of the lens
	 */ 
	protected byte focusPos;
	/**
	 * The test data, stores contrast values for all lens-positions
	 */
	protected double[] contrast;
	
	/**
	 * Default constructor uses some default data to initialize the camera
	 */
	public Objective() {
		this((byte) 7, (byte) 3, (byte) 1);
	}
	
	/**
	 * This constructor uses the given data to initialize the camera. 
	 * Makes sure that contrastMaxPos and initPos are smaller than numberOfSteps. 
	 * @param numberOfSteps the number of possible lens-positions
	 * @param contrastMaxPos the lens-position where the contrast maximum should reside
	 * @param initPos the initial lens-position
	 */
	public Objective(byte numberOfSteps, byte contrastMaxPos, byte initPos) {
		this.numberOfSteps = numberOfSteps;
		this.contrastMaxPos = (byte) Math.min(contrastMaxPos, numberOfSteps - 1);
		this.initPos = (byte) Math.min(initPos, numberOfSteps - 1);
		initTestData();
	}
	
	/**
	 * Initializes the camera with some test-data
	 */
	private void initTestData() {
		focusPos = initPos;
		contrast = new double[numberOfSteps];
		
		// a rough approximation of the bell curve with a variance of 1.7 and a mean specified by contrastMaxPos
		for (int i = 0; i < numberOfSteps; i++) {
			contrast[i] = 4 / Math.pow(Math.E, 0.5 * Math.pow((i - contrastMaxPos) / 1.7, 2));
		}
	}
	
	/**
	 * Prints the current state to the console
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();		
		buf.append("Maximum is at " + contrastMaxPos + " but lens position is " + focusPos);
		buf.append(" (width=" + numberOfSteps + ", initial position=" + initPos + ")");
		return buf.toString();
	}
	
	/**
	 * Initializes an Objective using the given arguments and prints the generated test-data
	 */
	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Usage: Objective nofSteps contrastMaxPos initPos");
			System.exit(1);
		}
		Objective obj = new Objective(Byte.parseByte(args[0]), Byte.parseByte(args[1]), Byte.parseByte(args[2]));
		System.out.println(obj.toString());
	}
	
	/**
	 * Method for automated test
	 */
	public boolean gotMaximum() {
		return contrastMaxPos == focusPos;
	}
	
	/**
	 * @return The contrast of the current camera image
	 */
	public double getContrast() {
		return contrast[focusPos];
	}
	
	/**
	 * Moves the focus-motor to the left
	 */
	public void stepLeft() {
		if (focusPos > 0) {
			focusPos--;
		}
	}
	
	/**
	 * Moves the focus-motor to the right
	 */
	public void stepRight() {
		if (focusPos < numberOfSteps - 1) {
			focusPos++;
		}
	}
}
