package seedu.us.among.model.endpoint;

import static java.util.Objects.requireNonNull;
import static seedu.us.among.commons.util.AppUtil.checkArgument;

import org.apache.commons.lang3.EnumUtils;

/**
 * Represents a Endpoint's Method in the address book. Guarantees: immutable; is
 * valid as declared in {@link #isValidMethod(String)}
 */
public class Method {

    public static final String MESSAGE_CONSTRAINTS = "Methods only consists of GET, POST, PUT, DELETE, HEAD, OPTIONS and PATCH ";

    /*
     * The first character of the address must not be a whitespace, otherwise " " (a
     * blank string) becomes a valid input.
     */
    // public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    // public static final String VALIDATION_REGEX = "(\\bget\\b)|(\\bpost\\b)|(\\bout\\b)|(\\bdelete\\b)|(\\bhead\\b)|(\\boptions\\b)|(\\bpatch\\b)";

    public final String methodName;
    // public final MethodType methodType;

    /**
     * Constructs a {@code Method}.
     *
     * @param method A valid method name.
     */
    public Method(String method) {
        requireNonNull(method);
        checkArgument(isValidMethod(method), MESSAGE_CONSTRAINTS);
        methodName = method.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid method.
     */
    public static boolean isValidMethod(String test) {
        // return test.matches(VALIDATION_REGEX);
        for (MethodType e : MethodType.values()) {
            if (e.name().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return methodName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Method // instanceof handles nulls
                        && methodName.equals(((Method) other).methodName)); // state check
    }

    @Override
    public int hashCode() {
        return methodName.hashCode();
    }

}
