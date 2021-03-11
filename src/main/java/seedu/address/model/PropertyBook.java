package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.property.Property;

/**
 * Wraps all data at the property-book level.
 * Duplicates are not allowed (by .isSameProperty comparison).
 */
public class PropertyBook {
    private final List<Property> properties;

    public PropertyBook() {
        properties = new ArrayList<>();
    }

    /**
     * Returns true if a property with the same identity as {@code property} exists in the app.
     *
     * @param property The property to check.
     * @return True if there is a property with the same identity, otherwise false.
     */
    public boolean hasProperty(Property property) {
        requireNonNull(property);
        return properties.contains(property);
    }

    /**
     * Adds a property to the app.
     * The property must not already exist in the property book.
     *
     * @param property The property to be added.
     */
    public void addProperty(Property property) {
        properties.add(property);
    }

    public int getPropertySize() {
        return properties.size();
    }

    public Property getProperty(int i) {
        return properties.get(i);
    }

    public void setProperty(int i, Property property) {
        properties.set(i, property);
    }
}
