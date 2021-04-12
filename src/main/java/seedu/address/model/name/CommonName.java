package seedu.address.model.name;

public class CommonName {
    protected String fullName;

    /**
     * Returns {@code String} representation of this {@code CommonName}.
     */
    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommonName // instanceof handles nulls
                && fullName.equals(((CommonName) other).fullName)); // state check
    }

    /**
     * Returns hashCode of this {@code CommonName}.
     */
    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}
