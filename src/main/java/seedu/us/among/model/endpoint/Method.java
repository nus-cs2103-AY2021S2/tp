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

    public final String methodName;
    public final MethodType methodType;

    /**
     * Constructs a {@code Method}.
     * @param method A valid method name.
     */
    public Method(String method) {
        requireNonNull(method);
        checkArgument(isValidMethod(method), MESSAGE_CONSTRAINTS);
        this.methodName = method.toUpperCase();
        this.methodType = parseMethodType(methodName);
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

    /**
     * Returns the MethodType if the given string is a valid method. Else returns null.
     * @param test A valid method
     * @return a MethodType enum
     */
    public MethodType parseMethodType(String test) {
        for (MethodType e : MethodType.values()) {
            if (e.name().equalsIgnoreCase(test)) {
                return e;
            }
        }
        return null;
    }
    
    public MethodType getMethodType() {
        return this.methodType;
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
