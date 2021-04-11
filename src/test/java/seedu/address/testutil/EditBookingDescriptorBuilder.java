package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.logic.commands.EditBookingCommand.EditBookingDescriptor;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Phone;
import seedu.address.model.booking.TenantName;

public class EditBookingDescriptorBuilder {

    private EditBookingDescriptor descriptor;

    public EditBookingDescriptorBuilder() {
        descriptor = new EditBookingDescriptor();
    }

    public EditBookingDescriptorBuilder(EditBookingDescriptor descriptor) {
        this.descriptor = new EditBookingDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBookingDescriptor} with fields containing {@code booking}'s details
     */
    public EditBookingDescriptorBuilder(Booking booking) {
        descriptor = new EditBookingDescriptor();
        descriptor.setName(booking.getTenantName());
        descriptor.setPhone(booking.getPhone());
        descriptor.setStartDate(booking.getStart());
        descriptor.setEndDate(booking.getEnd());
    }

    /**
     * Sets the {@code TenantName} of the {@code EditBookingDescriptor} that we are building.
     */
    public EditBookingDescriptorBuilder withName(String name) {
        descriptor.setName(new TenantName(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditBookingDescriptor} that we are building.
     */
    public EditBookingDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the start date of the {@code EditBookingDescriptor} that we are building.
     */
    public EditBookingDescriptorBuilder withStartDate(LocalDate startDate) {
        descriptor.setStartDate(startDate);
        return this;
    }

    /**
     * Sets the end date of the {@code EditBookingDescriptor} that we are building.
     */
    public EditBookingDescriptorBuilder withEndDate(LocalDate endDate) {
        descriptor.setEndDate(endDate);
        return this;
    }


    public EditBookingDescriptor build() {
        return descriptor;
    }


}
