public class Loops {

	private static boolean isTrivialNonPrime(int candidate) {
		return (candidate <= 1);
	}

	public static boolean isPrimeFor(int candidate) {
		if (isTrivialNonPrime(candidate)) {
			return false;
		}

		for (int i = 2; i < candidate; i++) {
			if ((candidate % i) == 0) {
				return false;
			}
		}

		return true;
	}

	public static boolean isPrimeWhile(int candidate) {
		if (isTrivialNonPrime(candidate)) {
			return false;
		}
		
		int i = 2;
		while (i < candidate) {
			if ((candidate % i) == 0) {
				return false;
			}
			i++;
		}
		return true;
	}

	public static void main(String[] args) {
		for (int i = -5; i < 15; ++i) {
			System.out.println(i + ": " + isPrimeWhile(i));
		}

	}
}