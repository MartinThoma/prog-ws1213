/**
 * Represents an animal
 *
 */
public class Animal {
	/** Haarfarbe des Tieres */
	private HairColor hairColor;
	
	/** L‰nge von Kopf bis Schwanz in cm */
	private int length;

	/**
	 * Setze L‰nge
	 * @param amount L‰nge
	 */
	public void setLength(int amount){
		length = amount;
	}
	
	// Das ist kein JavaDoc-Kommentar und wird von Eclipse nicht angezeigt
	public int getLength() {
		return length;
	}

	/*
	 * Auch kein JavaDoc-Kommentar!
	 */
	public HairColor getHairColor() {
		return hairColor;
	}

	public void setHairColor(HairColor hairColor) {
		// hier muss ein "this" stehen, damit Java weiﬂ, ob der 
		// Parameter "hairColor" (und nicht das Attribut "hairColor") gemeint ist.
		this.hairColor = hairColor;
	}
}
