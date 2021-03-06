package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.property.Property;

public class PropertyBook {
    private final List<Property> properties;
    
    public PropertyBook() {
        properties = new ArrayList<>();
    }

    public boolean hasProperty(Property property) {
        requireNonNull(property);
        return properties.contains(property);
    }
    
    public void addProperty(Property property) {
        properties.add(property);
    }
}
