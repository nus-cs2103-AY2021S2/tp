//@@author
package seedu.address.testutil;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPassengerDescriptor;
import seedu.address.model.TripDay;
import seedu.address.model.TripTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.passenger.Address;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.person.passenger.Price;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPassengerDescriptor objects.
 */
public class EditPassengerDescriptorBuilder {

    private EditCommand.EditPassengerDescriptor descriptor;

    public EditPassengerDescriptorBuilder() {
        descriptor = new EditPassengerDescriptor();
    }

    public EditPassengerDescriptorBuilder(EditCommand.EditPassengerDescriptor descriptor) {
        this.descriptor = new EditCommand.EditPassengerDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPassengerDescriptor} with fields containing {@code passenger}'s details
     */
    public EditPassengerDescriptorBuilder(Passenger passenger) {
        descriptor = new EditCommand.EditPassengerDescriptor();
        descriptor.setName(passenger.getName());
        descriptor.setPhone(passenger.getPhone());
        descriptor.setAddress(passenger.getAddress());
        descriptor.setTags(passenger.getTags());
        descriptor.setPrice(passenger.getPrice().orElse(null));
    }

    /**
     * Sets the {@code Name} of the {@code EditPassengerDescriptor} that we are building.
     */
    public EditPassengerDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPassengerDescriptor} that we are building.
     */
    public EditPassengerDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPassengerDescriptor} that we are building.
     */
    public EditPassengerDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }
    //@@author ZechariahTan
    /**
     * Sets the {@code TripDay} of the {@code EditPassengerDescriptor} that we are building.
     */
    public EditPassengerDescriptorBuilder withTripDay(DayOfWeek tripDay) {
        descriptor.setTripDay(new TripDay(tripDay));
        return this;
    }

    /**
     * Sets the {@code TripTime} of the {@code EditPassengerDescriptor} that we are building.
     */
    public EditPassengerDescriptorBuilder withTripTime(LocalTime tripTime) {
        descriptor.setTripTime(new TripTime(tripTime));
        return this;
    }
    //@@author chewterence
    /**
     * Sets the {@code Price} of the {@code EditPassengerDescriptor} that we are building.
     */
    public EditPassengerDescriptorBuilder withPrice(double price) {
        descriptor.setPrice(new Price(price));
        return this;
    }
    //@@author
    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPassengerDescriptor}
     * that we are building.
     */
    public EditPassengerDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditPassengerDescriptor build() {
        return descriptor;
    }
}
