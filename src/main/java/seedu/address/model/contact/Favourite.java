package seedu.address.model.contact;

/**
 * Represents whether or not a Contact is favourited in the address book.
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
                && Boolean.compare(isFav, ((Favourite) other).isFav) == 0); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
