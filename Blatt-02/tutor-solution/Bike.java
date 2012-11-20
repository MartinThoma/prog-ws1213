/**
 * A bike model in Java
 *
 * @author Markus Iser, Martin Thoma
 * @version 1.0
 */

public class Bike {
    private final Gears gears;
    private final Wheels wheels;

    enum Material {
        ALU, STEEL, TITAN
    }

    private final Material material;

    private final String modelId;

    private final boolean hasBell;
    private final boolean hasLights;

    /** Price of the bike in cents. */
    private int price;

    Bike(Gears gears, Wheels wheels, Material material,
            String modelId, boolean bell, boolean lights) {
        this.gears = gears;
        this.wheels = wheels;
        this.material = material;
        switch (material) {
            case ALU:
                price = 20000;
                break;
            case STEEL:
                price = 30000;
                break;
            case TITAN:
                price = 40000;
                break;
        }
        this.modelId = modelId;
        this.hasBell = bell;
        this.hasLights = lights;
    }

    /**
     * @return true if the bike has a hasBell and hasLights
     */
    boolean isStreetLegal() {
        return hasBell && hasLights;
    }

    /**
     * @return the sum of the bike's base-price and the price of the wheels and
     *         gears
     */
    int getPrice() {
        return price + gears.getPrice() + wheels.getPrice();
    }

    /**
     * @return the material of this bike
     */
    public Material getMaterial() {
        return this.material;
    }

    /**
     * @return the model id of this bike
     */
    public String getModelId() {
        return this.modelId;
    }
}

