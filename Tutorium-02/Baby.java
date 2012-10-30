public class Baby {
    /** Der Vor- und Nachname des Babys */
    public String name;

    /** Gewicht in Gramm */
    public int weight;

    /** Gr��e in cm */
    public int size;

    /** Lautst�rke in dB */
    public float loudness;
    
    /** Geschlecht */
    public Sex sex;

	public Baby(int weight, int size, float loudness) {
		super();
		this.weight = weight;
		this.size = size;
		this.loudness = loudness;
	}
	
	public Baby(int weight, int size, float loudness, Sex sex) {
		super();
		this.weight = weight;
		this.size = size;
		this.loudness = loudness;
		
		if(sex == Sex.FEMALE) {
			this.name = "Anna";
		} else {
			this.name = "Bob";
		}
	}
}
