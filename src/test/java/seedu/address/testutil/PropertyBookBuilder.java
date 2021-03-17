package seedu.address.testutil;

import seedu.address.model.PropertyBook;
import seedu.address.model.property.Property;

/**
 * A utility class to help with building PropertyBook objects.
 * Example usage: <br>
 *     {@code PropertyBook ab = new PropertyBookBuilder().withProperty("Mayfair", "condo",
 *     "1 Jurong East Street 32, #08-111", "609477", "2021-12-31").build();}
 */
public class PropertyBookBuilder {

    private PropertyBook propertyBook;

    public PropertyBookBuilder() {
        propertyBook = new PropertyBook();
    }

    public PropertyBookBuilder(PropertyBook propertyBook) {
        this.propertyBook = propertyBook;
    }

    /**
     * Adds a new {@code Property} to the {@code PropertyBook} that we are building.
     */
    public PropertyBookBuilder withProperty(Property property) {
        propertyBook.addProperty(property);
        return this;
    }

    public PropertyBook build() {
        return propertyBook;
    }
}
