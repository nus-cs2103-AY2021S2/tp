package seedu.cakecollate.model.orderitem;

import static java.util.Objects.requireNonNull;
import static seedu.cakecollate.commons.util.AppUtil.checkArgument;

/**
 * Represents the type of cake of an order item. Type refers to the description of the order item.
 * Guarantees: immutable; is valid as declared in {@link #isValidType(String)}
 */
public class Type {

    /**
     * The following constraints are shared by {@code Type} and {@code OrderDescription} from the {@code Order} model.
     */
    public static final String SHARED_CONSTRAINTS_MESSAGE =
            "%s should only contain alphabets.";

    public static final String MESSAGE_CONSTRAINTS = String.format(SHARED_CONSTRAINTS_MESSAGE, "Order Type");

    /**
     * Only matches alphabets and white spaces. String cannot start with a space.
     */
    public static final String VALIDATION_REGEX = "^([\\p{Alpha}]|([\\p{Alpha}][\\p{Alpha} ]*))$";

    /**
     * Holds the value of the {@code Type} as specified by the user.
     */
    public final String value;

    /**
     * Constructs a {@code Type}.
     *
     * @param type A valid type.
     */
    public Type(String type) {
        requireNonNull(type);
        checkArgument(isValidType(type), MESSAGE_CONSTRAINTS);
        value = type;
    }


    /**
     * Checks whether the string input by the user is a valid {@code Type} or not. For the string to be valid it must
     * contain only alphabets and must not be blank.
     *
     * @param test String to be checked
     * @return whether the string is valid or not
     */
    public static boolean isValidType(String test) {
        if (test.length() > 0) {
            assert (test.charAt(test.length() - 1) != ' ');
        }
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Returns true if the value of the type is the same regardless of case.
     *
     * @param other Object to compare with
     * @return whether the two are equal (case insensitive)
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Type // instanceof handles nulls
                && value.equalsIgnoreCase(((Type) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public String getValue() {
        return value;
    }
}
