package seedu.address.testutil;

import seedu.address.model.DietLah;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building DietLah objects.
 * Example usage: <br>
 *     {@code DietLah ab = new DietLahBuilder().withPerson("John", "Doe").build();}
 */
public class DietLahBuilder {

    private DietLah dietLah;

    public DietLahBuilder() {
        dietLah = new DietLah();
    }

    public DietLahBuilder(DietLah dietLah) {
        this.dietLah = dietLah;
    }

    /**
     * Adds a new {@code Person} to the {@code DietLah} that we are building.
     */
    public DietLahBuilder withPerson(Person person) {
        dietLah.addPerson(person);
        return this;
    }

    public DietLah build() {
        return dietLah;
    }
}
