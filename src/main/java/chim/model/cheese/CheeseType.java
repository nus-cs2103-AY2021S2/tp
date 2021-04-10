package chim.model.cheese;

import static chim.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Cheese's type in the Cheese Inventory Management System (CHIM).
 * Guarantees: immutable
 */
public class CheeseType {
    public static final String MESSAGE_CONSTRAINTS =
        "Cheese types should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\w]+[\\s&\\w+]*";
    private static final Map<String, CheeseType> typeToCheeseMap = new HashMap<>();

    public final String value;

    /**
     * Constructs a {@code CheeseType}.
     *
     * @param type A valid cheese type.
     */
    private CheeseType(String type) {
        requireNonNull(type);
        checkArgument(isValidType(type), MESSAGE_CONSTRAINTS);
        value = type;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static CheeseType getCheeseType(String type) {
        if (typeToCheeseMap.containsKey(type)) {
            return typeToCheeseMap.get(type);
        }
        CheeseType newCheeseType = new CheeseType(type);
        typeToCheeseMap.put(type, newCheeseType);
        return newCheeseType;
    }

    public static Map<String, CheeseType> getReadOnlyCheeseTypeMap() {
        return Collections.unmodifiableMap(typeToCheeseMap);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof CheeseType // instanceof handles nulls
            && value.equals(((CheeseType) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
