package seedu.smartlib.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {

    private final String prefix;

    /**
     * The constructor for the Prefix class.
     *
     * @param prefix a given prefix.
     */
    public Prefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Retrieves the prefix associated with this Prefix object.
     *
     * @return the prefix associated with this Prefix object.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Retrieves the prefix associated with this Prefix object, formatted as a String.
     *
     * @return the prefix associated with this Prefix object, formatted as a String.
     */
    public String toString() {
        return getPrefix();
    }

    /**
     * Generates a hashcode for this Prefix object.
     *
     * @return the hashcode for this Prefix object.
     */
    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }

    /**
     * Checks if this Prefix object is equal to another Prefix object.
     *
     * @param obj the other Prefix object to be compared.
     * @return true if this Prefix object is equal to the other Prefix object, and false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Prefix)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Prefix otherPrefix = (Prefix) obj;
        return otherPrefix.getPrefix().equals(getPrefix());
    }

}
