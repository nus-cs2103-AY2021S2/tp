package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.property.Property;
import seedu.address.model.property.UniquePropertyList;

/**
 * Wraps all data at the property-book level.
 * Duplicates are not allowed (by .isSameProperty comparison).
 */
public class PropertyBook implements ReadOnlyPropertyBook {
    private final UniquePropertyList properties;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        properties = new UniquePropertyList();
    }

    public PropertyBook() {
    }

    /**
     * Creates a Property Book using the properties in the {@code toBeCopied}.
     */
    public PropertyBook(ReadOnlyPropertyBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    // =====  List overwrite operations  =========================================================================

    /**
     * Replaces the contents of the property list with {@code properties}.
     * {@code properties} must not contain duplicate properties.
     */
    public void setProperties(List<Property> properties) {
        this.properties.setProperties(properties);
    }

    /**
     * Resets the existing data of this {@code PropertyBook} with {@code newData}.
     */
    public void resetData(ReadOnlyPropertyBook newData) {
        requireNonNull(newData);
        setProperties(newData.getPropertyList());
    }

    // =====  Property-level operations  =========================================================================

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

    public Property getProperty(int i) {
        return properties.asUnmodifiableObservableList().get(i);
    }

    /**
     * Replaces the property at {@code i} in the list with {@code property}.
     * {@code i} must exist in the property book.
     * The property identity of {@code property} must not be the same as another
     * existing property in the property book.
     */
    public void setProperty(int i, Property property) {
        properties.setProperty(getProperty(i), property);
    }

    /**
     * Replaces the given property {@code target} in the list with {@code editedProperty}.
     * {@code target} must exist in the property book.
     * The property identity of {@code editedProperty} must not be the same as another
     * existing property in the property book.
     */
    public void setProperty(Property target, Property editedProperty) {
        requireNonNull(editedProperty);

        properties.setProperty(target, editedProperty);
    }

    /**
     * Removes {@code key} from this {@code PropertyBook}.
     * {@code key} must exist in the property book.
     */
    public void removeProperty(Property key) {
        properties.remove(key);
    }

    /**
     * Sorts property list using the specified comparator {@code comparator}.
     */
    public void sortProperties(Comparator<Property> comparator) {
        properties.sortProperties(comparator);
    }

    // =====  Utility methods  ===================================================================================

    @Override
    public String toString() {
        return properties.asUnmodifiableObservableList().size() + " properties";
        // TODO: refine later
    }

    @Override
    public ObservableList<Property> getPropertyList() {
        return properties.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyBook // instanceof handles nulls
                && properties.equals(((PropertyBook) other).properties));
    }

    @Override
    public int hashCode() {
        return properties.hashCode();
    }
}
