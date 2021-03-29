package seedu.address.model.person;

/**
 * Represents whether or not a Person is favourited in the address book.
 */
public class Favourite {
    private String value;
    private boolean isFav;

    /**
     * Constructs a {@code Favourite}.
     *
     * @param value A valid favourite value.
     */
    public Favourite(String value) {
        this.value = value;
        isFav = Boolean.parseBoolean(value);
    }

    @Override
    public String toString() {
        return value;
    }

    public boolean isFav() {
        return isFav;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Favourite // instanceof handles nulls
                && value.equals(((Favourite) other).value)); // state check
    }
}
