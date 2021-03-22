package seedu.address.model.property;

import java.util.function.Predicate;

/**
 * Tests that a {@code Property}'s {@code Type} us the same as the given {@code type}
 */
public class PropertyTypePredicate implements Predicate<Property> {
    private final Type type;

    /**
     * Constructs a {@code PropertyTypePredicate}
     * @param typeName Housing type to search for
     */
    public PropertyTypePredicate(String typeName) {
        this.type = new Type(typeName);
    }

    @Override
    public boolean test(Property property) {
        return property.getPropertyType().equals(this.type);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyTypePredicate // instanceof handles nulls
                && type.equals(((PropertyTypePredicate) other).type)); // state check
    }

}
