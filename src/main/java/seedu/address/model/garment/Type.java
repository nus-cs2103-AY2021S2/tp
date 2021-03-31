package seedu.address.model.garment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a Garment's DressCode in the wardrobe.
 * Guarantees: immutable; is valid as declared in {@link #isValidType(String)}
 */
public class Type {

    public static final String MESSAGE_CONSTRAINTS = "Type can only take 3 values: upper, lower, footwear";

    public static final String VALIDATION_REGEX = "upper|lower|footwear";

    public static final HashMap<String, List<String>> MATCHES = new HashMap<>();

    public static final List<String> UPPER_MATCHES = Arrays.asList(
            "lower",
            "footwear"
    );

    public static final List<String> LOWER_MATCHES = Arrays.asList(
            "upper",
            "footwear"
    );

    public static final List<String> FOOTWEAR_MATCHES = Arrays.asList(
            "upper",
            "lower"
    );

    public final String value;

    /**
     * Constructs an {@code type}.
     *
     * @param type A valid Type.
     */
    public Type(String type) {
        requireNonNull(type);
        checkArgument(isValidType(type), MESSAGE_CONSTRAINTS);
        value = type;
    }

    /**
     * Returns true if a given string is a valid Type.
     */
    public static boolean isValidType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Initialises the HashMap of types and their respective matching types.
     */
    public static void initialiseMatches() {
        MATCHES.put("upper", UPPER_MATCHES);
        MATCHES.put("lower", LOWER_MATCHES);
        MATCHES.put("footwear", FOOTWEAR_MATCHES);
    }

    public List<String> getMatches() {
        return MATCHES.get(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Type // instanceof handles nulls
                && value.equals(((Type) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}


