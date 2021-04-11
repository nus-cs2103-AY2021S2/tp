package seedu.address.testutil;

import seedu.address.model.DietLah;

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

    public DietLah build() {
        return dietLah;
    }
}
