package seedu.address.testutil;

import seedu.address.logic.commands.sort.SortPropertyCommand.SortPropertyDescriptor;
import seedu.address.model.sort.descriptor.PropertySortingKey;
import seedu.address.model.sort.descriptor.SortingOrder;

/**
 * A utility class to help with building SortPropertyDescriptor objects.
 */
public class SortPropertyDescriptorBuilder {

    private SortPropertyDescriptor descriptor;

    public SortPropertyDescriptorBuilder() {
        descriptor = new SortPropertyDescriptor();
    }

    public SortPropertyDescriptorBuilder(SortPropertyDescriptor descriptor) {
        this.descriptor = new SortPropertyDescriptor(descriptor);
    }
    /**
     * Sets the {@code SortingOrder} of the {@code SortPropertyDescriptor} that we are building.
     */
    public SortPropertyDescriptorBuilder withSortingOrder(String sortingOrder) {
        descriptor.setSortingOrder(new SortingOrder(sortingOrder));
        return this;
    }

    /**
     * Sets the {@code PropertySortingKey} of the {@code SortPropertyDescriptor} that we are building.
     */
    public SortPropertyDescriptorBuilder withPropertySortingKey(String propertySortingKey) {
        descriptor.setPropertySortingKey(new PropertySortingKey(propertySortingKey));
        return this;
    }

    public SortPropertyDescriptor build() {
        return descriptor;
    }
}
