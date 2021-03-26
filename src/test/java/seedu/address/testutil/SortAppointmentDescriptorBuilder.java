package seedu.address.testutil;

import seedu.address.logic.commands.sort.SortAppointmentCommand.SortAppointmentDescriptor;
import seedu.address.model.sort.descriptor.AppointmentSortingKey;
import seedu.address.model.sort.descriptor.SortingOrder;

/**
 * A utility class to help with building SortAppointmentDescriptor objects.
 */
public class SortAppointmentDescriptorBuilder {

    private SortAppointmentDescriptor descriptor;

    public SortAppointmentDescriptorBuilder() {
        descriptor = new SortAppointmentDescriptor();
    }

    public SortAppointmentDescriptorBuilder(SortAppointmentDescriptor descriptor) {
        this.descriptor = new SortAppointmentDescriptor(descriptor);
    }
    /**
     * Sets the {@code SortingOrder} of the {@code SortAppointmentDescriptor} that we are building.
     */
    public SortAppointmentDescriptorBuilder withSortingOrder(String sortingOrder) {
        descriptor.setSortingOrder(new SortingOrder(sortingOrder));
        return this;
    }

    /**
     * Sets the {@code AppointmentSortingKey} of the {@code SortAppointmentDescriptor} that we are building.
     */
    public SortAppointmentDescriptorBuilder withAppointmentSortingKey(String appointmentSortingKey) {
        descriptor.setAppointmentSortingKey(new AppointmentSortingKey(appointmentSortingKey));
        return this;
    }

    public SortAppointmentDescriptor build() {
        return descriptor;
    }
}
