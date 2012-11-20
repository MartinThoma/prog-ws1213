/**
 * This class calculates the first 37 numbers of the tribonacci sequence
 * 
 * @author Markus Iser
 * @version 1.0
 */
class Tribonacci {
	/**
	 * main
	 */
	public static void main(String[] args) {
		int a = 1, b = 1, c = 1;
		for (int i = 4; i <= 37; i++) {
			int d = a + b + c;
			a = b;
			b = c;
			c = d;
		}
		System.out.println(c);
	}
}
