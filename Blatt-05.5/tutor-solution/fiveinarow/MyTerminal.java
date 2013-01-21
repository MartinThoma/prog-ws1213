package fiveinarow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class provides simple methods for reading user input from a terminal.
 * 
 * @author P. Pepper und Gruppe
 * @version 1.1 Modifiziert von A. Lochbihler
 * @version 1.2 Modifiziert von F. Kelbert
 * @version XS Reduziert von F. Merz, M. Iser
 */
public final class MyTerminal {

	private static final String ERROR = "Error! ";
	private static final String ERROR_INVALID_INT = "Ungueltige Integer-Zahl! (Nochmal eingeben) ";

	private static BufferedReader in = new BufferedReader(
			new InputStreamReader(System.in));

	private MyTerminal() {
	}

	/**
	 * Prompts the user to enter a string and returns it.
	 * 
	 * @param prompt
	 *            The prompt that will be shown to the user.
	 * @return The read string.
	 */
	public static String askString(String prompt) {
		System.out.print(prompt);
		return readString();
	}

	/**
	 * Prompts the user to enter a number and returns the number.
	 * 
	 * @param prompt
	 *            The prompt that will be shown to the user.
	 * @return The read number.
	 */
	public static int askInt(String prompt) {
		System.out.print(prompt);
		return readInt();
	}

	private static String readString() {
		try {
			return in.readLine();
		} catch (IOException e) {
			throw new Error(e);
		}
	}

	private static int readInt() {
		while (true) {
			for (String token : getTokens()) {
				try {
					return Integer.parseInt(token);
				} catch (NumberFormatException e) {
					System.err.println(ERROR_INVALID_INT);
				}
			}
		}
	}

	private static String[] getTokens() {
		String line;
		try {
			line = in.readLine();
		} catch (IOException e) {
			throw new Error(e);
		}

		if (line == null) {
			System.err.println(ERROR);
			return null;
		} else {
			return line.split("\\s");
		}
	}
}