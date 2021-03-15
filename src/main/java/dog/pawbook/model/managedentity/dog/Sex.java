package dog.pawbook.model.managedentity.dog;

import static java.util.Objects.requireNonNull;

public class Sex {

    private final String value;

    /**
     * Constructs an {@code Sex}.
     *
     * @param gender A valid sex.
     */
    public Sex(String sex) {
        requireNonNull(sex);
        value = sex;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Sex // instanceof handles nulls
                && value.equals(((Sex) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
