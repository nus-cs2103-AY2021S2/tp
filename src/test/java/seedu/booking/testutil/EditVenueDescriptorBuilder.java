package seedu.booking.testutil;

import seedu.booking.logic.commands.EditVenueCommand.EditVenueDescriptor;
import seedu.booking.logic.commands.EditVenueCommand;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Name;
import seedu.booking.model.person.Person;
import seedu.booking.model.person.Phone;
import seedu.booking.model.venue.Capacity;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;

/**
 * A utility class to help with building EditVenueDescriptor objects.
 */
public class EditVenueDescriptorBuilder {

    private EditVenueDescriptor descriptor;

    public EditVenueDescriptorBuilder() {
        descriptor = new EditVenueDescriptor();
    }

    public EditVenueDescriptorBuilder(EditVenueDescriptor descriptor) {
        this.descriptor = new EditVenueDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditVenueDescriptor} with fields containing {@code venue}'s details
     */
    public EditVenueDescriptorBuilder(Venue venue) {
        descriptor = new EditVenueDescriptor();
        descriptor.setVenueName(venue.getVenueName());
        descriptor.setCapacity(venue.getCapacity());
    }

    /**
     * Sets the {@code VenueName} of the {@code EditVenueDescriptor} that we are building.
     */
    public EditVenueDescriptorBuilder withVenueName(String name) {
        descriptor.setVenueName(new VenueName(name));
        return this;
    }

    /**
     * Sets the {@code Capacity} of the {@code EditVenueDescriptor} that we are building.
     */
    public EditVenueDescriptorBuilder withCapacity(Integer capacity) {
        descriptor.setCapacity(new Capacity(capacity));
        return this;
    }

    public EditVenueDescriptor build() {
        return descriptor;
    }
}
