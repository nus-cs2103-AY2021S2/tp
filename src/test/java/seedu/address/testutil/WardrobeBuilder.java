package seedu.address.testutil;

import seedu.address.model.Wardrobe;
import seedu.address.model.garment.Garment;

/**
 * A utility class to help with building Wardrobe objects.
 * Example usage: <br>
 *     {@code Wardrobe ab = new WardrobeBuilder().withGarment("John", "Doe").build();}
 */
public class WardrobeBuilder {

    private Wardrobe wardrobe;

    public WardrobeBuilder() {
        wardrobe = new Wardrobe();
    }

    public WardrobeBuilder(Wardrobe wardrobe) {
        this.wardrobe = wardrobe;
    }

    /**
     * Adds a new {@code Garment} to the {@code Wardrobe} that we are building.
     */
    public WardrobeBuilder withGarment(Garment garment) {
        wardrobe.addGarment(garment);
        return this;
    }

    public Wardrobe build() {
        return wardrobe;
    }
}
