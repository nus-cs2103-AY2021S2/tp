package seedu.address.testutil;

import seedu.address.model.Wardrobe;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Wardrobe objects.
 * Example usage: <br>
 *     {@code Wardrobe ab = new WardrobeBuilder().withPerson("John", "Doe").build();}
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
     * Adds a new {@code Person} to the {@code Wardrobe} that we are building.
     */
    public WardrobeBuilder withPerson(Person person) {
        wardrobe.addPerson(person);
        return this;
    }

    public Wardrobe build() {
        return wardrobe;
    }
}
